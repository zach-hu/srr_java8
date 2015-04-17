/**
 *
 * Created on August 24, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoice.tasks.InvoiceCreateFromOrderSetup.java
 *
 */
package com.tsa.puridiom.invoice.tasks;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceCreateFromOrderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		String organizationId = (String) incomingRequest.get("organizationId");
		String userDateFormat = (String) incomingRequest.get("userDateFormat");

		if (Utility.isEmpty(userDateFormat)) {
			userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(userDateFormat);

		PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
		//set history ics

		incomingRequest.put("InvoiceHeader_poNumber", poHeader.getPoNumber());
		incomingRequest.put("InvoiceHeader_poRelease", poHeader.getReleaseNumber().toString());
		incomingRequest.put("InvoiceHeader_poDate", formatter.format(HiltonUtility.ckNull(poHeader.getPoDate())));
		incomingRequest.put("InvoiceHeader_poTotal", poHeader.getTotal().toString());
		incomingRequest.put("InvoiceHeader_shipToCode", poHeader.getShipToCode());
		incomingRequest.put("InvoiceHeader_shipToContact", poHeader.getShipToContact());
		incomingRequest.put("InvoiceHeader_billToCode", poHeader.getBillToCode());
		incomingRequest.put("InvoiceHeader_billToContact", poHeader.getBillToContact());
		incomingRequest.put("InvoiceHeader_vendorId", poHeader.getVendorId());
		incomingRequest.put("InvoiceHeader_vendorName", poHeader.getVendorName());
		incomingRequest.put("InvoiceHeader_fobCode", poHeader.getFobCode());
		incomingRequest.put("InvoiceHeader_termsCode", poHeader.getTermsCode());
		incomingRequest.put("InvoiceHeader_udf6Code", poHeader.getUdf6Code());
		if(organizationId.equalsIgnoreCase("WPC08P"))
		{
			incomingRequest.put("InvoiceHeader_invoiceDesc", "");
		}
		else
		{
			incomingRequest.put("InvoiceHeader_invoiceDesc", poHeader.getInternalComments());
		}
		if(organizationId.equalsIgnoreCase("HOY08P"))
		{
			incomingRequest.put("InvoiceHeader_invoiceDiscount", poHeader.getDiscountAmount().toString());
			incomingRequest.put("InvoiceHeader_icHeaderHistory", poHeader.getIcHeaderHistory().toString());
		}
		incomingRequest.put("InvoiceHeader_prePaid", poHeader.getPrePaid());
		incomingRequest.put("InvoiceHeader_pcardName", poHeader.getPcardName());
		incomingRequest.put("InvoiceHeader_pcardHolder", poHeader.getPcardHolder());
		incomingRequest.put("InvoiceHeader_pcardNumber", poHeader.getPcardNumber());
		incomingRequest.put("InvoiceHeader_pcardExpires", poHeader.getPcardExpires());
		incomingRequest.put("InvoiceHeader_departmentCode", poHeader.getDepartmentCode());
		incomingRequest.put("InvoiceHeader_fiscalYear", poHeader.getFiscalYear());
		incomingRequest.put("InvoiceHeader_obmoNumber", poHeader.getObligNum());

		if (poHeader.getObligDate() != null)
		{
			incomingRequest.put("InvoiceHeader_obmoDate", formatter.format(poHeader.getObligDate()));
		}

		incomingRequest.put("InvoiceHeader_obmoTotal", poHeader.getObligAmt().toString());
		incomingRequest.put("InvoiceHeader_currencyCode", poHeader.getCurrencyCode());

		incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
		if (!Utility.isEmpty(poHeader.getVendorId()))
		{
			incomingRequest.put("InvoiceVendor_vendorId", poHeader.getVendorId());
			incomingRequest.put("InvoiceVendor_vendorName", poHeader.getVendorName());
			incomingRequest.put("InvoiceAddress_vendorId", poHeader.getVendorId());
			incomingRequest.put("InvoiceAddress_addressCode", poHeader.getContactAddr());
			incomingRequest.put("InvoiceHeader_vendorAddrCode", poHeader.getContactAddr());

			incomingRequest.put("Vendor_vendorId", poHeader.getVendorId());
			incomingRequest.put("Address_addressType", poHeader.getVendorId());
			incomingRequest.put("Address_addressCode", poHeader.getContactAddr());
		}

		incomingRequest.put("InvoiceHeader_orderByName", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), poHeader.getBuyerCode()).getDisplayName());
		incomingRequest.put("InvoiceHeader_orderByEmail", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), poHeader.getBuyerCode()).getMailId());
		incomingRequest.put("InvoiceHeader_orderByPhone", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), poHeader.getBuyerCode()).getPhoneNumber());

		incomingRequest.put("InvoiceHeader_udf4Code", poHeader.getUdf4Code());

		incomingRequest.put("Account_icHeader", poHeader.getIcPoHeader().toString());
		incomingRequest.put("Account_icLine", "0");
		incomingRequest.put("newAccount_icHeader", (String)incomingRequest.get("InvoiceHeader_icIvcHeader"));
		incomingRequest.put("newAccount_icLine", "0");
		incomingRequest.put("newAccount_accountType", "IVH");

		incomingRequest.put("PaymentTerm_termsCode", poHeader.getTermsCode());

//		Log.debug(poHeader, "PoHeader values ----->  PoHeader_vendorId = " + poHeader.getVendorId() + "; PoHeader_vendorName = " + poHeader.getVendorName() + "; PoHeader_contactAddr" + poHeader.getContactAddr());
//		Log.debug(poHeader, "setted values   ----->  InvoiceVendor_vendorId = " + poHeader.getVendorId() + " [PoHeader_vendorId]; InvoiceVendor_vendorName = " + poHeader.getVendorName() + "[PoHeader_vendorName]; " +
//				"InvoiceAddress_vendorId" + poHeader.getContactAddr() + "[PoHeader_vendorId]; InvoiceAddress_addressCode = "+poHeader.getContactAddr()+"[PoHeader_contactAddr]; "+
//				"InvoiceHeader_vendorAddrCode = "+poHeader.getContactAddr()+"[PoHeader_contactAddr]; Vendor_vendorId = "+poHeader.getVendorId()+"[PoHeader_vendorId]; "+
//				"Address_addressType = "+poHeader.getVendorId()+"[PoHeader_vendorId]; Address_addressCode = "+poHeader.getContactAddr()+"[PoHeader_contactAddr]");

		this.setStatus(Status.SUCCEEDED);

		return null;
	}
}