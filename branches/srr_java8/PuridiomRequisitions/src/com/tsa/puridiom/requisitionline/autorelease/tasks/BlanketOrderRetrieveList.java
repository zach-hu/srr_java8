package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class BlanketOrderRetrieveList extends Task
{
	public Map getGroup(Map incomingRequest)
	{
		return (Map)incomingRequest.get("groupByOrder");
	}

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		Map blanketList = new HashMap();
		try
		{
			Map groupByOrder = this.getGroup(incomingRequest);
			Set keysSet = groupByOrder.keySet();
			for (Iterator iter = keysSet.iterator(); iter.hasNext();)
			{
				String blanketNumber = (String) iter.next();
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));

                PuridiomProcess process = processLoader.loadProcess("blanket-retrieve-by-number.xml");
                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("PoHeader_poNumber", blanketNumber);
                process.executeProcess(newIncomingRequest);
                if(process.getStatus() == Status.SUCCEEDED)
                {
                	PoHeader blanket = (PoHeader)newIncomingRequest.get("poHeader");
                	blanketList.put(blanketNumber, blanket);
                }

			}
			ret = blanketList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("BlanketOrderRetrieveList failed to obtain Blanket List: " + e.getMessage(), e);
		}

		return ret;
	}
}
