/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.checks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;

public class AssetChecks
{
	private static HashMap properties = new HashMap();

	public static AssetChecks getInstance(String o)
	{
		if (AssetChecks.properties.get(o) == null)
		{
			AssetChecks.properties.put(o, new AssetChecks(o));
		}
		return (AssetChecks) AssetChecks.properties.get(o);
	}

	private AssetChecks(String o)
	{
		//this.load(o);
	}

	public boolean isPoItemsAsset(String oid, String poNumber)
	{
		boolean values = true;

		try
		{
		    Map incomingRequest = new HashMap() ;
			incomingRequest.put("organizationId", oid);
			incomingRequest.put("Asset_purchaseOrder", poNumber);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("asset-retrieve-by-po-number.xml");
			process.executeProcess(incomingRequest);

			List assetList = (List)incomingRequest.get("assetList");

			if(assetList.isEmpty())
				values = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return values;
	}

}
