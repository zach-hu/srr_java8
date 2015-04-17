package com.tsa.puridiom.requisitionline.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineSetupItemLocation extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		this.setStatus(Status.COMPLETED);
		
		RequisitionHeader  requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		
		if (requisitionHeader != null)
		{
			incomingRequest.put("RequisitionLine_itemLocation", requisitionHeader.getItemLocation());
		}
		
		return null;
	}
}