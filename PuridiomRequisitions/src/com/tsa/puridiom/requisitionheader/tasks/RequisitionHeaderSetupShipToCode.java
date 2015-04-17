package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * Sets the value of ShipToCode the ItemLocation RequisitionLine
 * 
 * @author Alexander
 *
 */
public class RequisitionHeaderSetupShipToCode extends Task {
	
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		this.setStatus(Status.SUCCEEDED);
		
		RequisitionLine  requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
		
		if (requisitionLine != null)
		{
			incomingRequest.put("RequisitionHeader_shipToCode", requisitionLine.getItemLocation());
		}
		
		return null;
	}
}