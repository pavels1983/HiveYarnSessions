package ru.dna;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import ru.dna.core.*;
import ru.dna.xml.Apps;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

public class AppsHttpService {

    public static AppProps props;

    public static void main(String[] args) throws IOException, ConfigurationException {
        props = new AppProps();
        props.readProperties("app.properties");

        HttpServer server = HttpServer.create(new InetSocketAddress(props.appHttpPort), 0);
        server.createContext("/apps", new AppsHandler());
        server.createContext("/kill", new KillHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println(new Date() + ": Starting web server at port " + props.appHttpPort);
        server.start();
    }

    static class AppsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            String response;
            try {
                // Collect running Hie queries
            	ArrayList<String> resps = new ArrayList<String>(); 
            	for (String hiveUrl : props.getHs2WebUrls()) {
            		String resp = Utils.httpGet(hiveUrl, "application/xml", props.DEBUG);
            		resps.add(resp);
            	}
                //String resp = Utils.readFileAsString("C:\\temp\\HiveServer2_history.xml");
                List<HiveQuery> hiveQueries = HdpUtils.getHiveQueries(props, resps, props.getHs2WebUrls());
                
                //Collect YARN applicaions
                String appsXml = Utils.httpGet(props.getRmApiAppsUrl(), "application/xml", props.DEBUG);
                Apps apps = HdpUtils.getYarnApps(props, appsXml);
                
                //Collect Timeline service DAGs
                String dagsJson = Utils.httpGet(props.getTimelineWsUrl(), "application/json", props.DEBUG);
                HashMap<String,TezDag> dags = HdpUtils.getTezDags(props, dagsJson);
                
                List<YarnApp> joined = HdpUtils.joinYarnHive(apps, hiveQueries, dags, true, true, true);
                List<YarnApp> sortedApps = YarnApp.sortApps(joined);
                
                // check if adm mode on
                Map<String, String> params = queryToMap(t.getRequestURI().getQuery());
            	//t.getRemoteAddress().getHostString();
            	boolean admMode = params.containsKey("adm") || params.containsKey("ADM");
            	
                HtmlFromatter htmlf = new HtmlFromatter();
                response = htmlf.encodeAppList(sortedApps, props, admMode);
            }
            catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                response = sw.toString().replaceAll("\\n", "<br>");
            }
            
            int resp_length = response.getBytes("UTF-8").length;
            t.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            t.getResponseHeaders().set("Content-Length", String.valueOf(resp_length));
            t.sendResponseHeaders(200, resp_length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }
    }
    
    static class KillHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
        	String response = "";
            try {
            	Map<String, String> params = queryToMap(t.getRequestURI().getQuery());
            	//t.getRemoteAddress().getHostString();
            	String appId = params.get("app_id");
            	Runtime rt = Runtime.getRuntime();
            	System.out.println(new Date() + ": Host " + t.getRemoteAddress().getHostName() + ", killing app: " + appId);
            	Process pr = rt.exec("/usr/bin/yarn application -kill " + appId);
                response = pr.getOutputStream().toString();
            }
            catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                response = sw.toString().replaceAll("\\n", "<br>");
            }
            
            int resp_length = response.getBytes("UTF-8").length;
            t.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            t.getResponseHeaders().set("Content-Length", String.valueOf(resp_length));
            t.sendResponseHeaders(200, resp_length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }
    }
    
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
