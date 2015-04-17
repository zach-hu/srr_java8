package com.tsa.puridiom.po.approvals.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

import java.util.ArrayList;
import java.util.Map;

public class PoRoutingSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;

        String appTypes = propertiesManager.getProperty("APPROVALS","Types","").trim().toUpperCase() ;
        String appEstimated = propertiesManager.getProperty("APPROVALS", "EstimatedCost", "N").toUpperCase();
		String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
		PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

		ArrayList approverList = new ArrayList() ;

		incomingRequest.put("appTypes",appTypes) ;
		incomingRequest.put("appEstimated",appEstimated) ;
		incomingRequest.put("approverList",approverList);

		if (Utility.isEmpty(icPoHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("Schedule_icHeader",icPoHeader) ;
			incomingRequest.put("BillTo_icHeader",icPoHeader) ;
			incomingRequest.put("ShipTo_icHeader",icPoHeader) ;
			incomingRequest.put("ApprovalLog_icHeader",icPoHeader) ;
			incomingRequest.put("Account_icHeader",icPoHeader) ;
			incomingRequest.put("DocComment_icHeader",icPoHeader) ;
			incomingRequest.put("DocAttachment_icHeader",icPoHeader) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icPoHeader) ;

			if (poHeader != null) {
			    incomingRequest.put("formCurrencyCode", poHeader.getCurrencyCode());
			}
		}


		return null ;
	}
}
