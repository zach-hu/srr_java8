/*
 * Created on Nov. 19, 2003
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineDataRetrieveList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
	        PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
	        processLoader.setApplicationName(this.getApplicationName());
			PuridiomProcess process = processLoader.loadProcess("rfqlinedata-retrieve-by-id.xml");
			        
			List rfqLineList = (List) incomingRequest.get("rfqLineList");
			
			if (rfqLineList != null) {
			    for (int i=0; i < rfqLineList.size(); i++) {
	            	RfqLine rfqLine = (RfqLine) rfqLineList.get(i);

	            	incomingRequest.put("rfqLine", rfqLine);
					process.executeProcess(incomingRequest);
					
					rfqLine = (RfqLine) incomingRequest.get("rfqLine");
					
					rfqLineList.set(i, rfqLine);
					
					this.setStatus(process.getStatus()) ;
					if (process.getStatus() == Status.FAILED) {
						break ;
					}
	        	}
			}
	        
	        incomingRequest.remove("rfqLine");
	        
	        result = rfqLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
	
}
