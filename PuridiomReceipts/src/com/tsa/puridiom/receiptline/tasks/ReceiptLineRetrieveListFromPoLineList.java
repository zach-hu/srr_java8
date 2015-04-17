/*
 * Created on August 5, 2004
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineRetrieveListFromPoLineList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("receiptline-retrieve-by.xml");
			
			List poLineList = (List) incomingRequest.get("poLineList");
			int	ii = 0;
	        for (Iterator it = poLineList.iterator(); it.hasNext(); ) {
	        	PoLine poLine = (PoLine) it.next();
	        	Map requestParameters = new HashMap();
	        	requestParameters.put("userId", incomingRequest.get("userId"));
	        	requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("ReceiptLine_icPoLine", String.valueOf(poLine.getIcPoLine()));
				process.executeProcess(requestParameters);
				
				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
				List receiptLineList = (List) requestParameters.get("receiptLineList");
				poLine.setReceiptLineList(receiptLineList);	
				poLineList.set(ii, poLine);
				ii++;
	        }
	        
	        result = poLineList;
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
