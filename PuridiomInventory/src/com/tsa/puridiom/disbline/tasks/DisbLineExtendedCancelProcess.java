/*
 * Created on Dec 19, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbLineExtendedCancelProcess.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class DisbLineExtendedCancelProcess extends Task
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
			List disbLines = (List) incomingRequest.get("disbLines");
			for (Iterator iter = disbLines.iterator(); iter.hasNext();)
			{
				DisbLine disbLine = (DisbLine) iter.next();
				incomingRequest.put("disbLine", disbLine);
				incomingRequest.put("RequisitionLine_icReqLine", disbLine.getIcReqLine().toString());
				incomingRequest.put("InvBinLocation", disbLine.getIcRc());
				incomingRequest.put("InvLocation_itemNumber", disbLine.getItemNumber());
				incomingRequest.put("InvLocation_itemLocation", disbLine.getItemLocation());
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("disbursementline-extended-cancel-process.xml");
				process.executeProcess(incomingRequest);
				this.setStatus(process.getStatus());
				if(process.getStatus() != Status.SUCCEEDED)
				{
					break;
				}
			}
			
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
