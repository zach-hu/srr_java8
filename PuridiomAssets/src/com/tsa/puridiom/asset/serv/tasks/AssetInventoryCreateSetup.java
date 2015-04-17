/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class AssetInventoryCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return the asset information, date and the userId that made this changes
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Asset asset = new Asset();
		try
		{
			InvItem invItem = (InvItem) incomingRequest.get("invItem");

			String userId = (String) incomingRequest.get("userId");
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String tagNumber = ukg.getUniqueKey().toString();
			asset.setTagNumber(tagNumber);
			asset.setLastChgBy(userId);
			asset.setDateEntered(new Date());
			asset.setDateChanged(new Date());
			asset.setCommodity(invItem.getCommodity());
			asset.setItemNumber(invItem.getItemNumber());
			asset.setAssetCost(invItem.getIssueCost());
			asset.setDescription(invItem.getDescription());
			asset.setAssetStatus("Inventory");
			asset.setAssetClass("Tangible");
			asset.setTrackingNumber("N/A");
			asset.setPrinted("N");
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return asset;
	}
}
