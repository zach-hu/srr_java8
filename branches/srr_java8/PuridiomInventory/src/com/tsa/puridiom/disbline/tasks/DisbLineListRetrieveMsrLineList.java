package com.tsa.puridiom.disbline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.requisitionline.tasks.RequisitionLineRetrieveByMsr;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class DisbLineListRetrieveMsrLineList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List disbLineList = (List) incomingRequest.get("disbLineList");
			List msrLineList = new ArrayList();

			for (int i = 0; i < disbLineList.size(); i++) {
				DisbLine disbLine = (DisbLine)disbLineList.get(i);

				RequisitionLineRetrieveByMsr retrieve = new RequisitionLineRetrieveByMsr();
				incomingRequest.put("RequisitionLine_icLineHistory", disbLine.getIcLineHistory().toString());

				RequisitionLine msrLine = (RequisitionLine)retrieve.executeTask(incomingRequest);
				msrLineList.add(msrLine);
			}

			result = msrLineList;

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