/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Asset;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetItemSetSerialNumbers extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return an Asset_serialNumber list
	 *
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetList = new ArrayList();
		try
		{
			assetList = (List) incomingRequest.get("assetList");
			String Asset_serialNumber[] = (String[]) incomingRequest.get("Asset_serialNumber");

			for (int i=0; i<assetList.size(); i++)
			{
				Asset asset = (Asset) assetList.get(i);
				asset.setSerialNumber(Asset_serialNumber[i]);
				assetList.set(i,asset);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetList;
	}
}
