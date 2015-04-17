/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.ShipTo;
import com.tsa.puridiom.entity.ShipToPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Administrator
 */
public class ShipToCreateForAutoRelease extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();
		try
		{
			RequisitionLineAutoReleaseObject autoReleaseObject = (RequisitionLineAutoReleaseObject)incomingRequest.get("autoReleaseObject");

			ShipTo shipto = new ShipTo();
			ShipToPK comp_id = new ShipToPK();
			ShipTo lineShipTo = autoReleaseObject.getShipTo();
			if(lineShipTo == null)
			{
				comp_id.setShipToCode(autoReleaseObject.getRequisitionHeader().getShipToCode());
				comp_id.setShipToPriority("STANDARD");
				shipto.setShipDate(autoReleaseObject.getRequisitionHeader().getRequiredDate());
			}
			else
			{
				comp_id.setShipToCode(lineShipTo.getShipToCode());
				comp_id.setShipToPriority(lineShipTo.getComp_id().getShipToPriority());
				shipto.setCodeType(lineShipTo.getCodeType());
				shipto.setShipDate(autoReleaseObject.getShipTo().getShipDate());
			}
			comp_id.setIcLine(autoReleaseObject.getIcReqLine());
			comp_id.setIcHeader(autoReleaseObject.getIcReqHeader());
			shipto.setComp_id(comp_id);
			shipto.setAttention(autoReleaseObject.getRequisitionHeader().getShipAttn());
			shipto.setQuantity(autoReleaseObject.getQuantity());


			result.add(shipto);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "ShipToRetrieveByLine Failed: " + e.getMessage());
		}
		return result  ;
	}
}
