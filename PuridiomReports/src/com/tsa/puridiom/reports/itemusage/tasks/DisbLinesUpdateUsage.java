/*
 * Created on Jun 2, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisbLinesUpdateUsage extends Task
{

	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			List disbLines = (List)incomingRequest.get("disbLines");
			for (Iterator iter = disbLines.iterator(); iter.hasNext();)
			{
				DisbLine	disbLine = (DisbLine) iter.next();
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("itemusage-update-usage.xml");
				incomingRequest.put("disbLine", disbLine);
				process.executeProcess(incomingRequest);
				if (process.getStatus() != Status.SUCCEEDED)
				{
					this.setStatus(process.getStatus());
					throw new TsaException("Error ocurred updating Inventory Usage!");
				}
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
