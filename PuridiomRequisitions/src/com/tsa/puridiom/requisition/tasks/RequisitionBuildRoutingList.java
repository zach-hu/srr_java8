package com.tsa.puridiom.requisition.tasks;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionBuildRoutingList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;

		RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
		List routingList = (List)incomingRequest.get("routingList") ;

    	this.setStatus(Status.SUCCEEDED);

		incomingRequest.put("Department_departmentCode",rqh.getDepartmentCode()) ;
		incomingRequest.put("requisitionerCode",rqh.getRequisitionerCode()) ;

		BigDecimal amtToApprove = null ;
		if (propertiesManager.getProperty("APPROVALS","EstimatedCost","N").equalsIgnoreCase("Y")) {
			amtToApprove = rqh.getEstimatedCost() ;
		} else {
			amtToApprove = rqh.getTotal() ;
		}

		incomingRequest.put("amtToApprove",amtToApprove) ;
//        if (requisitionStatus.equals("02")) {
//            /* Clear the log IF rejected */
//            String reset = propertiesManager.getProperty("APPROVALS","Rebuild Routing","Y").trim().toUpperCase() ;
//
//            if (reset.equals("N") && routingList.size() > 0) {
//                return routingList ;
//            }
//        } else {
//        	/* Delete Routing List */
//        	routingList.clear() ;
//			ApprovalLogDeleteByHeader apdel = new ApprovalLogDeleteByHeader() ;
//			apdel.executeTask(incomingRequest) ;
//        }

		return routingList ;
	}

}
