package com.tsa.puridiom.requisitionline.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;
public class RequisitionLineUpdateHistory extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest= (Map) object;
		Object result= null;
		try
		{
			List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			for (int i= 0; i < requisitionLineList.size(); i++)
			{
				RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
				Map updateParameters= new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("requisitionLine", requisitionLine);
				updateParameters.put("requisitionHeader", reqHeader);
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
				PuridiomProcess process = processLoader.loadProcess("requisitionline-add-history.xml") ;
				process.executeProcess(updateParameters);
				
				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("RequisitionLineHistoryUpdate failed.");
				}
			}
			result= requisitionLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}