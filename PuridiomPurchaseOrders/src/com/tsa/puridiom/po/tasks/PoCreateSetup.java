/**
 * Created on Jan 6, 2004
 * @author renzo
 * com.tsa.puridiom.po.tasks.POCreateSetup.java
 */

package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.PrintMode;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class PoCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            String noApprovalNeed = HiltonUtility.ckNull((String) incomingRequest.get("NoApprovalNeed"));
            String noApprovalNeedAutoAward = HiltonUtility.ckNull((String) incomingRequest.get("NoApprovalNeedAutoAward"));

			UserProfile user = UserManager.getInstance().getUser(organizationId, userId);
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Dates dates = new Dates();

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

			String	today = Dates.today(userDateFormat, userTimeZone) ;
			int		offsetDays = 0;
			try
			{
				offsetDays = Integer.valueOf(propertiesManager.getProperty("PO DEFAULTS", "DateOffset", "0")).intValue();
			}
			catch(NumberFormatException e)
			{
				offsetDays = 0;
			}
			String schedules = propertiesManager.getProperty("Calendar Management","Schedule","N");
			if(schedules.equals("Y"))
			{
				offsetDays = dates.getOffsetDays(offsetDays, true);
			}
			else
			{
				offsetDays = dates.getOffsetDays(offsetDays, false);
			}
			//fiscal year
			String begin = propertiesManager.getProperty("Misc", "fybegin", "1");
			Integer iBegin = new Integer(begin);
			String poType = (String)incomingRequest.get("PoHeader_poType");
			if(Utility.isEmpty(poType))
			{
			    poType = "PO";
			}

			CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
			String currencyCode = currencyManager.getCurrencyCodeForCountry(user.getLocale());

			if (Utility.isEmpty(currencyCode)) {
			    currencyCode = propertiesManager.getProperty("PO DEFAULTS","CurrencyCode","");
			}
			if (Utility.isEmpty(currencyCode)) {
			    currencyCode = currencyManager.getBaseCurrencyCode();
			}

			incomingRequest.put("PoHeader_poType", poType);
			incomingRequest.put("PoHeader_poNumber", "N/A");
			if(noApprovalNeed.equalsIgnoreCase("Y") && !noApprovalNeedAutoAward.equalsIgnoreCase("Y"))
	        {
				incomingRequest.put("PoHeader_status", DocumentStatus.PO_INPROGRESS);
				incomingRequest.put("PoHeader_confirming", "Y");
	        }
			else if(noApprovalNeedAutoAward.equalsIgnoreCase("Y"))
	        {
				incomingRequest.put("PoHeader_status", DocumentStatus.PO_AWARDED);
				incomingRequest.put("PoHeader_confirming", "Y");
	        }
			else
			{
	            if (poType.equals(OrderType.CONTRACT) && propertiesManager.getProperty("PO OPTIONS", "CONTRACTSAVEASPO", "N").equals("Y")) 
	            {
	                incomingRequest.put("PoHeader_status", DocumentStatus.CT_INPROGRESS);
	            } 
	           
	            else 
	            {
	                incomingRequest.put("PoHeader_status", DocumentStatus.PO_INPROGRESS);
	            } 
			}
            String icPoHeader = ukg.getUniqueKey().toString();
			incomingRequest.put("PoHeader_icPoHeader",icPoHeader);
			incomingRequest.put("PoHeader_icHeaderKey",icPoHeader);
			incomingRequest.put("PoHeader_icHeaderHistory",ukg.getUniqueKey().toString());
			incomingRequest.put("PoHeader_poDate", today);
			//incomingRequest.put("PoHeader_promisedDate", Dates.add(today, offsetDays, userDateFormat));
			incomingRequest.put("PoHeader_requiredDate", Dates.add(today, offsetDays, userDateFormat));
			if(!noApprovalNeed.equalsIgnoreCase("Y"))
	        {
				incomingRequest.put("PoHeader_buyerCode", userId);
				incomingRequest.put("PoHeader_owner", userId);
	        }
			incomingRequest.put("PoHeader_requisitionerCode", userId);
			if(organizationId.equalsIgnoreCase("BSC04P"))
			{
				incomingRequest.put("PoHeader_departmentCode", "");
			}
			else
			{
				incomingRequest.put("PoHeader_departmentCode", UserManager.getInstance().getUser(organizationId, userId).getDepartment());
			}
			//incomingRequest.put("PoHeader_departmentCode", UserManager.getInstance().getUser(organizationId, userId).getDepartment());
			incomingRequest.put("PoHeader_receiptRequired", propertiesManager.getProperty("PO DEFAULTS","ReceiptRequired",""));
			incomingRequest.put("PoHeader_language", propertiesManager.getProperty("PO DEFAULTS","Language","(Default)"));
			String	shipToCode = user.getShipToCode();
			if (Utility.isEmpty(shipToCode)) {
			    shipToCode = propertiesManager.getProperty("PO DEFAULTS","ShipToCode","");
			}
			incomingRequest.put("PoHeader_shipToCode", shipToCode);
			incomingRequest.put("PoHeader_shipToContact", propertiesManager.getProperty("PO DEFAULTS","ShipToContact",""));
			incomingRequest.put("PoHeader_billToCode", propertiesManager.getProperty("PO DEFAULTS","BillToCode",""));
			incomingRequest.put("PoHeader_billToContact", propertiesManager.getProperty("PO DEFAULTS","BillToContact",""));
            incomingRequest.put("PoHeader_priorityCode", propertiesManager.getProperty("PO DEFAULTS","PriorityCode",""));
			incomingRequest.put("PoHeader_taxCode", propertiesManager.getProperty("PO DEFAULTS","TaxCode",""));

			if (organizationId.equalsIgnoreCase("qri06p") && HiltonUtility.isQriCanadian(organizationId,propertiesManager.getProperty("PO DEFAULTS","Udf1Code","")))
			{
				incomingRequest.put("PoHeader_useTaxPercent", propertiesManager.getProperty("PO DEFAULTS","USETAXPERCENTCANADIAN","0"));
			}
			else
			{
				incomingRequest.put("PoHeader_useTaxPercent", propertiesManager.getProperty("PO DEFAULTS","USETAXPERCENT","0"));
			}

			String udf1 = propertiesManager.getProperty("PO DEFAULTS","Udf1Code","");
			String udf2 = propertiesManager.getProperty("PO DEFAULTS","Udf2Code","");
			String udf3 = propertiesManager.getProperty("PO DEFAULTS","Udf3Code","");
			String udf4 = propertiesManager.getProperty("PO DEFAULTS","Udf4Code","");
			String udf5 = propertiesManager.getProperty("PO DEFAULTS","Udf5Code","");
			String udf6 = propertiesManager.getProperty("PO DEFAULTS","Udf6Code","");
			String udf7 = propertiesManager.getProperty("PO DEFAULTS","Udf7Code","");
			String udf8 = propertiesManager.getProperty("PO DEFAULTS","Udf8Code","");
			String udf9 = propertiesManager.getProperty("PO DEFAULTS","Udf9Code","");
			String udf10 = propertiesManager.getProperty("PO DEFAULTS","Udf10Code","");

			if (!Utility.isEmpty(udf1)) {
				incomingRequest.put("PoHeader_udf1Code", udf1);
			}
			if (!Utility.isEmpty(udf2)) {
			    incomingRequest.put("PoHeader_udf2Code", udf2);
			}
			if (!Utility.isEmpty(udf3)) {
			    incomingRequest.put("PoHeader_udf3Code", udf3);
			}
			if (!Utility.isEmpty(udf4)) {
			    incomingRequest.put("PoHeader_udf4Code", udf4);
			}
			if (!Utility.isEmpty(udf5)) {
			    incomingRequest.put("PoHeader_udf5Code", udf5);
			}
			if (!Utility.isEmpty(udf6)) {
			    incomingRequest.put("PoHeader_udf6Code", udf6);
			}
			if (!Utility.isEmpty(udf7)) {
			    incomingRequest.put("PoHeader_udf7Code", udf7);
			}
			if (!Utility.isEmpty(udf8)) {
			    incomingRequest.put("PoHeader_udf8Code", udf8);
			}
			if (!Utility.isEmpty(udf9)) {
			    incomingRequest.put("PoHeader_udf9Code", udf9);
			}
			if (!Utility.isEmpty(udf10)) {
			    incomingRequest.put("PoHeader_udf10Code", udf10);
			}

			incomingRequest.put("PoHeader_ediOrder", propertiesManager.getProperty("PO DEFAULTS","EdiOrder",""));
			incomingRequest.put("PoHeader_ediStatus", propertiesManager.getProperty("PO DEFAULTS","EdiStatus",""));
			incomingRequest.put("PoHeader_currencyCode", currencyCode);
			incomingRequest.put("PoHeader_currencyFactor", String.valueOf(currencyManager.getCurrencyFactor(currencyCode)));
			incomingRequest.put("PoHeader_fiscalYear", Dates.getFiscalYear(iBegin.intValue(), userTimeZone));
            incomingRequest.put("PoHeader_timeZone", userTimeZone);

			incomingRequest.put("icHeader", (String) incomingRequest.get("PoHeader_icPoHeader"));
			incomingRequest.put("PoLine_icPoHeader", (String) incomingRequest.get("PoHeader_icPoHeader"));
			if(organizationId.equalsIgnoreCase("MSG07P")) {
				incomingRequest.put("PoHeader_printMode", PrintMode.DRAFT_ONLY_NOT_AN_ORDER);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
