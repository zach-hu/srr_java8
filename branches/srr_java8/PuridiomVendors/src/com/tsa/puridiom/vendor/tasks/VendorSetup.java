package com.tsa.puridiom.vendor.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class VendorSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
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
			incomingRequest.put("Vendor_lastActive", Dates.today("", ""));
			incomingRequest.put("Vendor_lastChange", Dates.today("", ""));
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
			incomingRequest.put("Vendor_dateEntered", Dates.today("", ""));
			incomingRequest.put("Vendor_dateExpires", Dates.today("", ""));
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
			incomingRequest.put("Vendor_termsAccepted", "");
			incomingRequest.put("Vendor_validated", "");
			incomingRequest.put("Vendor_vendorRating", "0");
			incomingRequest.put("Vendor_rated", "");
			incomingRequest.put("Vendor_iclLevel", "0");
			incomingRequest.put("Vendor_vendorType", "P");

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
