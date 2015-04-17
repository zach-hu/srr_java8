/**
 *
 * Created on Jan 27, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.tests.PoCreateFromRequisitionTest.java
 *
 */
package com.tsa.puridiom.handlers.test.asset;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.AssetGetTrackingNumberHandler;
import com.tsagate.foundation.utility.Log;

public class AssetGetTrackingNumberTest
{
	public static void main(String[] args)
	{
		try
		{
			AssetGetTrackingNumberHandler test = new AssetGetTrackingNumberHandler();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "TEST");
			incomingRequest.put("userId", "RRAMOS");
			incomingRequest.put("Asset_fiscalYear", "2006");


			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("GetNumber - SUCCESS");
			}
		}
		catch (Exception e)
		{
			Log.error("AssetGetTrackingNumber", e.toString());
		}
	}
}
