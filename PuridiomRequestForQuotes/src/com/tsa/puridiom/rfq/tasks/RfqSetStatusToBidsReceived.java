package com.tsa.puridiom.rfq.tasks;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import java.util.List;

import java.util.Map;

public class RfqSetStatusToBidsReceived extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	userId = (String) incomingRequest.get("userId") ;
		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
		
		RfqHeader rfh = (RfqHeader)incomingRequest.get("rfqHeader") ;
		List rfqLineList = (List) incomingRequest.get("rfqLineList") ;
		
		rfh.setStatus(DocumentStatus.RFQ_PURCHASING);
		
		for (int i=0; i < rfqLineList.size(); i++) {
			RfqLine rfl = (RfqLine) rfqLineList.get(i);
			rfl.setStatus(DocumentStatus.RFQ_PURCHASING) ;
		}
		
		return null ;
	}
}
