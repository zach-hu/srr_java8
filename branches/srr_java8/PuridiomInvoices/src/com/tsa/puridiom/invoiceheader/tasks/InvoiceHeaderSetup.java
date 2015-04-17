package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class InvoiceHeaderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
        String today = Dates.today(userDateFormat, userTimeZone);

		try
		{
			incomingRequest.put("InvoiceHeader_icIvcHeader", "0");
			incomingRequest.put("InvoiceHeader_icPoHeader", "0");
			incomingRequest.put("InvoiceHeader_invoiceNumber", "");
			incomingRequest.put("InvoiceHeader_invoiceDate", today);
			incomingRequest.put("InvoiceHeader_invoiceTime", "");
			incomingRequest.put("InvoiceHeader_invoiceDesc", "");
			incomingRequest.put("InvoiceHeader_invoiceSubtotal", "0");
			incomingRequest.put("InvoiceHeader_invoiceDiscount", "0");
			incomingRequest.put("InvoiceHeader_invoiceTax", "0");
			incomingRequest.put("InvoiceHeader_invoiceShipping", "0");
			incomingRequest.put("InvoiceHeader_invoiceOther", "0");
			incomingRequest.put("InvoiceHeader_invoiceTotal", "0");
			incomingRequest.put("InvoiceHeader_invoicePaid", "0");
			incomingRequest.put("InvoiceHeader_specialInst", "");
			incomingRequest.put("InvoiceHeader_status", "");
			incomingRequest.put("InvoiceHeader_dateEntered", today);
			incomingRequest.put("InvoiceHeader_paymentDueDate", today);
			incomingRequest.put("InvoiceHeader_termsCode", "");
			incomingRequest.put("InvoiceHeader_termsDiscperc", "0");
			incomingRequest.put("InvoiceHeader_termsDiscdays", "0");
			incomingRequest.put("InvoiceHeader_terms2Discperc", "0");
			incomingRequest.put("InvoiceHeader_terms2Discdays", "0");
			incomingRequest.put("InvoiceHeader_termsNetdays", "0");
			incomingRequest.put("InvoiceHeader_poNumber", "");
			incomingRequest.put("InvoiceHeader_poRelease", "0");
			incomingRequest.put("InvoiceHeader_poDate", today);
			incomingRequest.put("InvoiceHeader_poTotal", "0");
			incomingRequest.put("InvoiceHeader_vendorId", "");
			incomingRequest.put("InvoiceHeader_vendorName", "");
			incomingRequest.put("InvoiceHeader_contactName", "");
			incomingRequest.put("InvoiceHeader_contactEmail", "");
			incomingRequest.put("InvoiceHeader_contactPhone", "");
			incomingRequest.put("InvoiceHeader_contactFax", "");
			incomingRequest.put("InvoiceHeader_shipToCode", "");
			incomingRequest.put("InvoiceHeader_shipToContact", "");
			incomingRequest.put("InvoiceHeader_billToCode", "");
			incomingRequest.put("InvoiceHeader_billToContact", "");
			incomingRequest.put("InvoiceHeader_fobCode", "");
			incomingRequest.put("InvoiceHeader_pcardName", "");
			incomingRequest.put("InvoiceHeader_pcardHolder", "");
			incomingRequest.put("InvoiceHeader_pcardNumber", "");
			incomingRequest.put("InvoiceHeader_pcardExpires", "");
			incomingRequest.put("InvoiceHeader_eftBankName", "");
			incomingRequest.put("InvoiceHeader_eftBankAccount", "");
			incomingRequest.put("InvoiceHeader_eftAbaAch", "");
			incomingRequest.put("InvoiceHeader_eftAbaWire", "");
			incomingRequest.put("InvoiceHeader_eftHolderAccount", "");
			incomingRequest.put("InvoiceHeader_federalId", "");
			incomingRequest.put("InvoiceHeader_orderByName", "");
			incomingRequest.put("InvoiceHeader_orderByEmail", "");
			incomingRequest.put("InvoiceHeader_orderByPhone", "");
			incomingRequest.put("InvoiceHeader_ownerEmail", "");
			incomingRequest.put("InvoiceHeader_prePaid", "");
			incomingRequest.put("InvoiceHeader_udf1Code", "");
			incomingRequest.put("InvoiceHeader_udf2Code", "");
			incomingRequest.put("InvoiceHeader_udf3Code", "");
			incomingRequest.put("InvoiceHeader_udf4Code", "");
			incomingRequest.put("InvoiceHeader_udf5Code", "");
			incomingRequest.put("InvoiceHeader_udf6Code", "");
			incomingRequest.put("InvoiceHeader_udf7Code", "");
			incomingRequest.put("InvoiceHeader_udf8Code", "");
			incomingRequest.put("InvoiceHeader_udf9Code", "");
			incomingRequest.put("InvoiceHeader_udf10Code", "");
			incomingRequest.put("InvoiceHeader_invoiceRejecting", "0");
			incomingRequest.put("InvoiceHeader_reasonCode", "");
			incomingRequest.put("InvoiceHeader_apReference", "");
			incomingRequest.put("InvoiceHeader_invoiceType", "");
			incomingRequest.put("InvoiceHeader_lastExtract", "");
			incomingRequest.put("InvoiceHeader_icHeaderHistory", "0");
			incomingRequest.put("InvoiceHeader_currencyCode", "");
			incomingRequest.put("InvoiceHeader_currencyFactor", "1");
			incomingRequest.put("InvoiceHeader_fiscalYear", "") ;
			incomingRequest.put("InvoiceHeader_checkBatchId", "") ;
            incomingRequest.put("InvoiceHeader_timeZone", userTimeZone);
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
