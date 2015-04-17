package com.tsa.puridiom.asset.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class AssetSetValuesPK
{
	public void setValues(Map incomingRequest, Asset asset ) throws Exception
	{
		try
		{
			String tagNumber = (String ) incomingRequest.get("Asset_tagNumber");
			asset.setTagNumber(tagNumber);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}