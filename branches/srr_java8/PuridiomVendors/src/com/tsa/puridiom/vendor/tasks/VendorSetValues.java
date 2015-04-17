package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Vendor vendor = (Vendor) incomingRequest.get("vendor");
			if (vendor == null)
			{
				vendor = new Vendor();
			}

			if (incomingRequest.containsKey("Vendor_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("Vendor_vendorId");
				vendor.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("Vendor_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("Vendor_vendorName");
				vendor.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("Vendor_performance"))
			{
				String performanceString = (String) incomingRequest.get("Vendor_performance");
				if (Utility.isEmpty(performanceString))
				{
					performanceString = "0";
				}
				BigDecimal performance = new BigDecimal ( performanceString );
				vendor.setPerformance(performance);
			}
			if (incomingRequest.containsKey("Vendor_vendorClass"))
			{
				String vendorClass = (String) incomingRequest.get("Vendor_vendorClass");
				vendor.setVendorClass(vendorClass);
			}
			if (incomingRequest.containsKey("Vendor_fobId"))
			{
				String fobId = (String) incomingRequest.get("Vendor_fobId");
				vendor.setFobId(fobId);
			}
			if (incomingRequest.containsKey("Vendor_vendTerms"))
			{
				String vendTerms = (String) incomingRequest.get("Vendor_vendTerms");
				vendor.setVendTerms(vendTerms);
			}
			if (incomingRequest.containsKey("Vendor_shipVia"))
			{
				String shipVia = (String) incomingRequest.get("Vendor_shipVia");
				vendor.setShipVia(shipVia);
			}
			if (incomingRequest.containsKey("Vendor_printFaxCode"))
			{
				String printFaxCode = (String) incomingRequest.get("Vendor_printFaxCode");
				vendor.setPrintFaxCode(printFaxCode);
			}
			if (incomingRequest.containsKey("Vendor_faxNumber"))
			{
				String faxNumber = (String) incomingRequest.get("Vendor_faxNumber");
				vendor.setFaxNumber(faxNumber);
			}
			if (incomingRequest.containsKey("Vendor_vendorAccount"))
			{
				String vendorAccount = (String) incomingRequest.get("Vendor_vendorAccount");
				vendor.setVendorAccount(vendorAccount);
			}
			if (incomingRequest.containsKey("Vendor_ediVendor"))
			{
				String ediVendor = (String) incomingRequest.get("Vendor_ediVendor");
				vendor.setEdiVendor(ediVendor);
			}
			if (incomingRequest.containsKey("Vendor_taxCode"))
			{
				String taxCode = (String) incomingRequest.get("Vendor_taxCode");
				vendor.setTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("Vendor_currencyCode"))
			{
				String currencyCode = (String) incomingRequest.get("Vendor_currencyCode");
				vendor.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("Vendor_vendorSic"))
			{
				String vendorSic = (String) incomingRequest.get("Vendor_vendorSic");
				vendor.setVendorSic(vendorSic);
			}
			if (incomingRequest.containsKey("Vendor_vendorDuns"))
			{
				String vendorDuns = (String) incomingRequest.get("Vendor_vendorDuns");
				vendor.setVendorDuns(vendorDuns);
			}
			if (incomingRequest.containsKey("Vendor_vendorEin"))
			{
				String vendorEin = (String) incomingRequest.get("Vendor_vendorEin");
				vendor.setVendorEin(vendorEin);
			}
			if (incomingRequest.containsKey("Vendor_inspectionReqd"))
			{
				String inspectionReqd = (String) incomingRequest.get("Vendor_inspectionReqd");
				vendor.setInspectionReqd(inspectionReqd);
			}
			if (incomingRequest.containsKey("Vendor_poCopies"))
			{
				String poCopiesString = (String) incomingRequest.get("Vendor_poCopies");
				if (Utility.isEmpty(poCopiesString))
				{
					poCopiesString = "0";
				}
				BigDecimal poCopies = new BigDecimal ( poCopiesString );
				vendor.setPoCopies(poCopies);
			}
			if (incomingRequest.containsKey("Vendor_printPrices"))
			{
				String printPrices = (String) incomingRequest.get("Vendor_printPrices");
				vendor.setPrintPrices(printPrices);
			}
			if (incomingRequest.containsKey("Vendor_leadDays"))
			{
				String leadDaysString = (String) incomingRequest.get("Vendor_leadDays");
				if (Utility.isEmpty(leadDaysString))
				{
					leadDaysString = "0";
				}
				BigDecimal leadDays = new BigDecimal ( leadDaysString );
				vendor.setLeadDays(leadDays);
			}
			if (incomingRequest.containsKey("Vendor_yearsInBusiness"))
			{
				String yearsInBusinessString = (String) incomingRequest.get("Vendor_yearsInBusiness");
				if (Utility.isEmpty(yearsInBusinessString))
				{
					yearsInBusinessString = "0";
				}
				BigDecimal yearsInBusiness = new BigDecimal ( yearsInBusinessString );
				vendor.setYearsInBusiness(yearsInBusiness);
			}
			if (incomingRequest.containsKey("Vendor_yearsAsVendor"))
			{
				String yearsAsVendorString = (String) incomingRequest.get("Vendor_yearsAsVendor");
				if (Utility.isEmpty(yearsAsVendorString))
				{
					yearsAsVendorString = "0";
				}
				BigDecimal yearsAsVendor = new BigDecimal ( yearsAsVendorString );
				vendor.setYearsAsVendor(yearsAsVendor);
			}
			if (incomingRequest.containsKey("Vendor_lastActive"))
			{
				String lastActiveString = (String) incomingRequest.get("Vendor_lastActive");
				Date lastActive = Dates.getDate(lastActiveString);
				vendor.setLastActive(lastActive);
			}
			if (incomingRequest.containsKey("Vendor_lastChange"))
			{
				String lastChangeString = (String) incomingRequest.get("Vendor_lastChange");
				Date lastChange = Dates.getDate(lastChangeString);
				vendor.setLastChange(lastChange);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf1"))
			{
				String vendUdf1 = (String) incomingRequest.get("Vendor_vendUdf1");
				vendor.setVendUdf1(vendUdf1);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf2"))
			{
				String vendUdf2 = (String) incomingRequest.get("Vendor_vendUdf2");
				vendor.setVendUdf2(vendUdf2);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf3"))
			{
				String vendUdf3 = (String) incomingRequest.get("Vendor_vendUdf3");
				vendor.setVendUdf3(vendUdf3);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf4"))
			{
				String vendUdf4 = (String) incomingRequest.get("Vendor_vendUdf4");
				vendor.setVendUdf4(vendUdf4);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf5"))
			{
				String vendUdf5 = (String) incomingRequest.get("Vendor_vendUdf5");
				vendor.setVendUdf5(vendUdf5);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf6"))
			{
				String vendUdf6 = (String) incomingRequest.get("Vendor_vendUdf6");
				vendor.setVendUdf6(vendUdf6);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf7"))
			{
				String vendUdf7 = (String) incomingRequest.get("Vendor_vendUdf7");
				vendor.setVendUdf7(vendUdf7);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf8"))
			{
				String vendUdf8 = (String) incomingRequest.get("Vendor_vendUdf8");
				vendor.setVendUdf8(vendUdf8);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf9"))
			{
				String vendUdf9 = (String) incomingRequest.get("Vendor_vendUdf9");
				vendor.setVendUdf9(vendUdf9);
			}
			if (incomingRequest.containsKey("Vendor_vendUdf10"))
			{
				String vendUdf10 = (String) incomingRequest.get("Vendor_vendUdf10");
				vendor.setVendUdf10(vendUdf10);
			}
			if (incomingRequest.containsKey("Vendor_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Vendor_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				vendor.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Vendor_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Vendor_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				vendor.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Vendor_status"))
			{
				String status = (String) incomingRequest.get("Vendor_status");
				vendor.setStatus(status);
			}
			if (incomingRequest.containsKey("Vendor_owner"))
			{
				String owner = (String) incomingRequest.get("Vendor_owner");
				vendor.setOwner(owner);
			}
			if (incomingRequest.containsKey("Vendor_notes"))
			{
				String notes = (String) incomingRequest.get("Vendor_notes");
				vendor.setNotes(notes);
			}
			if (incomingRequest.containsKey("Vendor_vendor1099"))
			{
				String vendor1099 = (String) incomingRequest.get("Vendor_vendor1099");
				vendor.setVendor1099(vendor1099);
			}
			if (incomingRequest.containsKey("Vendor_apReference"))
			{
				String apReference = (String) incomingRequest.get("Vendor_apReference");
				vendor.setApReference(apReference);
			}
			if (incomingRequest.containsKey("Vendor_ediAddress"))
			{
				String ediAddress = (String) incomingRequest.get("Vendor_ediAddress");
				vendor.setEdiAddress(ediAddress);
			}
			if (incomingRequest.containsKey("Vendor_emailAddress"))
			{
				String emailAddress = (String) incomingRequest.get("Vendor_emailAddress");
				vendor.setEmailAddress(emailAddress);
			}
			if (incomingRequest.containsKey("Vendor_webAddress"))
			{
				String webAddress = (String) incomingRequest.get("Vendor_webAddress");
				vendor.setWebAddress(webAddress);
			}
			if (incomingRequest.containsKey("Vendor_parentCode"))
			{
				String parentCode = (String) incomingRequest.get("Vendor_parentCode");
				vendor.setParentCode(parentCode);
			}
			if (incomingRequest.containsKey("Vendor_pcardCode"))
			{
				String pcardCode = (String) incomingRequest.get("Vendor_pcardCode");
				vendor.setPcardCode(pcardCode);
			}
			if (incomingRequest.containsKey("Vendor_lastChangedBy"))
			{
				String lastChangedBy = (String) incomingRequest.get("Vendor_lastChangedBy");
				vendor.setLastChangedBy(lastChangedBy);
			}
			if (incomingRequest.containsKey("Vendor_apBatchid"))
			{
				String apBatchid = (String) incomingRequest.get("Vendor_apBatchid");
				vendor.setApBatchid(apBatchid);
			}
			if (incomingRequest.containsKey("Vendor_refCompanyName"))
			{
				String refCompanyName = (String) incomingRequest.get("Vendor_refCompanyName");
				vendor.setRefCompanyName(refCompanyName);
			}
			if (incomingRequest.containsKey("Vendor_refPhoneNumber"))
			{
				String refPhoneNumber = (String) incomingRequest.get("Vendor_refPhoneNumber");
				vendor.setRefPhoneNumber(refPhoneNumber);
			}
			if (incomingRequest.containsKey("Vendor_refContactName"))
			{
				String refContactName = (String) incomingRequest.get("Vendor_refContactName");
				vendor.setRefContactName(refContactName);
			}
			if (incomingRequest.containsKey("Vendor_vendorNaics"))
			{
				String vendorNaics = (String) incomingRequest.get("Vendor_vendorNaics");
				vendor.setVendorNaics(vendorNaics);
			}
			if (incomingRequest.containsKey("Vendor_diversityProgram"))
			{
				String diversityProgram = (String) incomingRequest.get("Vendor_diversityProgram");
				vendor.setDiversityProgram(diversityProgram);
			}
			if (incomingRequest.containsKey("Vendor_subcontract"))
			{
				String subcontract = (String) incomingRequest.get("Vendor_subcontract");
				vendor.setSubcontract(subcontract);
			}
			if (incomingRequest.containsKey("Vendor_ownershipType"))
			{
				String ownershipType = (String) incomingRequest.get("Vendor_ownershipType");
				vendor.setOwnershipType(ownershipType);
			}
			if (incomingRequest.containsKey("Vendor_diverseCertOrgs"))
			{
				String diverseCertOrgs = (String) incomingRequest.get("Vendor_diverseCertOrgs");
				vendor.setDiverseCertOrgs(diverseCertOrgs);
			}
			if (incomingRequest.containsKey("Vendor_businessType"))
			{
				String businessType = (String) incomingRequest.get("Vendor_businessType");
				vendor.setBusinessType(businessType);
			}
			if (incomingRequest.containsKey("Vendor_digitalSig"))
			{
				String digitalSig = (String) incomingRequest.get("Vendor_digitalSig");
				vendor.setDigitalSig(digitalSig);
			}
			if (incomingRequest.containsKey("Vendor_termsAccepted"))
			{
				String termsAccepted = (String) incomingRequest.get("Vendor_termsAccepted");
				vendor.setTermsAccepted(termsAccepted);
			}
			if (incomingRequest.containsKey("Vendor_validated"))
			{
				String validated = (String) incomingRequest.get("Vendor_validated");
				vendor.setValidated(validated);
			}
			if (incomingRequest.containsKey("Vendor_vendorRating"))
			{
                BigDecimal vendorRating = new BigDecimal (0);
			    Object vendorRatingObj = incomingRequest.get("Vendor_vendorRating");
                if (vendorRatingObj instanceof String)
                {
                    String vendorRatingString = (String) vendorRatingObj;
                    if (Utility.isEmpty(vendorRatingString))
                    {
                        vendorRatingString = "0";
                    }
                    vendorRating = new BigDecimal ( vendorRatingString );
                } else if (vendorRatingObj instanceof BigDecimal)
                {
                    vendorRating = (BigDecimal) vendorRatingObj;
                }
				vendor.setVendorRating(vendorRating);
			}
			if (incomingRequest.containsKey("Vendor_rated"))
			{
				String rated = (String) incomingRequest.get("Vendor_rated");
				vendor.setRated(rated);
			}
			if (incomingRequest.containsKey("Vendor_iclLevel"))
            {
                String iclLevelString = (String) incomingRequest.get("Vendor_iclLevel");
                if (Utility.isEmpty(iclLevelString))
                {
                	iclLevelString = "0";
                }
                BigDecimal iclLevel = new BigDecimal(iclLevelString);
                vendor.setIclLevel(iclLevel);
            }
			if (incomingRequest.containsKey("Vendor_vendorType"))
			{
				String vendorType = (String) incomingRequest.get("Vendor_vendorType");
				vendor.setVendorType(vendorType);
			}
			if (incomingRequest.containsKey("Vendor_buyer"))
			{
				String vendorBuyer = (String) incomingRequest.get("Vendor_buyer");
				vendor.setBuyer(vendorBuyer);
			}
			if (incomingRequest.containsKey("Vendor_eftAccountNumber"))
			{
				String eftAccountNumber = (String) incomingRequest.get("Vendor_eftAccountNumber");
				vendor.setEftAccountNumber(eftAccountNumber);
			}
			if (incomingRequest.containsKey("Vendor_eftAccountType"))
			{
				String eftAccountType = (String) incomingRequest.get("Vendor_eftAccountType");
				vendor.setEftAccountType(eftAccountType);
			}
			if (incomingRequest.containsKey("Vendor_eftBankName"))
			{
				String eftBankName = (String) incomingRequest.get("Vendor_eftBankName");
				vendor.setEftBankName(eftBankName);
			}
			if (incomingRequest.containsKey("Vendor_eftPersonName"))
			{
				String eftPersonName = (String) incomingRequest.get("Vendor_eftPersonName");
				vendor.setEftPersonName(eftPersonName);
			}
			if (incomingRequest.containsKey("Vendor_eftRoutingNumber"))
			{
				String eftRoutingNumber = (String) incomingRequest.get("Vendor_eftRoutingNumber");
				vendor.setEftRoutingNumber(eftRoutingNumber);
			}
			if (incomingRequest.containsKey("Vendor_eftWireAccount"))
			{
				String eftWireAccount = (String) incomingRequest.get("Vendor_eftWireAccount");
				vendor.setEftWireAccount(eftWireAccount);
			}
			if (incomingRequest.containsKey("Vendor_vendPaymentType"))
			{
				String vendPaymentType = (String) incomingRequest.get("Vendor_vendPaymentType");
				vendor.setVendPaymentType(vendPaymentType);
			}

			result = vendor;
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
