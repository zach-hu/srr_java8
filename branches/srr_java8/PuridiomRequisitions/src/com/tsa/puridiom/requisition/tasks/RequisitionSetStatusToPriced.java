package com.tsa.puridiom.requisition.tasks;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import java.util.List;

import java.util.Map;

public class RequisitionSetStatusToPriced extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	userId = (String) incomingRequest.get("userId") ;
		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
		
		RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
		List reqLineList = (List) incomingRequest.get("requisitionLineList") ;
		
		rqh.setStatus(DocumentStatus.RFQ_PRICED);
		
		for (int i=0; i < reqLineList.size(); i++) {
			RequisitionLine rql = (RequisitionLine) reqLineList.get(i);
			rql.setStatus(DocumentStatus.RFQ_PRICED) ;
		}
		
		return null ;
	}
}
