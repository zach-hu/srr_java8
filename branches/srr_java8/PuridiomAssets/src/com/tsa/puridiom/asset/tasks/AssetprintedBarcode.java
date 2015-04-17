package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.util.List;
import java.util.Map;
import com.tsa.puridiom.entity.Asset;
import java.util.ArrayList;

public class AssetprintedBarcode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List AssetList = new ArrayList();
		try
		{
			//newAssetList = (List) incomingRequest.get("newAssetList");
			String organizationId = (String) incomingRequest.get("organizationId");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("asset-retrieve-by-id-barcode.xml");
			process.executeProcess(incomingRequest);

			Asset asset = (Asset)incomingRequest.get("asset");
			if (asset == null)
			{
				throw new Exception ("Asset was not found.");
			}
			Log.debug(this, "Asset: " + asset);

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			asset.setPrinted("P");
			dbs.update(asset);
			AssetList.add(asset);
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return AssetList;
	}

}