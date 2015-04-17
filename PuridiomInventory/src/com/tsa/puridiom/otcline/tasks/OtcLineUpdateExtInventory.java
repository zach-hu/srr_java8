/*
 * Created on Jun 30, 2004
 *
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.otcline.tasks.OtcLineUpdateExtInventory.java
 *
 */
package com.tsa.puridiom.otcline.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class OtcLineUpdateExtInventory extends Task
{

	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			List otcLineList = (List)incomingRequest.get("disbLines");
			DisbHeader header = (DisbHeader)incomingRequest.get("disbHeader");
			for (Iterator iter = otcLineList.iterator(); iter.hasNext();)
			{
				DisbLine	disbLine = (DisbLine) iter.next();
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("otcline-update-ext-inventory-by-line.xml");

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("InvBinLocation_icRc", disbLine.getIcRc().toString());
				updateParameters.put("InvLocation_itemNumber", disbLine.getItemNumber());
				updateParameters.put("InvLocation_itemLocation", disbLine.getItemLocation());
				updateParameters.put("disbLine", disbLine);
				updateParameters.put("disbHeader", header);

				process.executeProcess(updateParameters);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
