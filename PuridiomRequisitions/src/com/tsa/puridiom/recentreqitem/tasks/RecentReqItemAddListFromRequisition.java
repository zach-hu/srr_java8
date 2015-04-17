package com.tsa.puridiom.recentreqitem.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentReqItemAddListFromRequisition extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		    List	lineList = (List) incomingRequest.get("requisitionLineList");
		    
		    if (lineList == null) {
		        throw new Exception ("Recent Requisition Items cannot be added.  RequisitionLineList was not found.");
		    }
		    
		    Map requestParameters = new HashMap();
		    
		    requestParameters.put("dbsession", incomingRequest.get("dbsession"));
		    requestParameters.put("userId", incomingRequest.get("userId"));
		    requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		    requestParameters.put("organizationId", incomingRequest.get("organizationId"));
		    requestParameters.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
		    
		    for (int i = 0; i < lineList.size(); i++) {
		        RequisitionLine requisitionLine = (RequisitionLine) lineList.get(i);
		        if (!Utility.isEmpty(requisitionLine.getItemNumber())) {
			        requestParameters.put("requisitionLine", requisitionLine);
			        
			        PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("recentreqitem-add.xml");
	
					process.executeProcess(requestParameters);
					
					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("RecentReqItem Add process failed.");
					}
		        }
		    }
		    
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}