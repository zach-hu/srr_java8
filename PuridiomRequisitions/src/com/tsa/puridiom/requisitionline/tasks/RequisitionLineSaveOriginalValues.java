package com.tsa.puridiom.requisitionline.tasks;
import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineSaveOriginalValues extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine") ;
		StringBuffer	desc = new StringBuffer("") ;

		incomingRequest.put("HistoryLog_docType","RQL") ;
		incomingRequest.put("HistoryLog_icHeader",(String) incomingRequest.get("RequisitionLine_icReqHeader")) ;
		incomingRequest.put("HistoryLog_icLineHistory",(String) incomingRequest.get("RequisitionLine_icHistory")) ;
		incomingRequest.put("HistoryLog_icHeaderHistory",(String) incomingRequest.get("RequisitionHeader_icHistory")) ;
		incomingRequest.put("HistoryLog_icLine",(String) incomingRequest.get("RequisitionLine_icReqLine")) ;
		
		if (! (rql == null)) {
			BigDecimal newQty = new BigDecimal((String)incomingRequest.get("RequisitionLine_quantity")) ;
			BigDecimal newTotal = new BigDecimal((String)incomingRequest.get("RequisitionLine_lineTotal")) ;
			String newStatus = (String) incomingRequest.get("RequisitionLine_status") ;
			
			if (! newStatus.equals(rql.getStatus())) {
				desc.append("Status changed from ") ;
				desc.append(rql.getStatus()) ;
				desc.append(" to ") ;
				desc.append(newStatus) ;
			}
			
			if (! newQty.equals(rql.getQuantity())) {
				desc.append("Quantity changed from ") ;
				desc.append(rql.getQuantity().toString()) ;
				desc.append(" to ") ;
				desc.append(newQty.toString()) ;
			}
			
			if (! newTotal.equals(rql.getLineTotal())) {
				desc.append("Total changed from ") ;
				desc.append(rql.getLineTotal().toString()) ;
				desc.append(" to ") ;
				desc.append(newTotal.toString()) ;
			}
		} else {
				desc.append("Added Line to requisition") ;
		}
		
		this.setStatus(Status.SUCCEEDED);
		
		return null ;
	}
}