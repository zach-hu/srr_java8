/*
 * Created on March 25, 2004
 * @author Kelli
 * com.tsa.puridiom.vendorqualification.tasks.VendorSetup.java
 */

package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorSetup extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String	userId = (String) incomingRequest.get("userId");
			String	today = Dates.today("", "");
			VendorRegister	vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

			if (incomingRequest.containsKey("newVendorRegister_vendorId"))
			{
				incomingRequest.put("Vendor_vendorId", (String) incomingRequest.get("newVendorRegister_vendorId"));
			}
			else
			{
				incomingRequest.put("Vendor_vendorId", vendorRegister.getComp_id().getVendorId());
			}

			incomingRequest.put("Vendor_vendorName", vendorRegister.getVendorName());
			incomingRequest.put("Vendor_performance", "0");
			incomingRequest.put("Vendor_vendorClass", vendorRegister.getVendorClass());
			incomingRequest.put("Vendor_fobId", vendorRegister.getVendorFobId());
			incomingRequest.put("Vendor_vendTerms", vendorRegister.getVendorVendTerms());
			incomingRequest.put("Vendor_shipVia", vendorRegister.getVendorShipVia());
			incomingRequest.put("Vendor_printFaxCode", vendorRegister.getVendorPrintFaxCode());
			incomingRequest.put("Vendor_faxNumber", vendorRegister.getVendorFaxNumber());
			incomingRequest.put("Vendor_vendorAccount", "");
			incomingRequest.put("Vendor_ediVendor", vendorRegister.getVendorEdiVendor());
			incomingRequest.put("Vendor_taxCode", "");
			incomingRequest.put("Vendor_currencyCode", "");
			incomingRequest.put("Vendor_vendorSic", vendorRegister.getVendorSic());
			incomingRequest.put("Vendor_vendorDuns", vendorRegister.getVendorDuns());
			incomingRequest.put("Vendor_vendorEin", vendorRegister.getVendorEin());
			incomingRequest.put("Vendor_inspectionReqd", "");
			incomingRequest.put("Vendor_poCopies", "0");
			incomingRequest.put("Vendor_printPrices", "");
			incomingRequest.put("Vendor_leadDays", vendorRegister.getVendorLeadDays().toString());
			incomingRequest.put("Vendor_yearsInBusiness", vendorRegister.getVendorYears().toString());
			incomingRequest.put("Vendor_yearsAsVendor", "0");
			incomingRequest.put("Vendor_lastActive", today);
			incomingRequest.put("Vendor_lastChange", today);
			incomingRequest.put("Vendor_vendUdf1", vendorRegister.getVendorUdf1());
			incomingRequest.put("Vendor_vendUdf2", vendorRegister.getVendorUdf2());
			incomingRequest.put("Vendor_vendUdf3", vendorRegister.getVendorUdf3());
			incomingRequest.put("Vendor_vendUdf4", vendorRegister.getVendorUdf4());
			incomingRequest.put("Vendor_vendUdf5", vendorRegister.getVendorUdf5());
			incomingRequest.put("Vendor_vendUdf6", vendorRegister.getVendorUdf6());
			incomingRequest.put("Vendor_vendUdf7", vendorRegister.getVendorUdf7());
			incomingRequest.put("Vendor_vendUdf8", vendorRegister.getVendorUdf8());
			incomingRequest.put("Vendor_vendUdf9", vendorRegister.getVendorUdf9());
			incomingRequest.put("Vendor_vendUdf10", vendorRegister.getVendorUdf10());
			incomingRequest.put("Vendor_dateEntered", today);
			incomingRequest.put("Vendor_dateExpires", today);
			incomingRequest.put("Vendor_status", "02");
			incomingRequest.put("Vendor_owner", "PURIDIOM-BB");
			incomingRequest.put("Vendor_notes", vendorRegister.getVendorNotes());
			incomingRequest.put("Vendor_vendor1099", vendorRegister.getVendor1099());
			incomingRequest.put("Vendor_apReference", "");
			incomingRequest.put("Vendor_ediAddress", vendorRegister.getVendorEdiAddress());
			incomingRequest.put("Vendor_emailAddress", "");
			incomingRequest.put("Vendor_webAddress", vendorRegister.getVendorWebAddress());
			incomingRequest.put("Vendor_parentCode", "");
			incomingRequest.put("Vendor_pcardCode", "");
			incomingRequest.put("Vendor_lastChangedBy", userId);
			incomingRequest.put("Vendor_apBatchid", "");
			incomingRequest.put("Vendor_refCompanyName", vendorRegister.getRefCompanyName());
			incomingRequest.put("Vendor_refPhoneNumber", vendorRegister.getRefPhoneNumber());
			incomingRequest.put("Vendor_refContactName", vendorRegister.getRefContactName());
			incomingRequest.put("Vendor_vendorNaics", vendorRegister.getVendorNaics());
			incomingRequest.put("Vendor_diversityProgram", vendorRegister.getDiversityProgram());
			incomingRequest.put("Vendor_subcontract", vendorRegister.getSubcontract());
			incomingRequest.put("Vendor_ownershipType", vendorRegister.getOwnershipType());
			incomingRequest.put("Vendor_diverseCertOrgs", vendorRegister.getDiverseCertOrgs());
			incomingRequest.put("Vendor_eftAccountNumber", vendorRegister.getEftAccountNumber());
			incomingRequest.put("Vendor_eftAccountType", vendorRegister.getEftAccountType());
			incomingRequest.put("Vendor_eftBankName", vendorRegister.getEftBankName());
			incomingRequest.put("Vendor_eftPersonName", vendorRegister.getEftPersonName());
			incomingRequest.put("Vendor_eftRoutingNumber", vendorRegister.getEftRoutingNumber());
			incomingRequest.put("Vendor_eftWireAccount", vendorRegister.getEftWireAccount());
			incomingRequest.put("Vendor_vendPaymentType", vendorRegister.getVendPaymentType());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
