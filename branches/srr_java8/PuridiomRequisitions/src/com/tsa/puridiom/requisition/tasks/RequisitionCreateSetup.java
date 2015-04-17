package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class RequisitionCreateSetup extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId") ;
		String	userId = (String) incomingRequest.get("userId") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");
		UserProfile user = UserManager.getInstance().getUser(organizationId, userId);

		// Create new ic codes
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
		//String	dateOffset = propertiesManager.getProperty("REQ DEFAULTS", "DateOffset", "0");
		String	schedules = propertiesManager.getProperty("Calendar Management","Schedule","N");
		String 	s_requiredDate = propertiesManager.getProperty("REQ DEFAULTS", "REQUIREDDATEEMPTY", "N");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
        }

		int		offsetDays = 0;
		try
		{
			offsetDays = Integer.valueOf(propertiesManager.getProperty("REQ DEFAULTS", "DateOffset", "0")).intValue();
		}
		catch(NumberFormatException e)
		{
			offsetDays = 0;
		}
		Dates dates = new Dates();

		if(schedules.equals("Y"))
		{
			offsetDays = dates.getOffsetDays(offsetDays, true);
		}
		else
		{
			offsetDays = dates.getOffsetDays(offsetDays, false);
		}

		String fYear = Dates.getFiscalYear(Integer.parseInt(propertiesManager.getProperty("misc","fybegin","1")), userTimeZone) ;
		String today = Dates.today(userDateFormat, userTimeZone) ;
		String requisitionType = (String) incomingRequest.get("RequisitionHeader_requisitionType") ;
		if (Utility.isEmpty(requisitionType)) {
			requisitionType = "P" ;
		}

		String	shipToCode = user.getShipToCode();
		if(requisitionType.equalsIgnoreCase("R"))
		{
			Object itemLocationObj = incomingRequest.get("InvLocation_itemLocation");
			if (itemLocationObj instanceof String[]) {
				String itemLocations[] = (String[]) itemLocationObj;
				for (int i=0; i < itemLocations.length; i++)
				{
					shipToCode = itemLocations[i];
					break;
				}
			}
			else
			{
				shipToCode = HiltonUtility.ckNull((String) incomingRequest.get("InvLocation_itemLocation"));
			}
		}
		if (Utility.isEmpty(shipToCode)) {
		    shipToCode = propertiesManager.getProperty("REQ DEFAULTS","ShipToCode","");
		}
		CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
		String currencyCode = currencyManager.getCurrencyCodeForCountry(user.getLocale());

		if (Utility.isEmpty(currencyCode)) {
		    currencyCode = propertiesManager.getProperty("REQ DEFAULTS","CurrencyCode","");
		}
		if (Utility.isEmpty(currencyCode)) {
		    currencyCode = currencyManager.getBaseCurrencyCode();
		}

		incomingRequest.put("RequisitionHeader_icReqHeader",ukg.getUniqueKey().toString());
		incomingRequest.put("RequisitionHeader_icHeaderHistory",ukg.getUniqueKey().toString());
		incomingRequest.put("icHeader", (String) incomingRequest.get("RequisitionHeader_icReqHeader"));
		incomingRequest.put("RequisitionHeader_requisitionType",requisitionType) ;
		incomingRequest.put("RequisitionHeader_requisitionNumber", "N/A");
		if(requisitionType.equalsIgnoreCase("M"))
		{
			incomingRequest.put("RequisitionHeader_status",DocumentStatus.REQ_PLANNING) ;
			if (user.isAFpe()) {
				incomingRequest.put("RequisitionHeader_owner",userId) ;
			} else {
				incomingRequest.put("RequisitionHeader_owner","") ;
			}
		} else {
			incomingRequest.put("RequisitionHeader_status",DocumentStatus.REQ_INPROGRESS) ;
			incomingRequest.put("RequisitionHeader_owner",userId) ;
		}

		incomingRequest.put("RequisitionHeader_requisitionerCode",userId) ;
		if ( !organizationId.equalsIgnoreCase("bsc04p") )
		{
			incomingRequest.put("RequisitionHeader_departmentCode", user.getDepartment());
		}
		incomingRequest.put("RequisitionHeader_dateEntered",today) ;
		incomingRequest.put("RequisitionHeader_requisitionDate",today) ;
		incomingRequest.put("RequisitionHeader_ammendment", "0");
		incomingRequest.put("RequisitionHeader_discountPercent", "0");
		incomingRequest.put("RequisitionHeader_discountAmount", "0");
		incomingRequest.put("RequisitionHeader_shippingCharges", "0");
		incomingRequest.put("RequisitionHeader_taxShipping", propertiesManager.getProperty("REQ DEFAULTS","SHIPTAXABLE","N"));
		incomingRequest.put("RequisitionHeader_otherCharges", "0");
		incomingRequest.put("RequisitionHeader_taxOther", "N");
		incomingRequest.put("RequisitionHeader_taxPercent", "0");
		incomingRequest.put("RequisitionHeader_taxAmount", "0");
		incomingRequest.put("RequisitionHeader_otherTaxAmount", "0");
		incomingRequest.put("RequisitionHeader_shippingTaxAmt", "0");
		incomingRequest.put("RequisitionHeader_subtotal", "0");
		incomingRequest.put("RequisitionHeader_total", "0");
		incomingRequest.put("RequisitionHeader_estimatedCost", "0");
		incomingRequest.put("RequisitionHeader_discountSource","S") ;
		incomingRequest.put("RequisitionHeader_language", propertiesManager.getProperty("REQ DEFAULTS","Language","(Default)")) ;
		incomingRequest.put("RequisitionHeader_appSigned","N") ;
		incomingRequest.put("RequisitionHeader_approved","N") ;
		incomingRequest.put("RequisitionHeader_lastChgBy",userId) ;
		incomingRequest.put("RequisitionHeader_lastChgDate",today) ;
		incomingRequest.put("RequisitionHeader_currencyCode", currencyCode);
		incomingRequest.put("RequisitionHeader_currencyFactor", String.valueOf(currencyManager.getCurrencyFactor(currencyCode)));

		if (organizationId.equalsIgnoreCase("qri06p") && HiltonUtility.isQriCanadian(organizationId,propertiesManager.getProperty("REQ DEFAULTS","Udf1Code","")))
		{
			incomingRequest.put("RequisitionHeader_useTaxPercent", propertiesManager.getProperty("REQ DEFAULTS","USETAXPERCENTCANADIAN","0"));
		}
		else
		{
			incomingRequest.put("RequisitionHeader_useTaxPercent", propertiesManager.getProperty("REQ DEFAULTS","USETAXPERCENT","0"));
		}

		if (organizationId.equalsIgnoreCase("qri06p"))
		{
			String canadianUser = propertiesManager.getProperty("MISC", "CanadianUser", "CA");
			if (user.getLocale().equalsIgnoreCase(canadianUser))
			{
				incomingRequest.put("RequisitionHeader_assignedBuyer", propertiesManager.getProperty("REQ DEFAULTS", "BuyerCanadian", ""));
				incomingRequest.put("RequisitionHeader_buyer", propertiesManager.getProperty("REQ DEFAULTS", "BuyerCanadian", ""));
			}
			else
			{
				incomingRequest.put("RequisitionHeader_assignedBuyer", "PURCHASING");
				incomingRequest.put("RequisitionHeader_buyer", propertiesManager.getProperty("REQ DEFAULTS","Buyer",""));
			}
		}
		else if (requisitionType.equals("K"))
		{
			incomingRequest.put("RequisitionHeader_assignedBuyer", "AP") ;
			incomingRequest.put("RequisitionHeader_buyer", "AP") ;
		}
		else
		{
			incomingRequest.put("RequisitionHeader_assignedBuyer", "PURCHASING") ;
			incomingRequest.put("RequisitionHeader_buyer", propertiesManager.getProperty("REQ DEFAULTS","Buyer","")) ;
		}
		incomingRequest.put("RequisitionHeader_assignedDate",today) ;
		incomingRequest.put("RequisitionHeader_pcardReq","N") ;
		incomingRequest.put("RequisitionHeader_reqRecalc","N");
		incomingRequest.put("RequisitionHeader_fiscalYear",fYear);

		if ("Y".equals(s_requiredDate))
			incomingRequest.put("RequisitionHeader_requiredDate", "");
		else
			incomingRequest.put("RequisitionHeader_requiredDate", Dates.add(today, (int) offsetDays, userDateFormat));

		incomingRequest.put("RequisitionHeader_receiptRequired", propertiesManager.getProperty("REQ DEFAULTS","ReceiptRequired",""));
		incomingRequest.put("RequisitionHeader_itemLocation", propertiesManager.getProperty("REQ DEFAULTS","InventoryLocation",""));
		if (requisitionType.equals("O"))
		{
			incomingRequest.put("RequisitionHeader_vendorId", "*TBD*");
		}
		else
		{
			incomingRequest.put("RequisitionHeader_vendorId", "");
		}

		incomingRequest.put("RequisitionHeader_shipToCode", shipToCode);
		if (propertiesManager.getProperty("REQ DEFAULTS","ShipAttn","").equals("Y"))
		{
			incomingRequest.put("RequisitionHeader_shipAttn", user.getDisplayName());
		}

		incomingRequest.put("RequisitionHeader_priorityCode", propertiesManager.getProperty("REQ DEFAULTS","PriorityCode",""));
		incomingRequest.put("RequisitionHeader_shippingCode", propertiesManager.getProperty("REQ DEFAULTS","ShippingCode",""));
		incomingRequest.put("RequisitionHeader_taxCode", propertiesManager.getProperty("REQ DEFAULTS","TaxCode",""));
		incomingRequest.put("RequisitionHeader_billToCode", propertiesManager.getProperty("REQ DEFAULTS","BillToCode",""));
		incomingRequest.put("RequisitionHeader_billToContact", propertiesManager.getProperty("REQ DEFAULTS","BillToContact",""));
		incomingRequest.put("RequisitionHeader_udf1Code", propertiesManager.getProperty("REQ DEFAULTS","Udf1Code",""));
		incomingRequest.put("RequisitionHeader_udf2Code", propertiesManager.getProperty("REQ DEFAULTS","Udf2Code",""));
		incomingRequest.put("RequisitionHeader_udf3Code", propertiesManager.getProperty("REQ DEFAULTS","Udf3Code",""));
		incomingRequest.put("RequisitionHeader_udf4Code", propertiesManager.getProperty("REQ DEFAULTS","Udf4Code",""));
		incomingRequest.put("RequisitionHeader_udf5Code", propertiesManager.getProperty("REQ DEFAULTS","Udf5Code",""));
		incomingRequest.put("RequisitionHeader_udf6Code", propertiesManager.getProperty("REQ DEFAULTS","Udf6Code",""));
		if (organizationId.equalsIgnoreCase("bly07p"))
		{
		 incomingRequest.put("RequisitionHeader_udf7Code", user.getNameUdf4());
		}
		else
		{
		 incomingRequest.put("RequisitionHeader_udf7Code", propertiesManager.getProperty("REQ DEFAULTS","Udf7Code",""));
		}

		incomingRequest.put("RequisitionHeader_udf8Code", propertiesManager.getProperty("REQ DEFAULTS","Udf8Code",""));
		incomingRequest.put("RequisitionHeader_udf9Code", propertiesManager.getProperty("REQ DEFAULTS","Udf9Code",""));
		incomingRequest.put("RequisitionHeader_udf10Code", propertiesManager.getProperty("REQ DEFAULTS","Udf10Code",""));
		incomingRequest.put("RequisitionHeader_udf11Code", propertiesManager.getProperty("REQ DEFAULTS","Udf11Code",""));
		incomingRequest.put("RequisitionHeader_udf12Code", propertiesManager.getProperty("REQ DEFAULTS","Udf12Code",""));
		incomingRequest.put("RequisitionHeader_udf13Code", propertiesManager.getProperty("REQ DEFAULTS","Udf13Code",""));
		incomingRequest.put("RequisitionHeader_udf14Code", propertiesManager.getProperty("REQ DEFAULTS","Udf14Code",""));
		incomingRequest.put("RequisitionHeader_udf15Code", propertiesManager.getProperty("REQ DEFAULTS","Udf15Code",""));
		incomingRequest.put("RequisitionHeader_routing", user.getRouting());
        incomingRequest.put("RequisitionHeader_timeZone", userTimeZone);

		incomingRequest.put("RequisitionLine_icReqHeader",(String) incomingRequest.get("RequisitionHeader_icReqHeader"));

		return null ;
	}

}