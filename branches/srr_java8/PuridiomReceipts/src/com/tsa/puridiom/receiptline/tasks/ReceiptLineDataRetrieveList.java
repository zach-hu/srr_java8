/*
 * Created on Nov. 19, 2003
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineDataRetrieveList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
	        PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("receiptlinedata-retrieve-by-id.xml");
			        
			List receiptLineList = (List) incomingRequest.get("receiptLineList");
	        for (Iterator it = receiptLineList.iterator(); it.hasNext(); ) {
					incomingRequest.put("receiptLine", (ReceiptLine) it.next());
					process.executeProcess(incomingRequest);
					this.setStatus(process.getStatus()) ;
					if (process.getStatus() == Status.FAILED) {
						break ;
					}
	        }
	        
	        incomingRequest.remove("receiptLine");
	        
	        result = receiptLineList;
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
