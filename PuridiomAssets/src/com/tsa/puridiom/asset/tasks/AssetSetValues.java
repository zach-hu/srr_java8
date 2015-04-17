package com.tsa.puridiom.asset.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AssetSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Asset asset = (Asset) incomingRequest.get("asset");
			if (asset == null)
			{
				asset = new Asset();
			}
            String organizationId = (String) incomingRequest.get("organizationId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

			if (incomingRequest.containsKey("Asset_tagNumber"))
			{
				String tagNumber = (String ) incomingRequest.get("Asset_tagNumber");
				asset.setTagNumber(tagNumber);
			}
			if (incomingRequest.containsKey("Asset_assetClass"))
			{
				String assetClass = (String ) incomingRequest.get("Asset_assetClass");
				asset.setAssetClass(assetClass);
			}
			if (incomingRequest.containsKey("Asset_securityCode"))
			{
				String securityCode = (String ) incomingRequest.get("Asset_securityCode");
				asset.setSecurityCode(securityCode);
			}
			if (incomingRequest.containsKey("Asset_commodity"))
			{
				String commodity = (String ) incomingRequest.get("Asset_commodity");
				asset.setCommodity(commodity);
			}
			if (incomingRequest.containsKey("Asset_manufacturer"))
			{
				String manufacturer = (String ) incomingRequest.get("Asset_manufacturer");
				asset.setManufacturer(manufacturer);
			}
			if (incomingRequest.containsKey("Asset_model"))
			{
				String model = (String ) incomingRequest.get("Asset_model");
				asset.setModel(model);
			}
			if (incomingRequest.containsKey("Asset_serialNumber"))
			{
				String serialNumber = (String ) incomingRequest.get("Asset_serialNumber");
				asset.setSerialNumber(serialNumber);
			}
			if (incomingRequest.containsKey("Asset_assetCost"))
			{
				String assetCostString = (String) incomingRequest.get("Asset_assetCost");
				if (Utility.isEmpty(assetCostString))
				{
					assetCostString = "0";
				}
				BigDecimal assetCost = new BigDecimal ( assetCostString );
				asset.setAssetCost(assetCost);
			}
			if (incomingRequest.containsKey("Asset_dateInService"))
			{
				String dateInServiceString = (String) incomingRequest.get("Asset_dateInService");
				Date dateInService = Dates.getDate(dateInServiceString, userDateFormat);
				asset.setDateInService(dateInService);
			}
			if (incomingRequest.containsKey("Asset_deprecMethod"))
			{
				String deprecMethod = (String ) incomingRequest.get("Asset_deprecMethod");
				asset.setDeprecMethod(deprecMethod);
			}
			if (incomingRequest.containsKey("Asset_warrantyStart"))
			{
				String warrantyStartString = (String) incomingRequest.get("Asset_warrantyStart");
				Date warrantyStart = Dates.getDate(warrantyStartString, userDateFormat);
				asset.setWarrantyStart(warrantyStart);
			}
			if (incomingRequest.containsKey("Asset_warrantyEnd"))
			{
				String warrantyEndString = (String) incomingRequest.get("Asset_warrantyEnd");
				Date warrantyEnd = Dates.getDate(warrantyEndString, userDateFormat);
				asset.setWarrantyEnd(warrantyEnd);
			}
			if (incomingRequest.containsKey("Asset_upgradeReqs"))
			{
				String upgradeReqs = (String ) incomingRequest.get("Asset_upgradeReqs");
				asset.setUpgradeReqs(upgradeReqs);
			}
			if (incomingRequest.containsKey("Asset_contractService"))
			{
				String contractService = (String ) incomingRequest.get("Asset_contractService");
				asset.setContractService(contractService);
			}
			if (incomingRequest.containsKey("Asset_purchaseOrder"))
			{
				String purchaseOrder = (String ) incomingRequest.get("Asset_purchaseOrder");
				asset.setPurchaseOrder(purchaseOrder);
			}
			if (incomingRequest.containsKey("Asset_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Asset_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString, userDateFormat);
				asset.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Asset_assetStatus"))
			{
				String assetStatus = (String ) incomingRequest.get("Asset_assetStatus");
				asset.setAssetStatus(assetStatus);
			}
			if (incomingRequest.containsKey("Asset_assetUdf1"))
			{
				String assetUdf1 = (String ) incomingRequest.get("Asset_assetUdf1");
				asset.setAssetUdf1(assetUdf1);
			}
			if (incomingRequest.containsKey("Asset_assetUdf2"))
			{
				String assetUdf2 = (String ) incomingRequest.get("Asset_assetUdf2");
				asset.setAssetUdf2(assetUdf2);
			}
			if (incomingRequest.containsKey("Asset_assetUdf3"))
			{
				String assetUdf3 = (String ) incomingRequest.get("Asset_assetUdf3");
				asset.setAssetUdf3(assetUdf3);
			}
			if (incomingRequest.containsKey("Asset_assetUdf4"))
			{
				String assetUdf4 = (String ) incomingRequest.get("Asset_assetUdf4");
				asset.setAssetUdf4(assetUdf4);
			}
			if (incomingRequest.containsKey("Asset_assetUdf5"))
			{
				String assetUdf5 = (String ) incomingRequest.get("Asset_assetUdf5");
				asset.setAssetUdf5(assetUdf5);
			}
			if (incomingRequest.containsKey("Asset_totalCost"))
			{
				String totalCostString = (String) incomingRequest.get("Asset_totalCost");
				if (Utility.isEmpty(totalCostString))
				{
					totalCostString = "0";
				}
				BigDecimal totalCost = new BigDecimal ( totalCostString );
				asset.setTotalCost(totalCost);
			}
			if (incomingRequest.containsKey("Asset_icText"))
			{
				String icTextString = (String) incomingRequest.get("Asset_icText");
				if (Utility.isEmpty(icTextString))
				{
					icTextString = "0";
				}
				BigDecimal icText = new BigDecimal ( icTextString );
				asset.setIcText(icText);
			}
			if (incomingRequest.containsKey("Asset_imageFile"))
			{
				String imageFile = (String ) incomingRequest.get("Asset_imageFile");
				asset.setImageFile(imageFile);
			}
			if (incomingRequest.containsKey("Asset_poVendor"))
			{
				String poVendor = (String ) incomingRequest.get("Asset_poVendor");
				asset.setPoVendor(poVendor);
			}
			if (incomingRequest.containsKey("Asset_contractorName"))
			{
				String contractorName = (String ) incomingRequest.get("Asset_contractorName");
				asset.setContractorName(contractorName);
			}
			if (incomingRequest.containsKey("Asset_owner"))
			{
				String owner = (String ) incomingRequest.get("Asset_owner");
				asset.setOwner(owner);
			}
			if (incomingRequest.containsKey("Asset_deprecTerm"))
			{
				String deprecTermString = (String) incomingRequest.get("Asset_deprecTerm");
				if (Utility.isEmpty(deprecTermString))
				{
					deprecTermString = "0";
				}
				BigDecimal deprecTerm = new BigDecimal ( deprecTermString );
				asset.setDeprecTerm(deprecTerm);
			}
			if (incomingRequest.containsKey("Asset_dateReceived"))
			{
				String dateReceivedString = (String) incomingRequest.get("Asset_dateReceived");
				Date dateReceived = Dates.getDate(dateReceivedString, userDateFormat);
				asset.setDateReceived(dateReceived);
			}
			if (incomingRequest.containsKey("Asset_icReceipt"))
			{
				String icReceiptString = (String) incomingRequest.get("Asset_icReceipt");
				if (Utility.isEmpty(icReceiptString))
				{
					icReceiptString = "0";
				}
				BigDecimal icReceipt = new BigDecimal ( icReceiptString );
				asset.setIcReceipt(icReceipt);
			}
			if (incomingRequest.containsKey("Asset_assetUdf6"))
			{
				String assetUdf6 = (String ) incomingRequest.get("Asset_assetUdf6");
				asset.setAssetUdf6(assetUdf6);
			}
			if (incomingRequest.containsKey("Asset_assetUdf7"))
			{
				String assetUdf7 = (String ) incomingRequest.get("Asset_assetUdf7");
				asset.setAssetUdf7(assetUdf7);
			}
			if (incomingRequest.containsKey("Asset_assetUdf8"))
			{
				String assetUdf8 = (String ) incomingRequest.get("Asset_assetUdf8");
				asset.setAssetUdf8(assetUdf8);
			}
			if (incomingRequest.containsKey("Asset_assetUdf9"))
			{
				String assetUdf9 = (String ) incomingRequest.get("Asset_assetUdf9");
				asset.setAssetUdf9(assetUdf9);
			}
			if (incomingRequest.containsKey("Asset_assetUdf10"))
			{
				String assetUdf10 = (String ) incomingRequest.get("Asset_assetUdf10");
				asset.setAssetUdf10(assetUdf10);
			}
			if (incomingRequest.containsKey("Asset_icLineKey"))
			{
				String icLineKeyString = (String) incomingRequest.get("Asset_icLineKey");
				if (Utility.isEmpty(icLineKeyString))
				{
					icLineKeyString = "0";
				}
				BigDecimal icLineKey = new BigDecimal ( icLineKeyString );
				asset.setIcLineKey(icLineKey);
			}
			if (incomingRequest.containsKey("Asset_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("Asset_itemNumber");
				asset.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("Asset_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("Asset_itemLocation");
				asset.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("Asset_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("Asset_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				BigDecimal icAccount = new BigDecimal ( icAccountString );
				asset.setIcAccount(icAccount);
			}
			if (incomingRequest.containsKey("Asset_icDsbHeader"))
			{
				String icDsbHeaderString = (String) incomingRequest.get("Asset_icDsbHeader");
				if (Utility.isEmpty(icDsbHeaderString))
				{
					icDsbHeaderString = "0";
				}
				BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
				asset.setIcDsbHeader(icDsbHeader);
			}
			if (incomingRequest.containsKey("Asset_icDsbLine"))
			{
				String icDsbLineString = (String) incomingRequest.get("Asset_icDsbLine");
				if (Utility.isEmpty(icDsbLineString))
				{
					icDsbLineString = "0";
				}
				BigDecimal icDsbLine = new BigDecimal ( icDsbLineString );
				asset.setIcDsbLine(icDsbLine);
			}
			if (incomingRequest.containsKey("Asset_localCurrencyPrice"))
			{
				String localCurrencyPriceString = (String) incomingRequest.get("Asset_localCurrencyPrice");
				if (Utility.isEmpty(localCurrencyPriceString))
				{
					localCurrencyPriceString = "0";
				}
				BigDecimal localCurrencyPrice = new BigDecimal ( localCurrencyPriceString );
				asset.setLocalCurrencyPrice(localCurrencyPrice);
			}
			if (incomingRequest.containsKey("Asset_originalCost"))
			{
				String originalCostString = (String) incomingRequest.get("Asset_originalCost");
				if (Utility.isEmpty(originalCostString))
				{
					originalCostString = "0";
				}
				BigDecimal originalCost = new BigDecimal ( originalCostString );
				asset.setOriginalCost(originalCost);
			}
			if (incomingRequest.containsKey("Asset_exitValue"))
			{
				String exitValueString = (String) incomingRequest.get("Asset_exitValue");
				if (Utility.isEmpty(exitValueString))
				{
					exitValueString = "0";
				}
				BigDecimal exitValue = new BigDecimal ( exitValueString );
				asset.setExitValue(exitValue);
			}
			if (incomingRequest.containsKey("Asset_dateInactive"))
			{
				String dateInactiveString = (String) incomingRequest.get("Asset_dateInactive");
				Date dateInactive = Dates.getDate(dateInactiveString, userDateFormat);
				asset.setDateInactive(dateInactive);
			}
			if (incomingRequest.containsKey("Asset_lastChgBy"))
			{
				String lastChgBy = (String ) incomingRequest.get("Asset_lastChgBy");
				asset.setLastChgBy(lastChgBy);
			}
			if (incomingRequest.containsKey("Asset_dateChanged"))
			{
				String dateChangedString = (String) incomingRequest.get("Asset_dateChanged");
				Date dateChanged = Dates.getDate(dateChangedString, userDateFormat);
				asset.setDateChanged(dateChanged);
			}
			if (incomingRequest.containsKey("Asset_descriptionLocal"))
			{
				String description = (String ) incomingRequest.get("Asset_descriptionLocal");
				asset.setDescription(description);
			}
			if (incomingRequest.containsKey("Asset_lease"))
			{
				String lease = (String ) incomingRequest.get("Asset_lease");
				asset.setLease(lease);
			}
			if (incomingRequest.containsKey("Asset_leaseType"))
			{
				String leaseType = (String ) incomingRequest.get("Asset_leaseType");
				asset.setLeaseType(leaseType);
			}
			if (incomingRequest.containsKey("Asset_leaseTerm"))
			{
				String leaseTerm = (String ) incomingRequest.get("Asset_leaseTerm");
				asset.setLeaseTerm(leaseTerm);
			}
			if (incomingRequest.containsKey("Asset_monthlyPayment"))
			{
				String monthlyPaymentString = (String) incomingRequest.get("Asset_monthlyPayment");
				Date monthlyPayment = Dates.getDate(monthlyPaymentString, userDateFormat);
				asset.setMonthlyPayment(monthlyPayment);
			}
			if (incomingRequest.containsKey("Asset_leasingCompany"))
			{
				String leasingCompanyString = (String) incomingRequest.get("Asset_leasingCompany");
				if (Utility.isEmpty(leasingCompanyString))
				{
					leasingCompanyString = "0";
				}
				BigDecimal leasingCompany = new BigDecimal ( leasingCompanyString );
				asset.setLeasingCompany(leasingCompany);
			}
			if (incomingRequest.containsKey("Asset_renewalDate"))
			{
				String renewalDateString = (String) incomingRequest.get("Asset_renewalDate");
				Date renewalDate = Dates.getDate(renewalDateString, userDateFormat);
				asset.setRenewalDate(renewalDate);
			}
			if (incomingRequest.containsKey("Asset_financing"))
			{
				String financingString = (String) incomingRequest.get("Asset_financing");
				if (Utility.isEmpty(financingString))
				{
					financingString = "0";
				}
				BigDecimal financing = new BigDecimal ( financingString );
				asset.setFinancing(financing);
			}
			if (incomingRequest.containsKey("Asset_printed"))
			{
				String printed = (String ) incomingRequest.get("Asset_printed");
				if(Utility.isEmpty(printed)) {
					printed = "N";
				}
				asset.setPrinted(printed);
			}
			if (incomingRequest.containsKey("Asset_fiscalYear"))
			{
				String fiscalYear = (String) incomingRequest.get("Asset_fiscalYear");
				asset.setFiscalYear(fiscalYear);
			}
			if (incomingRequest.containsKey("Asset_trackingNumber"))
			{
				String trackingNumber = (String ) incomingRequest.get("Asset_trackingNumber");
				if (Utility.isEmpty(trackingNumber)) { asset.setTrackingNumber("N/A"); }
				else { asset.setTrackingNumber(trackingNumber); }
			}

			result = asset;
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