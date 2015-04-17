package com.tsa.puridiom.po.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoCreateFromReqCheckSuppliers extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");

			Map vendorIds = new HashMap();

			if (requisitionHeader != null && !HiltonUtility.isEmpty(requisitionHeader.getVendorId()))
			{
				vendorIds.put(requisitionHeader.getVendorId(), requisitionHeader.getVendorId());
			}

			if (requisitionLineList != null && requisitionLineList.size() > 0)
			{
				for (int i = 0; i < requisitionLineList.size(); i++)
				{
					RequisitionLine requisitionLine = (RequisitionLine)requisitionLineList.get(i);
					if (requisitionLine != null && !HiltonUtility.isEmpty(requisitionLine.getVendorId()))
					{
						vendorIds.put(requisitionLine.getVendorId(), requisitionLine.getVendorId());
					}
				}
			}

			String hasRequisitionSeveralVendors = "N";
			if (vendorIds.size() > 1)
			{
				hasRequisitionSeveralVendors = "Y";
			}

			incomingRequest.put("hasRequisitionSeveralVendors", hasRequisitionSeveralVendors);

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
