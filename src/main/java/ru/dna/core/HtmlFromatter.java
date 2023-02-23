package ru.dna.core;

import ru.dna.core.AppProps;
import ru.dna.core.Utils;
import ru.dna.core.YarnApp;

import java.io.IOException;
import java.util.List;

public class HtmlFromatter {

    public String encodeAppList(List<YarnApp> apps, AppProps props, boolean admMode) throws IOException {
        String style = Utils.readFileAsString("table_style.css");
        String header = Utils.readFileAsString("header.html");
        
        StringBuffer html = new StringBuffer();

        html.append("<html>");
        html.append("<head><title>Hadoop applications</title>\n");
        html.append("<style>\n" + style + "</style>\n</head>");
        html.append("<body>\n");
        html.append("<table class=\"fixed\">\n");
        html.append(header);
        
        for (YarnApp app : apps) {

            String appId = (app.app == null)?"":app.app.getId();
            String appName = (app.app == null)?"":app.app.getName();
            
            String queue = (app.app == null)?"":app.app.getQueue();
            String sql = "";
            String state = app.getState();
            String state_color = "#ffffff";
            String killLink = "";
            if (state.equalsIgnoreCase("RUNNING") && !appId.isEmpty() && admMode)
            	killLink = "&nbsp;(<a href=\"/kill?app_id=" + appId + "\" target=\"_blank\">kill</a>)";
            
            switch (state) {
	            case "RUNNING":   state_color = "#8cd98c"; break;
	            case "FINISHED":  state_color = "#e0e0eb"; break;
	            case "FAILED":    state_color = "#ff9d9d"; break;
	            case "ERROR":     state_color = "#ff9d9d"; break;
	            case "KILLED":    state_color = "#e6b3e6"; break;
            }
            String appUrl = "<a href=\"" + props.resourseManagerUrl + "/cluster/app/" + appId + "\">" + appId + "</a>" + killLink + "<br>" + appName;
            String type = app.getType();
            String typeColor = "#ffffff";
            if (type.equals("tez")) 											typeColor = "#ffff66";
            else if (type.equals("spark") && app.getSybtype().equals("thrift")) typeColor = "#dca900";
            else if (type.equals("spark"))										typeColor = "#ffcc00";
            else if (app.getSybtype().equals("oozie"))							typeColor = "#b3d9ff";
            else if (type.equals("mapreduce"))								    typeColor = "#f2f2f2";
            else if (app.getSybtype().equals("llap"))							typeColor = "#ffffe6";
            
            if (app.query != null) {
                sql = app.getSQL(true, false);
                sql = "<div align=\"right\"><a href=\"" + app.query.hs2DetailsUrl +"\">Hive operationId = " + app.query.operationId + "</a>; " +
                      "id = " + ((app.query != null) ? app.query.id : app.app.getApplicationTags().replaceAll(",userid=\\w+", "")) + "</div>" + sql;
                if ((state != null && state.equals("FAILED") || state.equals("ERROR")) && !app.getErrorText().isEmpty()) {
                	sql = sql + "<br><div style=\"background-color:" + state_color + "\">" + app.getErrorText() + "</div>";
                }
            }

            html.append("  <tr>\n");
            html.append("    <td bgcolor=\"" + state_color + "\">" + state + "</td>\n");
            html.append("    <td>" + app.getUser() + "</td>\n");
            html.append("    <td bgcolor=\"" + typeColor + "\">" + type + "<br>" + app.getSybtype() + "</td>\n");
            html.append("    <td>" + queue + "</td>\n");
            html.append("    <td bgcolor=\"" + app.getResourcesColor() + "\">" + app.getResources() + "</td>\n");
            html.append("    <td>" + app.getStartTime() + "<br>" +app.getFinishTime() + "</td>\n");
            html.append("    <td bgcolor=\"" + app.getLatencyColor() + "\">" + app.getLantencyStr() + "</td>\n");
            html.append("    <td class=\"sql\">" + appUrl + "</td>\n");
            html.append("    <td class=\"sql\">" + sql + "</td>\n");
            html.append("  </tr>\n");
        }
        html.append("</table>\n");
        html.append("</body>\n");
        html.append("</html>");
        return html.toString();
    }

}
