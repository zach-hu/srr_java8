package com.tsa.puridiom.assetcost.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetCostListAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List assetCostList = new ArrayList();

		try
		{
			assetCostList = (List)incomingRequest.get("assetCostList");

			Map newIncomingRequest = new HashMap();
			String organizationId = (String) incomingRequest.get("organizationId");
			newIncomingRequest.put("userId", (String) incomingRequest.get("userId"));
			newIncomingRequest.put("organizationId", organizationId);

			for (int i=0; i<assetCostList.size(); i++) {
				AssetCost assetCost = (AssetCost) assetCostList.get(i);
				newIncomingRequest.put("assetCost",assetCost);
				newIncomingRequest.put("asset",incomingRequest.get("asset"));
				newIncomingRequest.put("AssetCost_tagNumber",assetCost.getComp_id().getTagNumber());

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("assetcost-add.xml");
				process.executeProcess(newIncomingRequest);
				this.setStatus(Status.SUCCEEDED);
				/*AssetCostAdd addTask = new AssetCostAdd();
				assetCost = (AssetCost) addTask.executeTask(newIncomingRequest);
				this.setStatus(addTask.getStatus()) ;*/
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return assetCostList;
	}

}