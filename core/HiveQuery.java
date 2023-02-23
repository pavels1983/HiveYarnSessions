package ru.rosbank.hdp.core;

import java.util.Date;

public class HiveQuery {

	public String id;
	public String userName;
	public String query;
	public String executionEngine;
	public String state;
	public Date openedTimestamp;
	public Date closeTimestamp;
	public Long openedSecs;
	public String latencySecs;
	public String operationId;
	public String hs2DetailsUrl;
	public String errorText;
	
	public void print() {
		System.out.println("userName =        "+userName);
		System.out.println("query =           "+query);
		System.out.println("executionEngine = "+executionEngine);
		System.out.println("state =           "+state);
		System.out.println("openedTimestamp = "+openedTimestamp);
		System.out.println("closeTimestamp =  "+closeTimestamp);
		System.out.println("openedSecs =      "+openedSecs);
		System.out.println("latencySecs =     "+latencySecs);
		System.out.println("operationId =     "+operationId);
		System.out.println("url =             "+hs2DetailsUrl);
		System.out.println("exception =       "+errorText);
		System.out.println("id =              "+id);
	};
}
