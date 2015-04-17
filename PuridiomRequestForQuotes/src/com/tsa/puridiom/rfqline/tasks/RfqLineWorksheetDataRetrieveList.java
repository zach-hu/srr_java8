/*
 * Created on Dec. 6, 2005
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineWorksheetDataRetrieveList extends Task {
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
			PuridiomProcess process = processLoader.loadProcess("rfqline-worksheet-data-retrieve-by-id.xml");
			        
			List rfqLineList = (List) incomingRequest.get("rfqLineList");
	        for (Iterator it = rfqLineList.iterator(); it.hasNext(); ) {
					incomingRequest.put("rfqLine", (RfqLine) it.next());
					process.executeProcess(incomingRequest);
					this.setStatus(process.getStatus()) ;
					if (process.getStatus() == Status.FAILED) {
						break ;
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
