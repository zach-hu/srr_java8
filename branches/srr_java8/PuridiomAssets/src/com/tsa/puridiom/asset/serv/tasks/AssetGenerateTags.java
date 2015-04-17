/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Asset;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetGenerateTags extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 *@author EDSAC
	 *return an asset list with trackingNumber

	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetList = new ArrayList();
		try
		{
			assetList = (List) incomingRequest.get("assetList");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");


			for (int i=0; i<assetList.size(); i++)
			{
				Asset asset = (Asset) assetList.get(i);
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("Asset_fiscalYear", "2006");
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("userId", userId);
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("asset-get-tracking-number.xml");
				process.executeProcess(newIncomingRequest);
				String trackNumber = (String) newIncomingRequest.get("Asset_trackingNumber");
				Log.debug(this, "TrackingNumber: " + trackNumber);
				asset.setTrackingNumber(trackNumber);
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
