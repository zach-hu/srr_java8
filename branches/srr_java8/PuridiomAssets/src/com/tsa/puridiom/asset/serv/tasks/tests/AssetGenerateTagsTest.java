package com.tsa.puridiom.asset.serv.tasks.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.asset.serv.tasks.AssetGenerateTags;
import com.tsa.puridiom.entity.Asset;

public class AssetGenerateTagsTest
{
	public static void main(String[] args)
	{
		AssetGenerateTags tags = new AssetGenerateTags();
		Map incomingRequest = new HashMap();
		try
		{
			List assetList = new ArrayList();
			assetList.add(new Asset());
			incomingRequest.put("assetList", assetList);
			incomingRequest.put("organizationId", "TEST");
			incomingRequest.put("userId", "RRAMOS");


			tags.executeTask(incomingRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
