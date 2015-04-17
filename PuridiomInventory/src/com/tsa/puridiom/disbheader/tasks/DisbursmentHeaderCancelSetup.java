/*
 * Created on Dec 10, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbLineCancelSetup.java
 */

package com.tsa.puridiom.disbheader.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.utility.HiltonUtility;


public class DisbursmentHeaderCancelSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");

			String canceltype = HiltonUtility.ckNull((String) incomingRequest.get("canceltype"));

			String status = (String)incomingRequest.get("RequisitionLine_status");

			if(!HiltonUtility.isEmpty(canceltype) && (canceltype.equalsIgnoreCase("line") || canceltype.equalsIgnoreCase("header") ) )
			{
				incomingRequest.put("RequisitionLine_status", status);

				if(!status.equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
				{
					disbLine.setStatus(DocumentStatus.CANCELLED);
				}
			}
			else
			{
				incomingRequest.put("RequisitionLine_status", DocumentStatus.CANCELLED);
				disbLine.setStatus(DocumentStatus.CANCELLED);
			}

			incomingRequest.put("InvLocation_itemNumber", disbLine.getItemNumber());
			incomingRequest.put("InvLocation_itemLocation", disbLine.getItemLocation());
			incomingRequest.put("RequisitionLine_icReqLine", disbLine.getIcReqLine().toString());
			incomingRequest.put("DisbLine_icDsbHeader", disbLine.getIcDsbHeader().toString());
			incomingRequest.put("DisbHeader_icDsbHeader", disbLine.getIcDsbHeader().toString());
			//extended-inventory
			incomingRequest.put("InvBinLocation_icRc", disbLine.getIcRc());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		finally
		{
			return ret;
		}
	}
}
