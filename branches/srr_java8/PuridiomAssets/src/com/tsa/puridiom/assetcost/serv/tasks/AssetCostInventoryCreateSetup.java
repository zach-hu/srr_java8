/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetcost.serv.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetCostInventoryCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		AssetCost assetCost = new AssetCost();
		try
		{
			InvItem invItem = (InvItem) incomingRequest.get("invItem");
			Asset asset = (Asset) incomingRequest.get("asset");
			String userId = (String) incomingRequest.get("userId");
			AssetCostPK assetCostPK = new AssetCostPK();
			assetCostPK.setTagNumber(asset.getTagNumber());
			assetCostPK.setSequenceNo(new BigDecimal(1));
			assetCost.setLastChgBy(userId);
			assetCost.setDateEntered(new Date());
			assetCost.setDateChanged(new Date());
			assetCost.setAmount(invItem.getCost());
			assetCost.setComp_id(assetCostPK);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetCost;
	}
}
