/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetPoAdd extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * add poLine list that were selected like assets
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			Map newIncomingRequest = new HashMap();
			String organizationId = (String) incomingRequest.get("organizationId");
			newIncomingRequest.put("userId", (String) incomingRequest.get("userId"));
			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("poHeader", (PoHeader) incomingRequest.get("poHeader"));
			List poLineList = (List) incomingRequest.get("poLineList");
			for(int i=0; i<poLineList.size(); i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				newIncomingRequest.put("poLine", poLine);
				if (poLine.getAsset().equalsIgnoreCase("Y"))
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("assetreceiptline-add.xml");
					process.executeProcess(newIncomingRequest);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}