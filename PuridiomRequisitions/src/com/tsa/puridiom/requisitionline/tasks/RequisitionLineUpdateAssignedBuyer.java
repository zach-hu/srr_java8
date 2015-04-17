package com.tsa.puridiom.requisitionline.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.requisitionheader.tasks.RequisitionHeaderRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineUpdateAssignedBuyer extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result  = null;

		try
		{
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			String icReqHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader");
			DBSession dbsession = (DBSession)incomingRequest.get("dbsession");

			Map reqHeaderMap = new HashMap();
			reqHeaderMap.put("dbsession", dbsession);
			reqHeaderMap.put("RequisitionHeader_icReqHeader", icReqHeader);

			RequisitionHeaderRetrieveById reqHeaderRetrieve = new RequisitionHeaderRetrieveById();
			RequisitionHeader requisitionHeader = (RequisitionHeader)reqHeaderRetrieve.executeTask(reqHeaderMap);
			incomingRequest.put("RequisitionLine_assignedBuyer", requisitionHeader.getAssignedBuyer());

			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
