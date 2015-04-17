package com.tsa.puridiom.disbline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.invbinlocation.tasks.InvBinLocationRetrieveById;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class DisbLineListRetrieveInvBinLocationList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List disbLineList = (List) incomingRequest.get("disbLineList");
			List invBinLocationList = new ArrayList();

			for (int i = 0; i < disbLineList.size(); i++) {
				DisbLine disbLine = (DisbLine)disbLineList.get(i);
				
				InvBinLocationRetrieveById retrieve = new InvBinLocationRetrieveById();
				incomingRequest.put("InvBinLocation_icRc", disbLine.getIcRc().toString());
				
				InvBinLocation invBinLocation = (InvBinLocation)retrieve.executeTask(incomingRequest);
				invBinLocationList.add(invBinLocation);
			}
			
			result = invBinLocationList;
			
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