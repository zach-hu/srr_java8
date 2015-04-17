package com.tsa.puridiom.approvals;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AppRuleAddToCatalog extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
			String extraSubjectFYI = HiltonUtility.ckNull((String)incomingRequest.get("extraSubjectFYI"));
			String addToCatalog = "N";

			for (int i=0; i<requisitionLineList.size(); i++)
			{
				RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
				if (requisitionLine.getUdf1Code().equalsIgnoreCase("Y") && HiltonUtility.isEmpty(requisitionLine.getCatalogId()))
				{
					addToCatalog = "Y";
					extraSubjectFYI += " - An Item on this Requisition is being added to the Catalog	";
					incomingRequest.put("extraSubjectFYI", extraSubjectFYI);
					break;
				}
			}
			incomingRequest.put("addToCatalog", addToCatalog);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occurred at AppRuleAddToCatalog", e);
		}
		return result;
	}
}