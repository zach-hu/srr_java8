/*
 * Created on Dec 2, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.disblineDisbLineListUpdate.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.util.*;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;


public class DisbLineListUpdate extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List disbLines = (List)incomingRequest.get("disbLines");
		for(int i = 0; i < disbLines.size(); i++)
		{
			DisbLine disb = (DisbLine)disbLines.get(i);
			incomingRequest.put("disbLine", disb);
			DisbLineUpdate upd = new DisbLineUpdate();
			upd.executeTask(incomingRequest);
			this.setStatus(upd.getStatus());
			if(this.getStatus() != Status.SUCCEEDED)
			{
				return null;
			}
		}
		
		return disbLines;
	}

}
