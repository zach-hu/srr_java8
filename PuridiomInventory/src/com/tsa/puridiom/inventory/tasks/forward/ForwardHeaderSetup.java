/*
 * Created on Dec 3, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventory.forwardForwardHeaderSetup.java
 */
 
package com.tsa.puridiom.inventory.tasks.forward;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ForwardHeaderSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		DisbHeader disb = null;
		try
		{
			Map incomingRequest = (Map)object;
			disb = (DisbHeader) incomingRequest.get("disbHeader");
			disb.setStatus(DocumentStatus.INV_DISBURSED);
			String icReqHeader = disb.getIcReqHeader().toString();
			incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
			incomingRequest.put("RequisitionLine_icReqHeader", icReqHeader);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return disb;
	}

}
