package com.tsa.puridiom.invoice.approvals.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

import java.util.ArrayList;
import java.util.Map;

public class InvoiceRoutingSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;

        String appTypes = propertiesManager.getProperty("APPROVALS","Types","").trim().toUpperCase() ;
        String appEstimated = propertiesManager.getProperty("APPROVALS", "EstimatedCost", "N").toUpperCase();
		String icHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");

		ArrayList approverList = new ArrayList() ;

		incomingRequest.put("appTypes",appTypes) ;
		incomingRequest.put("appEstimated",appEstimated) ;
		incomingRequest.put("approverList",approverList);

		if (Utility.isEmpty(icHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("Schedule_icHeader",icHeader) ;
			incomingRequest.put("BillTo_icHeader",icHeader) ;
			incomingRequest.put("ShipTo_icHeader",icHeader) ;
			incomingRequest.put("ApprovalLog_icHeader",icHeader) ;
			incomingRequest.put("Account_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("InvoiceLine_icIvcHeader",icHeader) ;
			incomingRequest.put("approvalFormType", "IVC");

			if (invoiceHeader != null) {
			    incomingRequest.put("formCurrencyCode", invoiceHeader.getCurrencyCode());
			}
		}


		return null ;
	}
}
