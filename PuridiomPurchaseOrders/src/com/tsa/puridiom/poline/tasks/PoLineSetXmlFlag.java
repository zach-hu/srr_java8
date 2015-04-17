/**
 * Created on Feb 25, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoLineForwardSetLineStatus.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineSetXmlFlag extends Task
{
	
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String		xmlOrder = "N";
		try
		{
			List poLines = (List)incomingRequest.get("poLineList");
			for(int i = 0; i < poLines.size(); i++)
			{
				PoLine poLine = (PoLine)poLines.get(i);
				if (poLine.getItemSource().startsWith("XM")) {
					xmlOrder = "Y" ;
					break ;
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return xmlOrder ;
	}

}
