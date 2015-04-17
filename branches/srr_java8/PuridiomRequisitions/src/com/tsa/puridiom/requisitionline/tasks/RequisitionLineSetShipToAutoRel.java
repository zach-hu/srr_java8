
package com.tsa.puridiom.requisitionline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineSetShipToAutoRel extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			RequisitionLineAutoReleaseObject autoReleaseObject = (RequisitionLineAutoReleaseObject)incomingRequest.get("autoReleaseObject");
			List shipToList = new ArrayList();
			shipToList.add(autoReleaseObject.getShipTo());
			incomingRequest.put("shipToList", shipToList);
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("ShipTo was not found!", e);
		}


		return null  ;
	}
}
