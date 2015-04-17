/*
 * Created on November 16, 2006
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kathleen
 */
public class ReceiptLineRetrieveListFromInvoiceLineList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("receiptline-retrieve-by-poline.xml");

			List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
			int	ii = 0;
	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); ) {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();
	        	Map requestParameters = new HashMap();
	        	requestParameters.put("userId", incomingRequest.get("userId"));
	        	requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("ReceiptLine_icPoLine", String.valueOf(invoiceLine.getIcPoLine()));
				process.executeProcess(requestParameters);

				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
				List receiptLineList = (List) requestParameters.get("receiptLineList");
				invoiceLine.setReceiptLineList(receiptLineList);
				invoiceLineList.set(ii, invoiceLine);
				ii++;
	        }

	        result = invoiceLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}
