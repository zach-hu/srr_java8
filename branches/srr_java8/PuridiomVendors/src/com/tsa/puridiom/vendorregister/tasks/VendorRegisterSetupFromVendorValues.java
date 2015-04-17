package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class VendorRegisterSetupFromVendorValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String today = Dates.today("", "");

		    incomingRequest.put("Vendor_vendorId", "");
			incomingRequest.put("Vendor_vendorName", "");
			incomingRequest.put("Vendor_performance", "0");
			incomingRequest.put("Vendor_vendorClass", "");
			incomingRequest.put("Vendor_fobId", "");
			incomingRequest.put("Vendor_vendTerms", "");
			incomingRequest.put("Vendor_shipVia", "");
			incomingRequest.put("Vendor_printFaxCode", "");
			incomingRequest.put("Vendor_faxNumber", "");
			incomingRequest.put("Vendor_vendorAccount", "");
			incomingRequest.put("Vendor_ediVendor", "");
			incomingRequest.put("Vendor_taxCode", "");
			incomingRequest.put("Vendor_currencyCode", "");
			incomingRequest.put("Vendor_vendorSic", "");
			incomingRequest.put("Vendor_vendorDuns", "");
			incomingRequest.put("Vendor_vendorEin", "");
			incomingRequest.put("Vendor_inspectionReqd", "");
			incomingRequest.put("Vendor_poCopies", "0");
			incomingRequest.put("Vendor_printPrices", "");
			incomingRequest.put("Vendor_leadDays", "0");
			incomingRequest.put("Vendor_yearsInBusiness", "0");
			incomingRequest.put("Vendor_yearsAsVendor", "0");
			incomingRequest.put("Vendor_lastActive", today);
			incomingRequest.put("Vendor_lastChange", today);
			incomingRequest.put("Vendor_vendUdf1", "");
			incomingRequest.put("Vendor_vendUdf2", "");
			incomingRequest.put("Vendor_vendUdf3", "");
			incomingRequest.put("Vendor_vendUdf4", "");
			incomingRequest.put("Vendor_vendUdf5", "");
			incomingRequest.put("Vendor_vendUdf6", "");
			incomingRequest.put("Vendor_vendUdf7", "");
			incomingRequest.put("Vendor_vendUdf8", "");
			incomingRequest.put("Vendor_vendUdf9", "");
			incomingRequest.put("Vendor_vendUdf10", "");
			incomingRequest.put("Vendor_dateEntered", today);
			incomingRequest.put("Vendor_dateExpires", today);
			incomingRequest.put("Vendor_status", "");
			incomingRequest.put("Vendor_owner", "");
			incomingRequest.put("Vendor_notes", "");
			incomingRequest.put("Vendor_vendor1099", "");
			incomingRequest.put("Vendor_apReference", "");
			incomingRequest.put("Vendor_ediAddress", "");
			incomingRequest.put("Vendor_emailAddress", "");
			incomingRequest.put("Vendor_webAddress", "");
			incomingRequest.put("Vendor_parentCode", "");
			incomingRequest.put("Vendor_pcardCode", "");
			incomingRequest.put("Vendor_lastChangedBy", "");
			incomingRequest.put("Vendor_apBatchid", "");
			incomingRequest.put("Vendor_refCompanyName", "");
			incomingRequest.put("Vendor_refPhoneNumber", "");
			incomingRequest.put("Vendor_refContactName", "");
			incomingRequest.put("Vendor_vendorNaics", "");
			incomingRequest.put("Vendor_diversityProgram", "");
			incomingRequest.put("Vendor_subcontract", "");
			incomingRequest.put("Vendor_ownershipType", "");
			incomingRequest.put("Vendor_diverseCertOrgs", "");
			incomingRequest.put("Vendor_businessType", "");
			incomingRequest.put("Vendor_digitalSig", "");



			incomingRequest.put("Contact_contactCode", "");
			incomingRequest.put("Contact_contactType", "");
			incomingRequest.put("Contact_vendorId", "");
			incomingRequest.put("Contact_lastName", "");
			incomingRequest.put("Contact_firstName", "");
			incomingRequest.put("Contact_middleInit", "");
			incomingRequest.put("Contact_salutation", "");
			incomingRequest.put("Contact_contactTitle", "");
			incomingRequest.put("Contact_phoneNumber", "");
			incomingRequest.put("Contact_phoneFormat", "");
			incomingRequest.put("Contact_faxNumber", "");
			incomingRequest.put("Contact_faxFormat", "");
			incomingRequest.put("Contact_addressCode", "");
			incomingRequest.put("Contact_dateEntered", today);
			incomingRequest.put("Contact_dateExpires", today);
			incomingRequest.put("Contact_status", "");
			incomingRequest.put("Contact_owner", "");
			incomingRequest.put("Contact_emailAddr", "");
			incomingRequest.put("Contact_info1", "");
			incomingRequest.put("Contact_info2", "");
			incomingRequest.put("Contact_info3", "");
			incomingRequest.put("Contact_info4", "");
			incomingRequest.put("Contact_contactPassword", "");
			incomingRequest.put("Contact_passChanged", "");


		    if (incomingRequest.containsKey("Vendor_vendorId")) {
		        incomingRequest.put("VendorRegister_vendorId", incomingRequest.get("Vendor_vendorId"));
		    }
		    if (incomingRequest.containsKey("Contact_emailAddr")) {
				incomingRequest.put("VendorRegister_contactEmailAddr", incomingRequest.get("Contact_emailAddr"));
		    }
		    if (incomingRequest.containsKey("Vendor_vendorName")) {
				incomingRequest.put("VendorRegister_vendorName", incomingRequest.get("Vendor_vendorName"));
		    }
		    if (incomingRequest.containsKey("Vendor_faxNumber")) {
				incomingRequest.put("VendorRegister_vendorFaxNumber", incomingRequest.get("Vendor_faxNumber"));
		    }
		    if (incomingRequest.containsKey("Vendor_vendorSic")) {
				incomingRequest.put("VendorRegister_vendorSic", incomingRequest.get("Vendor_vendorSic"));
		    }
		    if (incomingRequest.containsKey("Vendor_vendorDuns")) {
				incomingRequest.put("VendorRegister_vendorDuns", incomingRequest.get("Vendor_vendorDuns"));
		    }
		    if (incomingRequest.containsKey("Contact_lastName")) {
				incomingRequest.put("VendorRegister_contactLastName", incomingRequest.get("Contact_lastName"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactFirstName", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactMidInit", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactSalutation",incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactTitle", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactPhoneNo", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactFaxNumber", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactPassword", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_addressLine2", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_addressLine3", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_addressLine4", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_addressCity", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_addressState", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_addressZipCode", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_addressCountry", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf1", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf2", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf3", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf4", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf5", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf6", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf7", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf8", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf9", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorUdf10", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorClass", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorWebAddress", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorVendTerms", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorYears", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorLeadDays", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorEin", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorEdiVendor", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorEdiAddress", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorNotes", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altLastName", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altFirstName", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altMidInit", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altSalutation", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altTitle", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altEmailAddr", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altPhoneNo", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_altFaxNumber", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_contactType", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_refCompanyName", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_refPhoneNumber", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_refContactName", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_vendorNaics", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_diversityProgram", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_subcontract", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_ownershipType", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_diverseCertOrgs", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_businessType", incomingRequest.get("VendorRegister_vendorId"));
		    }
		    if (incomingRequest.containsKey("VendorRegister_vendorId")) {
				incomingRequest.put("VendorRegister_digitalSig", incomingRequest.get("VendorRegister_vendorId"));
		    }

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