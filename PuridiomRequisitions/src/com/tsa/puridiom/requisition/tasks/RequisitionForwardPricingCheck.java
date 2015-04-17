package com.tsa.puridiom.requisition.tasks;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;

public class RequisitionForwardPricingCheck extends Task	{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;

		 if (rqh != null) {
		 	String reqType = rqh.getRequisitionType();
		 	//String reqStatus = (String) incomingRequest.get("newStatus") ;
		 	String reqStatus = rqh.getStatus();
		 	String budStatus = (String) incomingRequest.get("budStatus") ;

		 	if (reqStatus.equals(DocumentStatus.RFQ_PRICED)) {
		 		String pricingAction = (String) incomingRequest.get("pricingAction");
		 		if(pricingAction.equalsIgnoreCase("repricing")) {
		 			reqStatus = DocumentStatus.REQ_FORWARDED ;
		 		} else {
		 			reqType = "P";
		 			rqh.setRequisitionType(reqType) ;
		 			budStatus = "00";

		 			List reqLineList = (List) incomingRequest.get("requisitionLineList") ;

		            for (int i=0; i < reqLineList.size(); i++) {
		                RequisitionLine rql = (RequisitionLine) reqLineList.get(i);
		                rql.setReqType(reqType);
		            }
		 		}
		 	} else {
		 		reqStatus = DocumentStatus.REQ_FORWARDED ;
		 	}
		 	incomingRequest.put("newStatus",reqStatus) ;
		 	incomingRequest.put("budStatus",budStatus) ;
		 }

		return null ;
	}
}
