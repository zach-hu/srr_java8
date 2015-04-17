/*
 * Created on Aug 19, 2003 
 */
package com.tsagate.foundation.processengine;

/**
 * @author Administrator 
 */
public class ActivityStatus {
	
	private String status = "";
	public final static String CANCELLED = "CANCELLED";
	public final static String FAILED = "FAILED";
	public final static String READY = "READY";
	public final static String ACTIVE = "ACTIVE";
	public final static String ABORTING = "ABORTING";
	public final static String COMPLETING = "COMPLETING";
	public final static String ABORTED = "ABORTED";
	public final static String COMPLETED = "COMPLETED";
	
	private final static String statuses [] = new String[] {"CANCELLED", "FAILED", "READY", "ACTIVE", "ABORTING",  "COMPLETING", "ABORTED", "COMPLETED" };
	
	public ActivityStatus(){
	}
	public void setStatus(String theStatus) throws Exception{
		try{
			int numberOfStatuses = statuses.length;
			boolean goodStatusType = false;
			for(int i = 0; i < numberOfStatuses; i++){
				if(theStatus.equalsIgnoreCase(statuses[i])){
					goodStatusType = true;
				}
			}
			if(goodStatusType == true){
				status = theStatus;
			}
			else{
				throw new Exception("not a valid status, please choose one of the static status types.");
			}			
		}
		catch(Exception exception){
			throw exception;
		}
	}
	public String getStatus(){
		return status;
	}

}
