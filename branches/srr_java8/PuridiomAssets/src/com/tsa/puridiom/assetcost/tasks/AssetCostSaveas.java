package com.tsa.puridiom.assetcost.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AssetCostSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain assetCost */
			AssetCostPK comp_id = new AssetCostPK();
			AssetCost	originalAssetCost = (AssetCost) incomingRequest.get("assetCost");
			AssetCost	assetCost = new AssetCost();

			comp_id.setTagNumber(originalAssetCost.getComp_id().getTagNumber());
			comp_id.setSequenceNo(originalAssetCost.getComp_id().getSequenceNo());
			assetCost.setAmount(originalAssetCost.getAmount());
			assetCost.setExtendLifeFlag(originalAssetCost.getExtendLifeFlag());
			assetCost.setDateEntered(originalAssetCost.getDateEntered());
			assetCost.setDateReceived(originalAssetCost.getDateReceived());
			assetCost.setPoNumber(originalAssetCost.getPoNumber());
			assetCost.setPoVendor(originalAssetCost.getPoVendor());
			assetCost.setDescription(originalAssetCost.getDescription());
			assetCost.setDateChanged(originalAssetCost.getDateChanged());
			assetCost.setLastChgBy(originalAssetCost.getLastChgBy());
			assetCost.setComp_id(comp_id);

			incomingRequest.put("assetCost", assetCost);

			AssetCostAdd addTask = new AssetCostAdd();
			assetCost = (AssetCost) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = assetCost;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}