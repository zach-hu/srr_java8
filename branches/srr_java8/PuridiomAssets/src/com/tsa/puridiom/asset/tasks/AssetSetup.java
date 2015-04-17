package com.tsa.puridiom.asset.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class AssetSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

			incomingRequest.put("Asset_tagNumber", "");
			incomingRequest.put("Asset_assetClass", "");
			incomingRequest.put("Asset_securityCode", "");
			incomingRequest.put("Asset_commodity", "");
			incomingRequest.put("Asset_manufacturer", "");
			incomingRequest.put("Asset_model", "");
			incomingRequest.put("Asset_serialNumber", "");
			incomingRequest.put("Asset_assetCost", "0");
			incomingRequest.put("Asset_dateInService", Dates.today(userDateFormat, userTimeZone));
			incomingRequest.put("Asset_deprecMethod", "");
			incomingRequest.put("Asset_warrantyStart", Dates.today(userDateFormat, userTimeZone));
			incomingRequest.put("Asset_warrantyEnd", Dates.today(userDateFormat, userTimeZone));
			incomingRequest.put("Asset_upgradeReqs", "");
			incomingRequest.put("Asset_contractService", "");
			incomingRequest.put("Asset_purchaseOrder", "");
			incomingRequest.put("Asset_dateEntered", Dates.today(userDateFormat, userTimeZone));
			incomingRequest.put("Asset_assetStatus", "");
			incomingRequest.put("Asset_assetUdf1", "");
			incomingRequest.put("Asset_assetUdf2", "");
			incomingRequest.put("Asset_assetUdf3", "");
			incomingRequest.put("Asset_assetUdf4", "");
			incomingRequest.put("Asset_assetUdf5", "");
			incomingRequest.put("Asset_totalCost", "0");
			incomingRequest.put("Asset_icText", "0");
			incomingRequest.put("Asset_imageFile", "");
			incomingRequest.put("Asset_poVendor", "");
			incomingRequest.put("Asset_contractorName", "");
			incomingRequest.put("Asset_owner", "");
			incomingRequest.put("Asset_deprecTerm", "0");
			incomingRequest.put("Asset_dateReceived", Dates.today(userDateFormat, userTimeZone));
			incomingRequest.put("Asset_icReceipt", "0");
			incomingRequest.put("Asset_assetUdf6", "");
			incomingRequest.put("Asset_assetUdf7", "");
			incomingRequest.put("Asset_assetUdf8", "");
			incomingRequest.put("Asset_assetUdf9", "");
			incomingRequest.put("Asset_assetUdf10", "");
			incomingRequest.put("Asset_icLineKey", "0");
			incomingRequest.put("Asset_itemNumber", "");
			incomingRequest.put("Asset_itemLocation", "");
			incomingRequest.put("Asset_icAccount", "0");
			incomingRequest.put("Asset_icDsbHeader", "0");
			incomingRequest.put("Asset_icDsbLine", "0");
			incomingRequest.put("Asset_localCurrencyPrice", "0");
			incomingRequest.put("Asset_originalCost", "0");
			incomingRequest.put("Asset_exitValue", "0");
			incomingRequest.put("Asset_dateInactive", Dates.today(userDateFormat, userTimeZone));

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