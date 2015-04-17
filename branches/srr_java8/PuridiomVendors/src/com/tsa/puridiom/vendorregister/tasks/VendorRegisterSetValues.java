package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorRegisterSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorRegisterPK comp_id = new VendorRegisterPK();
			VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

			if (vendorRegister == null)
			{
				vendorRegister = new VendorRegister();
			}

			if (incomingRequest.containsKey("VendorRegister_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("VendorRegister_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorRegister_contactEmailAddr"))
			{
				String contactEmailAddr = (String) incomingRequest.get("VendorRegister_contactEmailAddr");
				comp_id.setContactEmailAddr(Utility.ckNull(contactEmailAddr).toLowerCase());
			}
			if (incomingRequest.containsKey("VendorRegister_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("VendorRegister_vendorName");
				vendorRegister.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorFaxNumber"))
			{
				String vendorFaxNumber = (String) incomingRequest.get("VendorRegister_vendorFaxNumber");
				vendorRegister.setVendorFaxNumber(vendorFaxNumber);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorSic"))
			{
				String vendorSic = (String) incomingRequest.get("VendorRegister_vendorSic");
				vendorRegister.setVendorSic(vendorSic);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorDuns"))
			{
				String vendorDuns = (String) incomingRequest.get("VendorRegister_vendorDuns");
				vendorRegister.setVendorDuns(vendorDuns);
			}
			if (incomingRequest.containsKey("VendorRegister_contactLastName"))
			{
				String contactLastName = (String) incomingRequest.get("VendorRegister_contactLastName");
				vendorRegister.setContactLastName(contactLastName);
			}
			if (incomingRequest.containsKey("VendorRegister_contactFirstName"))
			{
				String contactFirstName = (String) incomingRequest.get("VendorRegister_contactFirstName");
				vendorRegister.setContactFirstName(contactFirstName);
			}
			if (incomingRequest.containsKey("VendorRegister_contactMidInit"))
			{
				String contactMidInit = (String) incomingRequest.get("VendorRegister_contactMidInit");
				vendorRegister.setContactMidInit(contactMidInit);
			}
			if (incomingRequest.containsKey("VendorRegister_contactSalutation"))
			{
				String contactSalutation = (String) incomingRequest.get("VendorRegister_contactSalutation");
				vendorRegister.setContactSalutation(contactSalutation);
			}
			if (incomingRequest.containsKey("VendorRegister_contactTitle"))
			{
				String contactTitle = (String) incomingRequest.get("VendorRegister_contactTitle");
				vendorRegister.setContactTitle(contactTitle);
			}
			if (incomingRequest.containsKey("VendorRegister_contactPhoneNo"))
			{
				String contactPhoneNo = (String) incomingRequest.get("VendorRegister_contactPhoneNo");
				vendorRegister.setContactPhoneNo(contactPhoneNo);
			}
			if (incomingRequest.containsKey("VendorRegister_contactFaxNumber"))
			{
				String contactFaxNumber = (String) incomingRequest.get("VendorRegister_contactFaxNumber");
				vendorRegister.setContactFaxNumber(contactFaxNumber);
			}
			if (incomingRequest.containsKey("VendorRegister_contactPassword"))
			{
				String contactPassword = (String) incomingRequest.get("VendorRegister_contactPassword");
				vendorRegister.setContactPassword(contactPassword);
			}
			if (incomingRequest.containsKey("VendorRegister_contactInfo1"))
			{
				String contactInfo1 = (String) incomingRequest.get("VendorRegister_contactInfo1");
				vendorRegister.setContactInfo1(contactInfo1);
			}
			if (incomingRequest.containsKey("VendorRegister_contactInfo2"))
			{
				String contactInfo2 = (String) incomingRequest.get("VendorRegister_contactInfo2");
				vendorRegister.setContactInfo2(contactInfo2);
			}
			if (incomingRequest.containsKey("VendorRegister_contactInfo3"))
			{
				String contactInfo3 = (String) incomingRequest.get("VendorRegister_contactInfo3");
				vendorRegister.setContactInfo3(contactInfo3);
			}
			if (incomingRequest.containsKey("VendorRegister_contactInfo4"))
			{
				String contactInfo4 = (String) incomingRequest.get("VendorRegister_contactInfo4");
				vendorRegister.setContactInfo4(contactInfo4);
			}
			if (incomingRequest.containsKey("VendorRegister_addressLine2"))
			{
				String addressLine2 = (String) incomingRequest.get("VendorRegister_addressLine2");
				vendorRegister.setAddressLine2(addressLine2);
			}
			if (incomingRequest.containsKey("VendorRegister_addressLine3"))
			{
				String addressLine3 = (String) incomingRequest.get("VendorRegister_addressLine3");
				vendorRegister.setAddressLine3(addressLine3);
			}
			if (incomingRequest.containsKey("VendorRegister_addressLine4"))
			{
				String addressLine4 = (String) incomingRequest.get("VendorRegister_addressLine4");
				vendorRegister.setAddressLine4(addressLine4);
			}
			if (incomingRequest.containsKey("VendorRegister_addressCity"))
			{
				String addressCity = (String) incomingRequest.get("VendorRegister_addressCity");
				vendorRegister.setAddressCity(addressCity);
			}
			if (incomingRequest.containsKey("VendorRegister_addressState"))
			{
				String addressState = (String) incomingRequest.get("VendorRegister_addressState");
				vendorRegister.setAddressState(addressState);
			}
			if (incomingRequest.containsKey("VendorRegister_addressZipCode"))
			{
				String addressZipCode = (String) incomingRequest.get("VendorRegister_addressZipCode");
				vendorRegister.setAddressZipCode(addressZipCode);
			}
			if (incomingRequest.containsKey("VendorRegister_addressCountry"))
			{
				String addressCountry = (String) incomingRequest.get("VendorRegister_addressCountry");
				vendorRegister.setAddressCountry(addressCountry);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf1"))
			{
				String vendorUdf1 = (String) incomingRequest.get("VendorRegister_vendorUdf1");
				vendorRegister.setVendorUdf1(vendorUdf1);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf2"))
			{
				String vendorUdf2 = (String) incomingRequest.get("VendorRegister_vendorUdf2");
				vendorRegister.setVendorUdf2(vendorUdf2);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf3"))
			{
				String vendorUdf3 = (String) incomingRequest.get("VendorRegister_vendorUdf3");
				vendorRegister.setVendorUdf3(vendorUdf3);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf4"))
			{
				String vendorUdf4 = (String) incomingRequest.get("VendorRegister_vendorUdf4");
				vendorRegister.setVendorUdf4(vendorUdf4);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf5"))
			{
				String vendorUdf5 = (String) incomingRequest.get("VendorRegister_vendorUdf5");
				vendorRegister.setVendorUdf5(vendorUdf5);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf6"))
			{
				String vendorUdf6 = (String) incomingRequest.get("VendorRegister_vendorUdf6");
				vendorRegister.setVendorUdf6(vendorUdf6);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf7"))
			{
				String vendorUdf7 = (String) incomingRequest.get("VendorRegister_vendorUdf7");
				vendorRegister.setVendorUdf7(vendorUdf7);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf8"))
			{
				String vendorUdf8 = (String) incomingRequest.get("VendorRegister_vendorUdf8");
				vendorRegister.setVendorUdf8(vendorUdf8);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf9"))
			{
				String vendorUdf9 = (String) incomingRequest.get("VendorRegister_vendorUdf9");
				vendorRegister.setVendorUdf9(vendorUdf9);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorUdf10"))
			{
				String vendorUdf10 = (String) incomingRequest.get("VendorRegister_vendorUdf10");
				vendorRegister.setVendorUdf10(vendorUdf10);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorClass"))
			{
				String vendorClass = (String) incomingRequest.get("VendorRegister_vendorClass");
				vendorRegister.setVendorClass(vendorClass);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorWebAddress"))
			{
				String vendorWebAddress = (String) incomingRequest.get("VendorRegister_vendorWebAddress");
				vendorRegister.setVendorWebAddress(vendorWebAddress);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorVendTerms"))
			{
				String vendorVendTerms = (String) incomingRequest.get("VendorRegister_vendorVendTerms");
				vendorRegister.setVendorVendTerms(vendorVendTerms);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorYears"))
			{
				String vendorYearsString = (String) incomingRequest.get("VendorRegister_vendorYears");
				if (Utility.isEmpty(vendorYearsString))
				{
					vendorYearsString = "0";
				}
				BigDecimal vendorYears = new BigDecimal ( vendorYearsString );
				vendorRegister.setVendorYears(vendorYears);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorLeadDays"))
			{
				String vendorLeadDaysString = (String) incomingRequest.get("VendorRegister_vendorLeadDays");
				if (Utility.isEmpty(vendorLeadDaysString))
				{
					vendorLeadDaysString = "0";
				}
				BigDecimal vendorLeadDays = new BigDecimal ( vendorLeadDaysString );
				vendorRegister.setVendorLeadDays(vendorLeadDays);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorEin"))
			{
				String vendorEin = (String) incomingRequest.get("VendorRegister_vendorEin");
				vendorRegister.setVendorEin(vendorEin);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorEdiVendor"))
			{
				String vendorEdiVendor = (String) incomingRequest.get("VendorRegister_vendorEdiVendor");
				vendorRegister.setVendorEdiVendor(vendorEdiVendor);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorEdiAddress"))
			{
				String vendorEdiAddress = (String) incomingRequest.get("VendorRegister_vendorEdiAddress");
				vendorRegister.setVendorEdiAddress(vendorEdiAddress);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorNotes"))
			{
				String vendorNotes = (String) incomingRequest.get("VendorRegister_vendorNotes");
				vendorRegister.setVendorNotes(vendorNotes);
			}
			if (incomingRequest.containsKey("VendorRegister_altLastName"))
			{
				String altLastName = (String) incomingRequest.get("VendorRegister_altLastName");
				vendorRegister.setAltLastName(altLastName);
			}
			if (incomingRequest.containsKey("VendorRegister_altFirstName"))
			{
				String altFirstName = (String) incomingRequest.get("VendorRegister_altFirstName");
				vendorRegister.setAltFirstName(altFirstName);
			}
			if (incomingRequest.containsKey("VendorRegister_altMidInit"))
			{
				String altMidInit = (String) incomingRequest.get("VendorRegister_altMidInit");
				vendorRegister.setAltMidInit(altMidInit);
			}
			if (incomingRequest.containsKey("VendorRegister_altSalutation"))
			{
				String altSalutation = (String) incomingRequest.get("VendorRegister_altSalutation");
				vendorRegister.setAltSalutation(altSalutation);
			}
			if (incomingRequest.containsKey("VendorRegister_altTitle"))
			{
				String altTitle = (String) incomingRequest.get("VendorRegister_altTitle");
				vendorRegister.setAltTitle(altTitle);
			}
			if (incomingRequest.containsKey("VendorRegister_altEmailAddr"))
			{
				String altEmailAddr = (String) incomingRequest.get("VendorRegister_altEmailAddr");
				vendorRegister.setAltEmailAddr(Utility.ckNull(altEmailAddr).toLowerCase());
			}
			if (incomingRequest.containsKey("VendorRegister_altPhoneNo"))
			{
				String altPhoneNo = (String) incomingRequest.get("VendorRegister_altPhoneNo");
				vendorRegister.setAltPhoneNo(altPhoneNo);
			}
			if (incomingRequest.containsKey("VendorRegister_altFaxNumber"))
			{
				String altFaxNumber = (String) incomingRequest.get("VendorRegister_altFaxNumber");
				vendorRegister.setAltFaxNumber(altFaxNumber);
			}
			if (incomingRequest.containsKey("VendorRegister_altInfo1"))
			{
				String altInfo1 = (String) incomingRequest.get("VendorRegister_altInfo1");
				vendorRegister.setAltInfo1(altInfo1);
			}
			if (incomingRequest.containsKey("VendorRegister_altInfo2"))
			{
				String altInfo2 = (String) incomingRequest.get("VendorRegister_altInfo2");
				vendorRegister.setAltInfo2(altInfo2);
			}
			if (incomingRequest.containsKey("VendorRegister_altInfo3"))
			{
				String altInfo3 = (String) incomingRequest.get("VendorRegister_altInfo3");
				vendorRegister.setAltInfo3(altInfo3);
			}
			if (incomingRequest.containsKey("VendorRegister_altInfo4"))
			{
				String altInfo4 = (String) incomingRequest.get("VendorRegister_altInfo4");
				vendorRegister.setAltInfo4(altInfo4);
			}
			if (incomingRequest.containsKey("VendorRegister_contactType"))
			{
				String contactType = (String) incomingRequest.get("VendorRegister_contactType");
				vendorRegister.setContactType(contactType);
			}
			if (incomingRequest.containsKey("VendorRegister_refCompanyName"))
			{
				String refCompanyName = (String) incomingRequest.get("VendorRegister_refCompanyName");
				vendorRegister.setRefCompanyName(refCompanyName);
			}
			if (incomingRequest.containsKey("VendorRegister_refPhoneNumber"))
			{
				String refPhoneNumber = (String) incomingRequest.get("VendorRegister_refPhoneNumber");
				vendorRegister.setRefPhoneNumber(refPhoneNumber);
			}
			if (incomingRequest.containsKey("VendorRegister_refContactName"))
			{
				String refContactName = (String) incomingRequest.get("VendorRegister_refContactName");
				vendorRegister.setRefContactName(refContactName);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorNaics"))
			{
				String vendorNaics = (String) incomingRequest.get("VendorRegister_vendorNaics");
				vendorRegister.setVendorNaics(vendorNaics);
			}
			if (incomingRequest.containsKey("VendorRegister_diversityProgram"))
			{
				String diversityProgram = (String) incomingRequest.get("VendorRegister_diversityProgram");
				vendorRegister.setDiversityProgram(diversityProgram);
			}
			if (incomingRequest.containsKey("VendorRegister_subcontract"))
			{
				String subcontract = (String) incomingRequest.get("VendorRegister_subcontract");
				vendorRegister.setSubcontract(subcontract);
			}
			if (incomingRequest.containsKey("VendorRegister_ownershipType"))
			{
				String ownershipType = (String) incomingRequest.get("VendorRegister_ownershipType");
				vendorRegister.setOwnershipType(ownershipType);
			}
			if (incomingRequest.containsKey("VendorRegister_diverseCertOrgs"))
			{
				String diverseCertOrgs = (String) incomingRequest.get("VendorRegister_diverseCertOrgs");
				vendorRegister.setDiverseCertOrgs(diverseCertOrgs);
			}
			if (incomingRequest.containsKey("VendorRegister_businessType"))
			{
				String businessType = (String) incomingRequest.get("VendorRegister_businessType");
				vendorRegister.setBusinessType(businessType);
			}
			if (incomingRequest.containsKey("VendorRegister_digitalSig"))
			{
				String digitalSig = (String) incomingRequest.get("VendorRegister_digitalSig");
				vendorRegister.setDigitalSig(digitalSig);
			}
			if (incomingRequest.containsKey("VendorRegister_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("VendorRegister_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				vendorRegister.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("VendorRegister_termsAccepted"))
			{
				String termsAccepted = (String) incomingRequest.get("VendorRegister_termsAccepted");
				vendorRegister.setTermsAccepted(termsAccepted);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressLine1"))
			{
				String rtAddressLine1 = (String) incomingRequest.get("VendorRegister_rtAddressLine1");
				vendorRegister.setRtAddressLine1(rtAddressLine1);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressLine2"))
			{
				String rtAddressLine2 = (String) incomingRequest.get("VendorRegister_rtAddressLine2");
				vendorRegister.setRtAddressLine2(rtAddressLine2);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressLine3"))
			{
				String rtAddressLine3 = (String) incomingRequest.get("VendorRegister_rtAddressLine3");
				vendorRegister.setRtAddressLine3(rtAddressLine3);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressLine4"))
			{
				String rtAddressLine4 = (String) incomingRequest.get("VendorRegister_rtAddressLine4");
				vendorRegister.setRtAddressLine4(rtAddressLine4);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressCity"))
			{
				String rtAddressCity = (String) incomingRequest.get("VendorRegister_rtAddressCity");
				vendorRegister.setRtAddressCity(rtAddressCity);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressState"))
			{
				String rtAddressState = (String) incomingRequest.get("VendorRegister_rtAddressState");
				vendorRegister.setRtAddressState(rtAddressState);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressZip"))
			{
				String rtAddressZip = (String) incomingRequest.get("VendorRegister_rtAddressZip");
				vendorRegister.setRtAddressZip(rtAddressZip);
			}
			if (incomingRequest.containsKey("VendorRegister_rtAddressCountry"))
			{
				String rtAddressCountry = (String) incomingRequest.get("VendorRegister_rtAddressCountry");
				vendorRegister.setRtAddressCountry(rtAddressCountry);
			}
			if (incomingRequest.containsKey("VendorRegister_authorizedByName"))
			{
				String authorizedByName = (String) incomingRequest.get("VendorRegister_authorizedByName");
				vendorRegister.setAuthorizedByName(authorizedByName);
			}
			if (incomingRequest.containsKey("VendorRegister_authorizedByTitle"))
			{
				String authorizedByTitle = (String) incomingRequest.get("VendorRegister_authorizedByTitle");
				vendorRegister.setAuthorizedByTitle(authorizedByTitle);
			}
			if (incomingRequest.containsKey("VendorRegister_authorizedDate"))
			{
				String authorizedDateString = (String) incomingRequest.get("VendorRegister_authorizedDate");
				Date authorizedDate = Dates.getDate(authorizedDateString);
				vendorRegister.setAuthorizedDate(authorizedDate);
			}
			if (incomingRequest.containsKey("VendorRegister_status"))
			{
				String status = (String) incomingRequest.get("VendorRegister_status");
				vendorRegister.setStatus(status);
			}
			if (incomingRequest.containsKey("VendorRegister_rejectionNotes"))
			{
				String rejectionNotes = (String) incomingRequest.get("VendorRegister_rejectionNotes");
				vendorRegister.setRejectionNotes(rejectionNotes);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorFobId"))
			{
				String vendorFobId = (String) incomingRequest.get("VendorRegister_vendorFobId");
				vendorRegister.setVendorFobId(vendorFobId);
			}
			if (incomingRequest.containsKey("VendorRegister_arLastName"))
			{
				String arLastName = (String) incomingRequest.get("VendorRegister_arLastName");
				vendorRegister.setArLastName(arLastName);
			}
			if (incomingRequest.containsKey("VendorRegister_arFirstName"))
			{
				String arFirstName = (String) incomingRequest.get("VendorRegister_arFirstName");
				vendorRegister.setArFirstName(arFirstName);
			}
			if (incomingRequest.containsKey("VendorRegister_arMidInit"))
			{
				String arMidInit = (String) incomingRequest.get("VendorRegister_arMidInit");
				vendorRegister.setArMidInit(arMidInit);
			}
			if (incomingRequest.containsKey("VendorRegister_arSalutation"))
			{
				String arSalutation = (String) incomingRequest.get("VendorRegister_arSalutation");
				vendorRegister.setArSalutation(arSalutation);
			}
			if (incomingRequest.containsKey("VendorRegister_arTitle"))
			{
				String arTitle = (String) incomingRequest.get("VendorRegister_arTitle");
				vendorRegister.setArTitle(arTitle);
			}
			if (incomingRequest.containsKey("VendorRegister_arEmailAddr"))
			{
				String arEmailAddr = (String) incomingRequest.get("VendorRegister_arEmailAddr");
				vendorRegister.setArEmailAddr(Utility.ckNull(arEmailAddr).toLowerCase());
			}
			if (incomingRequest.containsKey("VendorRegister_arPhoneNo"))
			{
				String arPhoneNo = (String) incomingRequest.get("VendorRegister_arPhoneNo");
				vendorRegister.setArPhoneNo(arPhoneNo);
			}
			if (incomingRequest.containsKey("VendorRegister_arFaxNumber"))
			{
				String arFaxNumber = (String) incomingRequest.get("VendorRegister_arFaxNumber");
				vendorRegister.setArFaxNumber(arFaxNumber);
			}
			if (incomingRequest.containsKey("VendorRegister_arInfo1"))
			{
				String arInfo1 = (String) incomingRequest.get("VendorRegister_arInfo1");
				vendorRegister.setArInfo1(arInfo1);
			}
			if (incomingRequest.containsKey("VendorRegister_arInfo2"))
			{
				String arInfo2 = (String) incomingRequest.get("VendorRegister_arInfo2");
				vendorRegister.setArInfo2(arInfo2);
			}
			if (incomingRequest.containsKey("VendorRegister_arInfo3"))
			{
				String arInfo3 = (String) incomingRequest.get("VendorRegister_arInfo3");
				vendorRegister.setArInfo3(arInfo3);
			}
			if (incomingRequest.containsKey("VendorRegister_arInfo4"))
			{
				String arInfo4 = (String) incomingRequest.get("VendorRegister_arInfo4");
				vendorRegister.setArInfo4(arInfo4);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorEmailAddr"))
			{
				String vendorEmailAddr = (String) incomingRequest.get("VendorRegister_vendorEmailAddr");
				vendorRegister.setVendorEmailAddr(vendorEmailAddr);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorFobId"))
			{
				String vendorFobId = (String) incomingRequest.get("VendorRegister_vendorFobId");
				vendorRegister.setVendorFobId(vendorFobId);
			}
			if (incomingRequest.containsKey("VendorRegister_vendorShipVia"))
			{
				String vendorShipVia = (String) incomingRequest.get("VendorRegister_vendorShipVia");
				vendorRegister.setVendorShipVia(vendorShipVia);
			}
			if (incomingRequest.containsKey("VendorRegister_eftBankName"))
			{
				String eftBankName = (String) incomingRequest.get("VendorRegister_eftBankName");
				vendorRegister.setEftBankName(eftBankName);
			}
			if (incomingRequest.containsKey("VendorRegister_eftRoutingNumber"))
			{
				String eftRoutingNumber = (String) incomingRequest.get("VendorRegister_eftRoutingNumber");
				vendorRegister.setEftRoutingNumber(eftRoutingNumber);
			}
			if (incomingRequest.containsKey("VendorRegister_eftAccountNumber"))
			{
				String eftAccountNumber = (String) incomingRequest.get("VendorRegister_eftAccountNumber");
				vendorRegister.setEftAccountNumber(eftAccountNumber);
			}
			if (incomingRequest.containsKey("VendorRegister_eftAccountType"))
			{
				String eftAccountType = (String) incomingRequest.get("VendorRegister_eftAccountType");
				vendorRegister.setEftAccountType(eftAccountType);
			}
			if (incomingRequest.containsKey("VendorRegister_eftPersonName"))
			{
				String eftPersonName = (String) incomingRequest.get("VendorRegister_eftPersonName");
				vendorRegister.setEftPersonName(eftPersonName);
			}
			if (incomingRequest.containsKey("VendorRegister_eftWireAccount"))
			{
				String eftWireAccount = (String) incomingRequest.get("VendorRegister_eftWireAccount");
				vendorRegister.setEftWireAccount(eftWireAccount);
			}
			if (incomingRequest.containsKey("VendorRegister_vendor1099"))
			{
				String vendor1099 = (String) incomingRequest.get("VendorRegister_vendor1099");
				vendorRegister.setVendor1099(vendor1099);
			}
			if (incomingRequest.containsKey("VendorRegister_priorPassword"))
			{
				String priorPassword = (String) incomingRequest.get("VendorRegister_priorPassword");
				vendorRegister.setPriorPassword(priorPassword);
			}
			if (incomingRequest.containsKey("VendorRegister_passChanged"))
			{
				String passChanged = (String) incomingRequest.get("VendorRegister_passChanged");
				vendorRegister.setPassChanged(passChanged);
			}
			if (incomingRequest.containsKey("VendorRegister_lockLogin"))
			{
				String lockLogin = (String) incomingRequest.get("VendorRegister_lockLogin");
				vendorRegister.setLockLogin(lockLogin);
			}
			if (incomingRequest.containsKey("VendorRegister_vendPaymentType"))
			{
				String vendPaymentType = (String) incomingRequest.get("VendorRegister_vendPaymentType");
				vendorRegister.setVendPaymentType(vendPaymentType);
			}
			vendorRegister.setComp_id(comp_id);

			result = vendorRegister;
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
