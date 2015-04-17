package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class CommoditySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

	if (Utility.isEmpty(userDateFormat)) {
		userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	}

		try
		{
			Commodity commodity = (Commodity) incomingRequest.get("commodity");
			if (commodity == null)
			{
				commodity = new Commodity();
			}

			if (incomingRequest.containsKey("Commodity_commodity"))
			{
				String commodityCode = (String) incomingRequest.get("Commodity_commodity");
				commodity.setCommodity(commodityCode);
			}
			if (incomingRequest.containsKey("Commodity_variance"))
			{
				String varianceString = (String) incomingRequest.get("Commodity_variance");
				if (Utility.isEmpty(varianceString))
				{
					varianceString = "0";
				}
				BigDecimal variance = new BigDecimal ( varianceString );
				commodity.setVariance(variance);
			}
			if (incomingRequest.containsKey("Commodity_failsafe"))
			{
				String failsafe = (String) incomingRequest.get("Commodity_failsafe");
				commodity.setFailsafe(failsafe);
			}
			if (incomingRequest.containsKey("Commodity_description"))
			{
				String description = (String) incomingRequest.get("Commodity_description");
				commodity.setDescription(description);
			}
			if (incomingRequest.containsKey("Commodity_buyerCode"))
			{
				String buyerCode = (String) incomingRequest.get("Commodity_buyerCode");
				commodity.setBuyerCode(buyerCode);
			}
			if (incomingRequest.containsKey("Commodity_status"))
			{
				String status = (String) incomingRequest.get("Commodity_status");
				commodity.setStatus(status);
			}
			if (incomingRequest.containsKey("Commodity_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Commodity_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString, userDateFormat);
				commodity.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Commodity_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Commodity_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString, userDateFormat);
				commodity.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Commodity_owner"))
			{
				String owner = (String) incomingRequest.get("Commodity_owner");
				commodity.setOwner(owner);
			}
			if (incomingRequest.containsKey("Commodity_lastChgBy"))
			{
				String lastChgBy = (String) incomingRequest.get("Commodity_lastChgBy");
				commodity.setLastChgBy(lastChgBy);
			}
			else
			{
				commodity.setLastChgBy((String) incomingRequest.get("userId"));
			}
			if (incomingRequest.containsKey("Commodity_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("Commodity_lastChgDate");
				Date lastChgDate = Dates.getDate(lastChgDateString, userDateFormat);
				commodity.setLastChgDate(lastChgDate);
			}
			else
			{
                String userTimeZone = (String) incomingRequest.get("userTimeZone");
				commodity.setLastChgDate(Dates.getDate(Dates.today("", userTimeZone)));
			}
			if (incomingRequest.containsKey("Commodity_vchVariance"))
			{
				String vchVarianceString = (String) incomingRequest.get("Commodity_vchVariance");
				if (Utility.isEmpty(vchVarianceString))
				{
					vchVarianceString = "0";
				}
				BigDecimal vchVariance = new BigDecimal ( vchVarianceString );
				commodity.setVchVariance(vchVariance);
			}
			if (incomingRequest.containsKey("Commodity_vchFailsafe"))
			{
				String vchFailsafe = (String) incomingRequest.get("Commodity_vchFailsafe");
				commodity.setVchFailsafe(vchFailsafe);
			}
			if (incomingRequest.containsKey("Commodity_asset"))
			{
				String asset = (String) incomingRequest.get("Commodity_asset");
				commodity.setAsset(asset);
			}
			if (incomingRequest.containsKey("Commodity_depreciationTerm"))
			{
				String depreciationTermString = (String) incomingRequest.get("Commodity_depreciationTerm");
				if (Utility.isEmpty(depreciationTermString))
				{
					depreciationTermString = "0";
				}
				BigDecimal depreciationTerm = new BigDecimal ( depreciationTermString );
				commodity.setDepreciationTerm(depreciationTerm);
			}
			if (incomingRequest.containsKey("Commodity_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("Commodity_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				BigDecimal icAccount = new BigDecimal ( icAccountString );
				commodity.setIcAccount(icAccount);
			}
			if (incomingRequest.containsKey("Commodity_nigplevel"))
			{
				String nigplevel = (String) incomingRequest.get("Commodity_nigplevel");
				commodity.setNigplevel(nigplevel);
			}
			if (incomingRequest.containsKey("Commodity_level1"))
			{
				String level1 = (String) incomingRequest.get("Commodity_level1");
				commodity.setLevel1(level1);
			}
			if (incomingRequest.containsKey("Commodity_level2"))
			{
				String level2 = (String) incomingRequest.get("Commodity_level2");
				commodity.setLevel2(level2);
			}
			if (incomingRequest.containsKey("Commodity_level3"))
			{
				String level3 = (String) incomingRequest.get("Commodity_level3");
				commodity.setLevel3(level3);
			}
			if (incomingRequest.containsKey("Commodity_taxCode"))
			{
				String taxCode = (String) incomingRequest.get("Commodity_taxCode");
				commodity.setTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("Commodity_commodityType"))
			{
				String commodityType = (String) incomingRequest.get("Commodity_commodityType");
				commodity.setCommodityType(commodityType);
			}
			if (incomingRequest.containsKey("Commodity_duomRequired"))
			{
				String duomRequired = (String) incomingRequest.get("Commodity_duomRequired");
				commodity.setDuomRequired(duomRequired);
			}
			if (incomingRequest.containsKey("Commodity_iclLevel"))
			{
				String iclLevelString = (String) incomingRequest.get("Commodity_iclLevel");
				if (Utility.isEmpty(iclLevelString))
				{
					iclLevelString = "0";
				}
				BigDecimal iclLevel = new BigDecimal(iclLevelString);
				commodity.setIclLevel(iclLevel);
			}
			if (incomingRequest.containsKey("Commodity_serialNumbersRequired"))
			{
				String serialNumbersRequired = (String) incomingRequest.get("Commodity_serialNumbersRequired");
				commodity.setSerialNumbersRequired(serialNumbersRequired);
			}
			if (incomingRequest.containsKey("Commodity_receiptRequired"))
			{
				String receiptRequired = (String ) incomingRequest.get("Commodity_receiptRequired");
				commodity.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("Commodity_taxable"))
			{
				String taxable = (String ) incomingRequest.get("Commodity_taxable");
				commodity.setTaxable(taxable);
			}

			result = commodity;
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
