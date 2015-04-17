/*
 * Created on August 5, 2004
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineRetrieveListFromReqLineList extends Task {
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

			List reqLineList = (List) incomingRequest.get("requisitionLineList");
			int	ii = 0;
	        for (Iterator it = reqLineList.iterator(); it.hasNext(); ) {
	        	RequisitionLine reqLine = (RequisitionLine) it.next();
	        	Map requestParameters = new HashMap();
	        	requestParameters.put("userId", incomingRequest.get("userId"));
	        	requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("ReceiptLine_icReqLine", String.valueOf(reqLine.getIcReqLine()));
				process.executeProcess(requestParameters);

				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
				List receiptLineList = (List) requestParameters.get("receiptLineList");
				reqLine.setReceiptLineList(receiptLineList);
				reqLineList.set(ii, reqLine);
				ii++;
	        }

	        result = reqLineList;
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
