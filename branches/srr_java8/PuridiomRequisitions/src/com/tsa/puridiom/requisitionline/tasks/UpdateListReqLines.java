/*
 * Created on Dec 3, 2003
 * @author renzo
 * com.tsa.puridiom.requisitionline.tasks.UpdateListReqLines.java
 */

package com.tsa.puridiom.requisitionline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;


public class UpdateListReqLines extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List reqLines = (List)incomingRequest.get("reqLines");
		for (Iterator iter = reqLines.iterator(); iter.hasNext();)
		{
			RequisitionLine reqLine = (RequisitionLine) iter.next();
			incomingRequest.put("requisitionLine", reqLine);
			RequisitionLineUpdate upd = new RequisitionLineUpdate();
			upd.executeTask(incomingRequest);
			this.setStatus(upd.getStatus());
			if(this.getStatus() != Status.SUCCEEDED)
			{
				return null;
			}
		}
		this.setStatus(Status.SUCCEEDED);
		return reqLines;
	}

}
