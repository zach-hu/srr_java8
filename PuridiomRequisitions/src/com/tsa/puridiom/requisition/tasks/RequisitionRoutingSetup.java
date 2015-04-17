package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

import java.util.ArrayList;
import java.util.Map;

public class RequisitionRoutingSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	userId = (String) incomingRequest.get("userId") ;
		
		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
		
        String appTypes = propertiesManager.getProperty("APPROVALS","Types","").trim().toUpperCase() ;
        String appEstimated = propertiesManager.getProperty("APPROVALS", "EstimatedCost", "N").toUpperCase();
		String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
		RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		
		ArrayList approverList = new ArrayList() ;

		incomingRequest.put("appTypes",appTypes) ;
		incomingRequest.put("appEstimated",appEstimated) ;
		incomingRequest.put("approverList",approverList);
		
		if (Utility.isEmpty(icReqHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("Schedule_icHeader",icReqHeader) ;
			incomingRequest.put("BillTo_icHeader",icReqHeader) ;
			incomingRequest.put("ShipTo_icHeader",icReqHeader) ;
			incomingRequest.put("ApprovalLog_icHeader",icReqHeader) ;
			incomingRequest.put("Account_icHeader",icReqHeader) ;
			incomingRequest.put("DocComment_icHeader",icReqHeader) ;
			incomingRequest.put("DocAttachment_icHeader",icReqHeader) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icReqHeader) ;
			
			if (requisitionHeader != null) {
			    incomingRequest.put("formCurrencyCode", requisitionHeader.getCurrencyCode());
			}
		}
       
	
		return null ;
	}
}
