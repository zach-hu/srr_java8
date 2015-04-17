package com.tsa.puridiom.assetcost.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AssetCostSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

			AssetCostPK comp_id = new AssetCostPK();
			AssetCost assetCost = (AssetCost) incomingRequest.get("assetCost");
			if (assetCost == null)
			{
				assetCost = new AssetCost();
			}

			if (incomingRequest.containsKey("AssetCost_tagNumber"))
			{
				String tagNumber = (String ) incomingRequest.get("AssetCost_tagNumber");
				comp_id.setTagNumber(tagNumber);
			}
			if (incomingRequest.containsKey("AssetCost_sequenceNo"))
			{
				String sequenceNoString = (String) incomingRequest.get("AssetCost_sequenceNo");
				if (Utility.isEmpty(sequenceNoString))
				{
					sequenceNoString = "0";
				}
				BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
				comp_id.setSequenceNo(sequenceNo);
			}
			if (incomingRequest.containsKey("AssetCost_amount"))
			{
				String amountString = (String) incomingRequest.get("AssetCost_amount");
				if (Utility.isEmpty(amountString))
				{
					amountString = "0";
				}
				BigDecimal amount = new BigDecimal ( amountString );
				assetCost.setAmount(amount);
			}
			if (incomingRequest.containsKey("AssetCost_extendLifeFlag"))
			{
				String extendLifeFlag = (String ) incomingRequest.get("AssetCost_extendLifeFlag");
				assetCost.setExtendLifeFlag(extendLifeFlag);
			}
			if (incomingRequest.containsKey("AssetCost_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("AssetCost_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString, userDateFormat);
				assetCost.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("AssetCost_dateReceived"))
			{
				String dateReceivedString = (String) incomingRequest.get("AssetCost_dateReceived");
				Date dateReceived = Dates.getDate(dateReceivedString, userDateFormat);
				assetCost.setDateReceived(dateReceived);
			}
			if (incomingRequest.containsKey("AssetCost_poNumber"))
			{
				String poNumber = (String ) incomingRequest.get("AssetCost_poNumber");
				assetCost.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("AssetCost_poVendor"))
			{
				String poVendor = (String ) incomingRequest.get("AssetCost_poVendor");
				assetCost.setPoVendor(poVendor);
			}
			if (incomingRequest.containsKey("AssetCost_description"))
			{
				String description = (String ) incomingRequest.get("AssetCost_description");
				assetCost.setDescription(description);
			}
			if (incomingRequest.containsKey("AssetCost_lastChgBy"))
			{
				String lastChgBy = (String ) incomingRequest.get("AssetCost_lastChgBy");
				assetCost.setLastChgBy(lastChgBy);
			}
			if (incomingRequest.containsKey("AssetCost_dateChanged"))
			{
				String dateChangedString = (String) incomingRequest.get("AssetCost_dateChanged");
				Date dateChanged = Dates.getDate(dateChangedString, userDateFormat);
				assetCost.setDateChanged(dateChanged);
			}
			assetCost.setComp_id(comp_id);

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