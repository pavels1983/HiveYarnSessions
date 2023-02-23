package ru.dna.core;

import java.util.ArrayList;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class AppProps {
	private ArrayList<String> hiveServer2Urls;
    public int appHttpPort = 9111;
    public String resourseManagerUrl;
    public String timelineWebUrl;
    public boolean DEBUG = false;

    public ArrayList<String> getHs2WebUrls() {
    	ArrayList<String> res = new ArrayList<String>();
    	
    	for (String url: hiveServer2Urls) {
    		res.add("http://"+ url);
    	}
    	return res;
    }
    public String getRmApiAppsUrl() {
        return "http://"+ resourseManagerUrl + "/ws/v1/cluster/apps";
    }
    public String getTimelineWsUrl() {
        return "http://"+ timelineWebUrl + "/ws/v1/timeline/TEZ_DAG_ID?limit=100";
    }
    
    //@SneakyThrows
    public void readProperties(String fileName) throws ConfigurationException  {
    	hiveServer2Urls = new ArrayList<String>();
    	PropertiesConfiguration props = new PropertiesConfiguration();
        DEBUG = false;
        props.setDelimiterParsingDisabled(true);
		props.load(fileName);
        appHttpPort = props.getInt("server.http.port");
        resourseManagerUrl = props.getString("resourse.manager.address");
        timelineWebUrl = props.getString("timeline.server.address");
        props.getKeys("hive.server2.address").forEachRemaining(adr ->  hiveServer2Urls.add(props.getString(adr)));
        DEBUG = props.getString("debug").equalsIgnoreCase("true");

    }
}
