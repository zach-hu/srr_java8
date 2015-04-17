package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class InvoiceCreateSetup extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

		// Create new ic codes
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = propertiesManager.getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
		String today = Dates.today(userDateFormat, userTimeZone);
		int		offsetDays = 0;
		try
		{
			offsetDays = Integer.valueOf(propertiesManager.getProperty("VOUCHER DEFAULTS", "DateOffset", "0")).intValue();
		}
		catch(NumberFormatException e)
		{
			offsetDays = 0;
		}

		CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
		String currencyCode = "";

		if (this.applicationName.indexOf("bidboard") >= 0) {
		   // Will need to get the currency code from the vendor
		} else {
		    UserProfile user = UserManager.getInstance().getUser(organizationId, userId);
		    currencyCode = currencyManager.getCurrencyCodeForCountry(user.getLocale());
		}

		if (Utility.isEmpty(currencyCode)) {
		    currencyCode = propertiesManager.getProperty("VOUCHER DEFAULTS","CurrencyCode","");
		}
		if (Utility.isEmpty(currencyCode)) {
		    currencyCode = currencyManager.getBaseCurrencyCode();
		}

		incomingRequest.put("InvoiceHeader_icIvcHeader", ukg.getUniqueKey().toString());
		incomingRequest.put("InvoiceHeader_icPoHeader", (String) incomingRequest.get("PoHeader_icPoHeader"));
		incomingRequest.put("InvoiceHeader_icHeaderHistory", ukg.getUniqueKey().toString());
		incomingRequest.put("InvoiceHeader_invoiceNumber", "N/A");
		if (organizationId.equalsIgnoreCase("wpc08p"))
		{
			incomingRequest.put("InvoiceHeader_invoiceDate", today);
		}
		//incomingRequest.put("InvoiceHeader_invoiceDate", today);
		incomingRequest.put("InvoiceHeader_invoiceTime", Dates.getTimeString(today));
		incomingRequest.put("InvoiceHeader_status", DocumentStatus.IVC_INPROGRESS);
		incomingRequest.put("InvoiceHeader_dateEntered", today);
		incomingRequest.put("InvoiceHeader_invoiceType", "D");
		incomingRequest.put("InvoiceHeader_invoiceReceivedDate", today);
		incomingRequest.put("InvoiceHeader_paymentDueDate", Dates.add(today, (int) offsetDays, userDateFormat));
		incomingRequest.put("InvoiceHeader_currencyCode", currencyCode);
		incomingRequest.put("InvoiceHeader_currencyFactor", String.valueOf(currencyManager.getCurrencyFactor(currencyCode)));

		if (userId.indexOf("@") > 0)
		{
			//leave empty if entered by a supplier
			incomingRequest.put("InvoiceHeader_enteredBy", "");
		}
		else
		{
			incomingRequest.put("InvoiceHeader_enteredBy", userId);
		}


		incomingRequest.put("InvoiceLine_icIvcHeader",(String) incomingRequest.get("InvoiceHeader_icIvcHeader"));

		return null;
	}

}