/*
 * Created on Dec 10, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbLineCancelSetup.java
 */

package com.tsa.puridiom.disbheader.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.DisbHeader;
//import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
//import com.tsa.puridiom.common.utility.HiltonUtility;


public class DisbHeaderCancelSetupStatus extends Task
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
			boolean bCancel = true;
			DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
			List disbLines = (List)incomingRequest.get("disbLines");

			for (Iterator iter = disbLines.iterator(); iter.hasNext();)
			{
				DisbLine disbLine = (DisbLine) iter.next();
				if(!disbLine.getStatus().equals(DocumentStatus.CANCELLED))
				{
					bCancel = false;
				}
			}

			if(bCancel)
			{
				disbHeader.setStatus(DocumentStatus.CANCELLED);
			}
			//String status = (String)incomingRequest.get("status");

			disbHeader.setStatus(DocumentStatus.CANCELLED);

			ret = disbHeader;

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
