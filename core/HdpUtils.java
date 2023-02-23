package ru.rosbank.hdp.core;

import ru.rosbank.hdp.AppProps;
import ru.rosbank.hdp.Utils;
import ru.rosbank.hdp.json.Entity;
import ru.rosbank.hdp.json.TezDagApps;
import ru.rosbank.hdp.xml.Apps;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HdpUtils {

    public static List<HiveQuery> getHiveQueries(AppProps props, ArrayList<String> resps, ArrayList<String> urls) throws Exception {
        List<HiveQuery> result = new ArrayList<HiveQuery>();
        if (props.DEBUG) System.out.println("Parsing Hive running queries");
        
        for (int index = 0; index < resps.size(); index++) {
	        String resp = resps.get(index);
        	//if (resp==null) return ret;
	        String[] s1 = resp.split("<h2>Open Queries</h2>");
	        //if (s1[1] == null) return ret;
	        String[] s2 = s1[1].split("<td colspan=\"8\">Total number of queries:");
	
	        String running_queries = s2[0];
	        String[] trs = running_queries.split("<th>Drilldown Link</th>\\s+</tr>\\s+");
	        if (trs == null || trs.length < 2) return result;
	        String[] tr_entry = trs[1].split("</tr>\\s+<tr>");
	        if (tr_entry[0].length() > 10) {
	            for (String tr : tr_entry) {
	                if (tr.length() < 20) continue;
	                tr.replaceAll("</?tr>\\s+", "");
	                String[] tr_vals = tr.split("</td>\\s+<td>");
	                HiveQuery q = new HiveQuery();
	                q.userName = tr_vals[0].replaceAll("(<tr>\\s*)?<td>\\s*", "");
	                q.query = tr_vals[1];
	                q.executionEngine = Utils.regexpLike(tr_vals[2], ".*?(\\w+)\\s*");
	                q.state = Utils.regexpLike(tr_vals[2], "<td>(\\w+)\\s*");
	                q.openedTimestamp = Utils.StrToDate(tr_vals[3], "EEE MMM dd HH:mm:ss z yyyy");
	                q.openedSecs = Long.valueOf(tr_vals[4]);
	                q.latencySecs = tr_vals[5];
	                q.operationId = Utils.regexpLike(tr_vals[6], ".*?operationId=(.*)>Drilldown.*");
	                q.hs2DetailsUrl = urls.get(index) + "/query_page?operationId=" + q.operationId;
	                String details = Utils.httpGet(q.hs2DetailsUrl, "application/xml", props.DEBUG);
	                // skip long pages to pase
	                if (details.length() < 1024*1024) {
	                	// get more details via Drilldown link
	                	if (!((details == null) || details.trim().isEmpty())) {
		                    String dcore = Utils.regexpLike(details, ".*?<div class=\"tab-pane active\" id=\"tab_baseProfile\">(.*?)</table>.*?");
		                    String sCloseTimestamp = Utils.regexpLike(dcore, ".*?<td>Closed Timestamp</td>\\s*<td>(.*?)</td>.*?");
		                    try {
		                        q.closeTimestamp = Utils.StrToDate(sCloseTimestamp, "EEE MMM dd HH:mm:ss z yyyy");
		                    } catch (Exception e) {}
		                    q.id = Utils.regexpLike(dcore, ".*?<td>Id</td>\\s*<td>(.*?)</td>.*?");
		                    q.errorText = Utils.regexpLike(dcore, ".*?<td>Error</td>\\s*<td>(.*?)</td>.*?");
		                }
	                }
	                if (props.DEBUG) q.print();
	                
	                if (!q.query.trim().equalsIgnoreCase("Progress")) {
	                	result.add(q);
	                }
	            }
	        }
        }
        
        if (props.DEBUG) System.out.println("Parsing Hive closed queries");
        
        for (int index = 0; index < resps.size(); index++) {
        	String resp = resps.get(index);
        	String[] s1 = resp.split("<h2>Open Queries</h2>");
	        String[] s2 = s1[1].split("<td colspan=\"8\">Total number of queries:");
        	String closed_queries = s2[1];
        	String[] trs = closed_queries.split("<th>Drilldown Link</th>\\s+</tr>\\s+");
	        if (trs == null || trs.length < 2) return result;
	        String[] tr_entry = trs[1].split("</tr>\\s+<tr>");
	        if (tr_entry[0].length() < 10) return result;
	
	        for (String tr: tr_entry) {
	            if (tr.length() < 20) continue;
	            tr.replaceAll("</?tr>\\s+", "");
	            String[] tr_vals = tr.split("</td>\\s+<td>");
	            HiveQuery q = new HiveQuery();
	            q.userName = tr_vals[0].replaceAll("(<tr>\\s*)?<td>\\s*", "").trim();
	            q.query = tr_vals[1];
	            q.executionEngine = Utils.regexpLike(tr_vals[2],".*?(\\w+)\\s*");
	            q.state = Utils.regexpLike(tr_vals[2],"<td>(\\w+)\\s*");
	            q.closeTimestamp = Utils.StrToDate(tr_vals[4],"EEE MMM dd HH:mm:ss z yyyy");
	            q.openedSecs = Long.valueOf(tr_vals[3]);
	            q.latencySecs = tr_vals[5];
	            q.operationId = Utils.regexpLike(tr_vals[6],".*?operationId=(.*)>Drilldown.*");
	            q.hs2DetailsUrl = urls.get(index) + "/query_page?operationId=" + q.operationId;
	            String details = Utils.httpGet(q.hs2DetailsUrl, "application/xml", props.DEBUG);
	            // skip long pages to pase
                if (details.length() < 1024*1024) {
                	// get more details via Drilldown link
                	if (!((details == null) || details.trim().isEmpty())) {
		                String dcore = Utils.regexpLike(details, ".*?<div class=\"tab-pane active\" id=\"tab_baseProfile\">(.*?)</table>.*?");
		                String sOpenTimestamp = Utils.regexpLike(dcore, ".*?<td>Opened Timestamp</td>\\s*<td>(.*?)</td>.*?");
		                q.openedTimestamp = Utils.StrToDate(sOpenTimestamp, "EEE MMM dd HH:mm:ss z yyyy");
		                q.id = Utils.regexpLike(dcore, ".*?<td>Id</td>\\s*<td>(.*?)</td>.*?");
		                q.errorText = Utils.regexpLike(dcore, ".*?<td>Error</td>\\s*<td>(.*?)</td>.*?");
		            }
                }
	            if (props.DEBUG) q.print();
	            if (!q.query.trim().equalsIgnoreCase("Progress")) {
                	result.add(q);
                }
	        }
        }
        if (props.DEBUG) System.out.print("Retreived and parsed " + result.size() + " hive queries");
        return result;
    }

    public static Apps getYarnApps(AppProps props, String appsXml) {
        //String testXml = Utils.readFileAsString("C:\\temp\\apps.xml");
        Apps apps = null;
        appsXml = appsXml.replaceAll("<\\?.*?\\?>", "");

        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(Apps.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            apps = (Apps) jaxbUnmarshaller.unmarshal(new StringReader(appsXml));
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        if (props.DEBUG) System.out.print("Retreived and parsed " + apps.getApp().size() + " YARN applications");
        return apps;
    }
    
    public static HashMap<String,TezDag> getTezDags(AppProps props, String dagsJson) {
    	ObjectMapper objectMapper = new ObjectMapper();
    	TezDagApps dags = null;
    	HashMap<String,TezDag> res = new HashMap<String,TezDag>();
    	try {
    		dags = objectMapper.readValue(dagsJson, TezDagApps.class);
        }
        catch (Exception e) {
        	e.printStackTrace();
        	return res;
        }
    	
    	for (Entity en : dags.getEntities()) {
    		TezDag dag = new TezDag();
    		dag.dagID = en.getEntity();
    		dag.appID = en.getOtherinfo().getApplicationId();
    		dag.hiveID = en.getOtherinfo().getCallerId();
    		res.put(dag.hiveID, dag);
    	}
    	
    	if (props.DEBUG) System.out.print("Retreived and parsed " + res.size() + " DAG's");
    	return res;
    }
    
    
    public static List<YarnApp> joinYarnHive(Apps apps, List<HiveQuery> queries, HashMap<String,TezDag> dags, boolean includeMatched, boolean includeYarnUnmatched, boolean includeHiveUnmatched) {
        List<YarnApp> result = new ArrayList<YarnApp>();

        // Build HiveQuery id index
        HashMap<String, HiveQuery> idx_queries = new HashMap<String, HiveQuery>();
        for (HiveQuery query : queries) {
        	if (query.id != null && !query.id.trim().isEmpty()) {
        		idx_queries.put(query.id, query);
        	} else {
        		idx_queries.put(query.operationId, query);
        	}
        }
        
        // Perform join
        for (Apps.App app : apps.getApp()) {
            String tags = app.getApplicationTags();
            if (!((tags == null) || tags.isEmpty())) {
                if (tags.matches("hive_\\d{14}_\\w+{8}(-\\w+{4}){3}-\\w+{12},userid=\\w+")) {
                    String query_id_link = tags.replaceAll(",userid=\\w+", "");
                    HiveQuery query = idx_queries.get(query_id_link);
                    idx_queries.remove(query_id_link);
                    TezDag dag = dags.get(query_id_link);
                	String dagId = dag == null ? null : dag.dagID;
                    if (query != null) {
                        if (includeMatched) result.add(new YarnApp(app, query, dagId));
                    } else {
                        if (includeYarnUnmatched) result.add(new YarnApp(app, null, dagId));
                    }
                }
                else {
                	if (includeYarnUnmatched) result.add(new YarnApp(app, null, null));
                }
            }
            else {
                if (includeYarnUnmatched) result.add(new YarnApp(app, null, null));
            }
        }
        
        if (includeHiveUnmatched) {
        	// Build App id index
        	HashMap<String, Apps.App> idx_apps = new HashMap<String, Apps.App>();
        	for (Apps.App app : apps.getApp()) {
        		idx_apps.put(app.getId(), app);
        	}
        	
            for (HiveQuery q : idx_queries.values()) {
            	TezDag dag = dags.get(q.id);
            	String dagId = dag == null ? null : dag.dagID;
            	String appId = dag == null ? null : dag.appID;
                result.add(new YarnApp(idx_apps.get(appId), q, dagId));
            }
        }
        return result;
    }

    public static void printApp(Apps.App app) {
        if (app != null) {
            System.out.println("ApplicationId = " + app.getId());
            System.out.println("  User =   " + app.getUser());
            System.out.println("  Type =   " + app.getApplicationType());
            System.out.println("  Queue =  " + app.getQueue());
            System.out.println("  Tags  =  " + app.getApplicationTags());
            System.out.println("  State =  " + app.getState());
            System.out.println("  Start =  " + app.getStartedTime());
            System.out.println("  Finish = " + app.getFinishedTime());
        }
    }
}
