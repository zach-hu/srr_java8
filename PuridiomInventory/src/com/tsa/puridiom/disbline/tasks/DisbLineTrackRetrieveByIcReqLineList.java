package com.tsa.puridiom.disbline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class DisbLineTrackRetrieveByIcReqLineList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List disbLineList = (List) incomingRequest.get("disbLineList");
			List disbLineTrackList = new ArrayList();

			for (int i = 0; i < disbLineList.size(); i++) {
				DisbLine disbLine = (DisbLine)disbLineList.get(i);
				
				DisbLineRetrieveByReqLine retrieve = new DisbLineRetrieveByReqLine();
				incomingRequest.put("DisbLine_icReqLine", disbLine.getIcReqLine().toString());
				
				List disbLineTrack = (List)retrieve.executeTask(incomingRequest);
				disbLineTrackList.add(disbLineTrack);
			}
			
			result = disbLineTrackList;
			
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