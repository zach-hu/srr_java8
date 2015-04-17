/*
 * Created on July 27, 2004
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineRetrieveListFromHeaderList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("receiptlinedata-retrieve-by-header.xml");
			
			List receiptHeaderList = (List) incomingRequest.get("receiptHeaderList");
			int	ii = 0;
	        for (Iterator it = receiptHeaderList.iterator(); it.hasNext(); ) {
	        	Map requestParameters = new HashMap();
	        	requestParameters.put("userId", incomingRequest.get("userId"));
	        	requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("receiptHeader", (ReceiptHeader) it.next());
				process.executeProcess(incomingRequest);
				
				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
				
				ReceiptHeader receiptHeader = (ReceiptHeader) requestParameters.get("receiptHeader");
				receiptHeaderList.set(ii, receiptHeader);
				ii++;
	        }
	        
	        incomingRequest.remove("receiptLine");
	        
	        result = receiptHeaderList;
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
