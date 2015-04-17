/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.BillTo;
import com.tsa.puridiom.entity.BillToPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Administrator
 */
public class BillToCreateForAutoRelease extends Task
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

			BillTo billTo = new BillTo();
			BillToPK comp_id = new BillToPK();
			comp_id.setBillToCode(autoReleaseObject.getRequisitionHeader().getBillToCode());
			comp_id.setIcLine(autoReleaseObject.getIcReqLine());
			comp_id.setIcHeader(autoReleaseObject.getIcReqHeader());
			billTo.setComp_id(comp_id);
			billTo.setAttention(autoReleaseObject.getRequisitionHeader().getBillToContact());
			billTo.setQuantity(autoReleaseObject.getQuantity());
			result.add(billTo);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "BillToRetrieveByLine Failed: " + e.getMessage());
		}
		return result  ;
	}
}
