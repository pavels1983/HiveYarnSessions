package ru.dna.core;

import ru.dna.xml.Apps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YarnApp {
    public HiveQuery query;
    public Apps.App app;
    public String dagID;

    public YarnApp (Apps.App app, HiveQuery query, String dagId) {
        this.app = app;
        this.query = query;
    }

    public static List<YarnApp> sortApps(List<YarnApp> apps) {
        ArrayList<YarnApp> result = new ArrayList<YarnApp>();

        // 1. Running Hive queries
        for (YarnApp app: apps) {
            if (app.query != null) {
                if (app.query.state.equalsIgnoreCase("RUNNING")) {
                    result.add(app);
                }
            }
        }

        // 2. Running YARN non-hive apps
        for (YarnApp app: apps) {
            if (app.query == null) {
                if (app.app != null) {
                    if (app.app.getState().equalsIgnoreCase("RUNNING")) {
                        result.add(app);
                    }
                }
            }
        }

        // 3. Finished/exception hive queries
        for (YarnApp app: apps) {
            if (app.query != null) {
                if (!app.query.state.equalsIgnoreCase("RUNNING")) {
                    result.add(app);
                }
            }
        }

        // 3. Finished/exception YARN non-hive apps
        for (YarnApp app: apps) {
            if (app.query == null) {
                if (app.app != null) {
                    if (!app.app.getState().equalsIgnoreCase("RUNNING")) {
                        result.add(app);
                    }
                }
            }
        }

        return result;
    }
    
    public String getUser() {
    	if (this.app != null)
    		return this.app.getUser();
    	else {
    		if (this.query != null)
    			return this.query.userName;
    		else
    			return "";
    	}
    }
    
    public String getStartTime() {
        Date start_time = null;
        if (this.query != null)
            start_time = this.query.openedTimestamp;
        else
            if (this.app == null) return "null";
            else start_time = new Date(this.app.getStartedTime());
        if (start_time == null) return "null";
        return Utils.DateToStr(start_time, "dd.MM.yyyy HH:mm:ss");
    }

    public String getFinishTime() {
        Date finish_time = null;
        if (this.query != null)
            finish_time = this.query.closeTimestamp;
        else
            if (this.app == null) return "null";
            else finish_time = new Date(this.app.getFinishedTime());
        if (finish_time == null) return "null";
        if (Utils.DateToStr(finish_time, "dd.MM.yyyy HH:mm:ss").equals("01.01.1970 03:00:00"))
        	return "n/a";
        else
        	return Utils.DateToStr(finish_time, "dd.MM.yyyy HH:mm:ss");
    }

    public String getState() {
        if (this.query == null)
            return this.app.getState();
        else
            return this.query.state;
    }
    
    public String getType() {
    	String type = (this.app == null)?"":this.app.getApplicationType().toLowerCase();
		if (type.isEmpty()) { if (this.query != null) type = "tez"; }
		return type;
    }
    
    
    public String getSybtype() {
		String appName = ((this.app == null)?"":this.app.getName()).toLowerCase();
		if (appName.startsWith("oozie")) return "oozie";
		if (appName.startsWith("llap")) return "llap";
		if (appName.startsWith("hive")) return "hive";
		if (appName.startsWith("thrift")) return "thrift";
    	if (getType().equalsIgnoreCase("tez")) return "hive";
		return "";
    }
    
    public long getLatencySec() {
		long latency = -1;
		if (this.query != null) {
            if (!this.query.latencySecs.isEmpty())
            	try {
            		latency = (Long)(Long.valueOf(this.query.latencySecs));
            		return latency;
            	}
	    		catch (Exception e) {
	    			try {
	    				if (this.query.closeTimestamp == null) {
	    					latency = (Long)((new Date().getTime() - this.query.openedTimestamp.getTime())/1000);
	    				} else {
	    					latency = (Long)((this.query.openedTimestamp.getTime() - this.query.openedTimestamp.getTime())/1000);
	    				}
	    				
	    			} catch (Exception e2) {
	    				latency = -2;
	    			}
	    		}
        }
		
		if (this.app != null) {
			if (this.app.getFinishedTime() == 0) {
				latency = (Long)((new Date().getTime() - this.app.getStartedTime())/1000);
			}
			else {
				latency = ((Long)((this.app.getFinishedTime() - this.app.getStartedTime())/1000));
			}
		}
        
    	return latency;
    }
    
    public boolean isRunning() {
    	if (this.app != null) {
    		return this.app.getState().equalsIgnoreCase("RUNNING");
    	} else {
    		if (this.query != null) {
    			return this.query.state.equalsIgnoreCase("RUNNING");
    		} else
    			return false;
    	}
    }
    
    public String getLantencyStr() {
    	long latency = this.getLatencySec();
    	if (latency < 0) return "";
    	String result = "";
		if (latency < 60) 
			result = latency + " sec";
		else if (latency < 60*60)
			result = (long)(latency/60) + " min";
		else if (latency < 60*60*60)
			result = (long)(latency/60/60) + " hrs";
		else
			result = (long)(latency/60/60/24) + " days";
		
		if (isRunning()) {
    		result = result + "+<br>not finished";
    	}
    	return result;
    }
    
    public String getLatencyColor() {
    	long latency = this.getLatencySec();
    	if (latency <= 0) latency = 0;
    	else {
    		String type = this.getType();
    		if (type.isEmpty()) {  }
    		if (type.equalsIgnoreCase("tez") || type.equalsIgnoreCase("spark") || type.equalsIgnoreCase("mapreduce")) {
    			if (!(this.getSybtype().equals("oozie")||this.getSybtype().equals("thrift"))) {
		    		latency = (long)latency/60; //minutes
		    		if (latency > 255) latency = 255;
    			}
    			else latency = 0;
    		}
    		else latency = 0;
    	}
    	return "#ff" + Long.toHexString(255-latency) + Long.toHexString(255-latency); 
    }
    
    public String getSQL(boolean trimLen, boolean removeNewLines) {
        if (this.query == null) return "";
        int maxLen = 1000;
        String sql = this.query.query;
        if (removeNewLines) sql = sql.replaceAll("\\s*<br/?>\\s*"," ");
        if (trimLen) {
            if (sql.length() > maxLen) {
                sql = sql.substring(0, maxLen - 15) + "... (+" + (sql.length() - maxLen) + " chars more...)";
            }
        }
        //sql = sql.replaceAll("[^ a-zA-Z0-9_\\.\\\\(\\)\\s\\t\\r\\n+-=%!@#$%\\^&\\*\\[\\],/:'\\|<>\\?`]", "*");
        return sql;
    }

    public String getErrorText() {
    	String result = "";
    	if (this.query != null) {
    		result = this.query.errorText;
    	}
    	return result;
    }
    
    public String getResources() {
    	if (this.app != null) {
    		if (app.getAllocatedMB() > 0) {
	    		String res = "exception"; 
	    		try {
	    			float mem = (float)app.getAllocatedMB()/1024;
	    			res = String.format("%.1f", mem) + " GB<br>" + app.getRunningContainers() + " c / " + app.getAllocatedVCores() + " cpu";
	    		} catch (Exception e) {}
	    		return res;
    		} else return "";
    	}
    	else return "";
    }
    
    public String getResourcesColor() {
    	if (this.app != null) {
    		if (app.getAllocatedMB() > 0) {
    			long col = (255*app.getAllocatedMB())/(910*1024);
    			if (col > 255) col = 255;
    			return "#ff" + Long.toHexString(255-col) + Long.toHexString(255-col);
    		} else
    			return "#ffffff";
    	}
    	else return "#ffffff";
    }
}
