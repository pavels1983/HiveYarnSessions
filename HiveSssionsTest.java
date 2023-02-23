package ru.rosbank.hdp;

import ru.rosbank.hdp.core.HiveQuery;
import ru.rosbank.hdp.core.HdpUtils;
import ru.rosbank.hdp.core.YarnApp;
import ru.rosbank.hdp.xml.Apps;
import ru.rosbank.hdp.xml.Apps.App;

import java.util.ArrayList;
import java.util.List;

public class HiveSssionsTest {


	public static void main(String[] args) throws Exception {
        AppProps props = new AppProps();
        props.readProperties("app.properties");

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
        for (App a : apps.getApp()) {
        	if (a.getId().equalsIgnoreCase("application_1573227872340_1905")) {
        		System.out.println("application_1573227872340_1908");
        		//System.exit(0);
        	}
        }
        
        /*
        List<YarnApp> joined = HdpUtils.joinYarnHive(apps, hiveQueries, true, true, true);
        List<YarnApp> sortedApps = YarnApp.sortApps(joined);
        for (YarnApp app : sortedApps) {
            HdpUtils.printApp(app.app);
            if (app. query != null) app.query.print();
        }
        */
	}
	

}
