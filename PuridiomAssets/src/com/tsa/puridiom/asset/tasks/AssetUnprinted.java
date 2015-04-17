package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.tsa.puridiom.entity.Asset;
import java.util.ArrayList;

public class AssetUnprinted extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List newAssetList = new ArrayList();
		try
		{
			//newAssetList = (List) incomingRequest.get("newAssetList");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			String quantity = (String) incomingRequest.get("qty");
			for (int i=0; i< Integer.valueOf(quantity).intValue();i++)
			{
				String tag = "Asset_tagNumber"+String.valueOf(i);
				String tagNumber = (String) incomingRequest.get(tag);
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("Asset_tagNumber",tagNumber);
				newIncomingRequest.put("organizationId",organizationId);
				newIncomingRequest.put("userId",userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("asset-retrieve-by-id-barcode.xml");

				process.executeProcess(newIncomingRequest);

				Asset asset = (Asset) newIncomingRequest.get("asset");
				Log.debug(this, "Asset: " + asset);

				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				asset.setPrinted("P");
				dbs.update(asset);
				newAssetList.add(asset);

			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return newAssetList;
	}

}