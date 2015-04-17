package com.tsa.puridiom.vendorinsurance.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorInsuranceSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorInsurance vendorInsurance = (VendorInsurance) incomingRequest.get("vendorInsurance");
			if (vendorInsurance == null)
			{
				vendorInsurance = new VendorInsurance();
			}

			if (incomingRequest.containsKey("VendorInsurance_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("VendorInsurance_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				vendorInsurance.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("VendorInsurance_contNumber"))
			{
				String contNumber = (String ) incomingRequest.get("VendorInsurance_contNumber");
				vendorInsurance.setContNumber(contNumber);
			}
			if (incomingRequest.containsKey("VendorInsurance_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("VendorInsurance_vendorId");
				vendorInsurance.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorInsurance_contType"))
			{
				String contType = (String ) incomingRequest.get("VendorInsurance_contType");
				vendorInsurance.setContType(contType);
			}
			if (incomingRequest.containsKey("VendorInsurance_contEffective"))
			{
				String contEffectiveString = (String) incomingRequest.get("VendorInsurance_contEffective");
				if (!Utility.isEmpty(contEffectiveString)) {
					Date contEffective = Dates.getDate(contEffectiveString);
					vendorInsurance.setContEffective(contEffective);
				}
			}
			if (incomingRequest.containsKey("VendorInsurance_contExpires"))
			{
				String contExpiresString = (String) incomingRequest.get("VendorInsurance_contExpires");
				if (!Utility.isEmpty(contExpiresString)) {
					Date contExpires = Dates.getDate(contExpiresString);
					vendorInsurance.setContExpires(contExpires);
				}
			}
			if (incomingRequest.containsKey("VendorInsurance_contAdmin"))
			{
				String contAdmin = (String ) incomingRequest.get("VendorInsurance_contAdmin");
				vendorInsurance.setContAdmin(contAdmin);
			}
			if (incomingRequest.containsKey("VendorInsurance_contModFlag"))
			{
				String contModFlag = (String ) incomingRequest.get("VendorInsurance_contModFlag");
				vendorInsurance.setContModFlag(contModFlag);
			}
			if (incomingRequest.containsKey("VendorInsurance_contModUserid"))
			{
				String contModUserid = (String ) incomingRequest.get("VendorInsurance_contModUserid");
				vendorInsurance.setContModUserid(contModUserid);
			}
			if (incomingRequest.containsKey("VendorInsurance_contModDate"))
			{
				String contModDateString = (String) incomingRequest.get("VendorInsurance_contModDate");
				if (!Utility.isEmpty(contModDateString)) {
					Date contModDate = Dates.getDate(contModDateString);
					vendorInsurance.setContModDate(contModDate);
				}
			}
			if (incomingRequest.containsKey("VendorInsurance_contOwner"))
			{
				String contOwner = (String ) incomingRequest.get("VendorInsurance_contOwner");
				vendorInsurance.setContOwner(contOwner);
			}
			if (incomingRequest.containsKey("VendorInsurance_contStatus"))
			{
				String contStatus = (String ) incomingRequest.get("VendorInsurance_contStatus");
				vendorInsurance.setContStatus(contStatus);
			}
			if (incomingRequest.containsKey("VendorInsurance_contRequestDate"))
			{
				String contRequestDateString = (String) incomingRequest.get("VendorInsurance_contRequestDate");
				if (!Utility.isEmpty(contRequestDateString)) {
					Date contRequestDate = Dates.getDate(contRequestDateString);
					vendorInsurance.setContRequestDate(contRequestDate);
				}
			}
			if (incomingRequest.containsKey("VendorInsurance_contDescription"))
			{
				String contDescription = (String ) incomingRequest.get("VendorInsurance_contDescription");
				vendorInsurance.setContDescription(contDescription);
			}
			if (incomingRequest.containsKey("VendorInsurance_contUdf1"))
			{
				String contUdf1 = (String ) incomingRequest.get("VendorInsurance_contUdf1");
				vendorInsurance.setContUdf1(contUdf1);
			}
			if (incomingRequest.containsKey("VendorInsurance_contUdf2"))
			{
				String contUdf2 = (String ) incomingRequest.get("VendorInsurance_contUdf2");
				vendorInsurance.setContUdf2(contUdf2);
			}
			if (incomingRequest.containsKey("VendorInsurance_contUdf3"))
			{
				String contUdf3 = (String ) incomingRequest.get("VendorInsurance_contUdf3");
				vendorInsurance.setContUdf3(contUdf3);
			}
			if (incomingRequest.containsKey("VendorInsurance_contUdf4"))
			{
				String contUdf4 = (String ) incomingRequest.get("VendorInsurance_contUdf4");
				vendorInsurance.setContUdf4(contUdf4);
			}
			if (incomingRequest.containsKey("VendorInsurance_contUdf5"))
			{
				String contUdf5 = (String ) incomingRequest.get("VendorInsurance_contUdf5");
				vendorInsurance.setContUdf5(contUdf5);
			}
			if (incomingRequest.containsKey("VendorInsurance_insuranceStatus"))
			{
				String insuranceStatus = (String ) incomingRequest.get("VendorInsurance_insuranceStatus");
				vendorInsurance.setInsuranceStatus(insuranceStatus);
			}
			if (incomingRequest.containsKey("VendorInsurance_insuranceContact"))
			{
				String insuranceContact = (String ) incomingRequest.get("VendorInsurance_insuranceContact");
				vendorInsurance.setInsuranceContact(insuranceContact);
			}
			if (incomingRequest.containsKey("VendorInsurance_expiredLetter"))
			{
				String expiredLetterString = (String) incomingRequest.get("VendorInsurance_expiredLetter");
				if (!Utility.isEmpty(expiredLetterString)) {
					Date expiredLetter = Dates.getDate(expiredLetterString);
					vendorInsurance.setExpiredLetter(expiredLetter);
				}
			}
			if (incomingRequest.containsKey("VendorInsurance_coverage1"))
			{
				String coverage1 = (String ) incomingRequest.get("VendorInsurance_coverage1");
				vendorInsurance.setCoverage1(coverage1);
			}
			if (incomingRequest.containsKey("VendorInsurance_expires1"))
			{
				String expires1String = (String) incomingRequest.get("VendorInsurance_expires1");
				if (!Utility.isEmpty(expires1String)) {
					Date expires1 = Dates.getDate(expires1String);
					vendorInsurance.setExpires1(expires1);
				}
				else
					vendorInsurance.setExpires1(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_named1"))
			{
				String named1 = (String ) incomingRequest.get("VendorInsurance_named1");
				vendorInsurance.setNamed1(named1);
			}
			if (incomingRequest.containsKey("VendorInsurance_waiver1"))
			{
				String waiver1 = (String ) incomingRequest.get("VendorInsurance_waiver1");
				vendorInsurance.setWaiver1(waiver1);
			}
			if (incomingRequest.containsKey("VendorInsurance_limit1"))
            {
                String limit1String = (String) incomingRequest.get("VendorInsurance_limit1");
                if (Utility.isEmpty(limit1String))
                {
                	limit1String = "0";
                }
                BigDecimal limit1 = new BigDecimal(limit1String);
                vendorInsurance.setLimit1(limit1);
            }
			if (incomingRequest.containsKey("VendorInsurance_coverage2"))
			{
				String coverage2 = (String ) incomingRequest.get("VendorInsurance_coverage2");
				vendorInsurance.setCoverage2(coverage2);
			}
			if (incomingRequest.containsKey("VendorInsurance_expires2"))
			{
				String expires2String = (String) incomingRequest.get("VendorInsurance_expires2");
				if (!Utility.isEmpty(expires2String)) {
					Date expires2 = Dates.getDate(expires2String);
					vendorInsurance.setExpires2(expires2);
				}
				else
					vendorInsurance.setExpires2(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_named2"))
			{
				String named2 = (String ) incomingRequest.get("VendorInsurance_named2");
				vendorInsurance.setNamed2(named2);
			}
			if (incomingRequest.containsKey("VendorInsurance_waiver2"))
			{
				String waiver2 = (String ) incomingRequest.get("VendorInsurance_waiver2");
				vendorInsurance.setWaiver2(waiver2);
			}
			if (incomingRequest.containsKey("VendorInsurance_limit2"))
            {
                String limit2String = (String) incomingRequest.get("VendorInsurance_limit2");
                if (Utility.isEmpty(limit2String))
                {
                	limit2String = "0";
                }
                BigDecimal limit2 = new BigDecimal(limit2String);
                vendorInsurance.setLimit2(limit2);
            }
			if (incomingRequest.containsKey("VendorInsurance_coverage3"))
			{
				String coverage3 = (String ) incomingRequest.get("VendorInsurance_coverage3");
				vendorInsurance.setCoverage3(coverage3);
			}
			if (incomingRequest.containsKey("VendorInsurance_expires3"))
			{
				String expires3String = (String) incomingRequest.get("VendorInsurance_expires3");
				if (!Utility.isEmpty(expires3String)) {
					Date expires3 = Dates.getDate(expires3String);
					vendorInsurance.setExpires3(expires3);
				}
				else
					vendorInsurance.setExpires3(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_named3"))
			{
				String named3 = (String ) incomingRequest.get("VendorInsurance_named3");
				vendorInsurance.setNamed3(named3);
			}
			if (incomingRequest.containsKey("VendorInsurance_waiver3"))
			{
				String waiver3 = (String ) incomingRequest.get("VendorInsurance_waiver3");
				vendorInsurance.setWaiver3(waiver3);
			}
			if (incomingRequest.containsKey("VendorInsurance_limit3"))
            {
                String limit3String = (String) incomingRequest.get("VendorInsurance_limit3");
                if (Utility.isEmpty(limit3String))
                {
                	limit3String = "0";
                }
                BigDecimal limit3 = new BigDecimal(limit3String);
                vendorInsurance.setLimit3(limit3);
            }
			if (incomingRequest.containsKey("VendorInsurance_coverage4"))
			{
				String coverage4 = (String ) incomingRequest.get("VendorInsurance_coverage4");
				vendorInsurance.setCoverage4(coverage4);
			}
			if (incomingRequest.containsKey("VendorInsurance_expires4"))
			{
				String expires4String = (String) incomingRequest.get("VendorInsurance_expires4");
				if (!Utility.isEmpty(expires4String)) {
					Date expires4 = Dates.getDate(expires4String);
					vendorInsurance.setExpires4(expires4);
				}
				else
					vendorInsurance.setExpires4(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_named4"))
			{
				String named4 = (String ) incomingRequest.get("VendorInsurance_named4");
				vendorInsurance.setNamed4(named4);
			}
			if (incomingRequest.containsKey("VendorInsurance_waiver4"))
			{
				String waiver4 = (String ) incomingRequest.get("VendorInsurance_waiver4");
				vendorInsurance.setWaiver4(waiver4);
			}
			if (incomingRequest.containsKey("VendorInsurance_limit4"))
            {
                String limit4String = (String) incomingRequest.get("VendorInsurance_limit4");
                if (Utility.isEmpty(limit4String))
                {
                	limit4String = "0";
                }
                BigDecimal limit4 = new BigDecimal(limit4String);
                vendorInsurance.setLimit4(limit4);
            }
			if (incomingRequest.containsKey("VendorInsurance_coverage5"))
			{
				String coverage5 = (String ) incomingRequest.get("VendorInsurance_coverage5");
				vendorInsurance.setCoverage5(coverage5);
			}
			if (incomingRequest.containsKey("VendorInsurance_expires5"))
			{
				String expires5String = (String) incomingRequest.get("VendorInsurance_expires5");
				if (!Utility.isEmpty(expires5String)) {
					Date expires5 = Dates.getDate(expires5String);
					vendorInsurance.setExpires5(expires5);
				}
				else
					vendorInsurance.setExpires5(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_named5"))
			{
				String named5 = (String ) incomingRequest.get("VendorInsurance_named5");
				vendorInsurance.setNamed5(named5);
			}
			if (incomingRequest.containsKey("VendorInsurance_waiver5"))
			{
				String waiver5 = (String ) incomingRequest.get("VendorInsurance_waiver5");
				vendorInsurance.setWaiver5(waiver5);
			}
			if (incomingRequest.containsKey("VendorInsurance_limit5"))
            {
                String limit5String = (String) incomingRequest.get("VendorInsurance_limit5");
                if (Utility.isEmpty(limit5String))
                {
                	limit5String = "0";
                }
                BigDecimal limit5 = new BigDecimal(limit5String);
                vendorInsurance.setLimit5(limit5);
            }
			if (incomingRequest.containsKey("VendorInsurance_coverage6"))
			{
				String coverage6 = (String ) incomingRequest.get("VendorInsurance_coverage6");
				vendorInsurance.setCoverage6(coverage6);
			}
			if (incomingRequest.containsKey("VendorInsurance_expires6"))
			{
				String expires6String = (String) incomingRequest.get("VendorInsurance_expires6");
				if (!Utility.isEmpty(expires6String)) {
					Date expires6 = Dates.getDate(expires6String);
					vendorInsurance.setExpires6(expires6);
				}
				else
					vendorInsurance.setExpires6(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_named6"))
			{
				String named6 = (String ) incomingRequest.get("VendorInsurance_named6");
				vendorInsurance.setNamed6(named6);
			}
			if (incomingRequest.containsKey("VendorInsurance_waiver6"))
			{
				String waiver6 = (String ) incomingRequest.get("VendorInsurance_waiver6");
				vendorInsurance.setWaiver6(waiver6);
			}
			if (incomingRequest.containsKey("VendorInsurance_limit6"))
            {
                String limit6String = (String) incomingRequest.get("VendorInsurance_limit6");
                if (Utility.isEmpty(limit6String))
                {
                	limit6String = "0";
                }
                BigDecimal limit6 = new BigDecimal(limit6String);
                vendorInsurance.setLimit6(limit6);
            }
			if (incomingRequest.containsKey("VendorInsurance_certifiedDate1"))
			{
				String certifiedDate1String = (String) incomingRequest.get("VendorInsurance_certifiedDate1");
				if (!Utility.isEmpty(certifiedDate1String)) {
					Date certifiedDate1 = Dates.getDate(certifiedDate1String);
					vendorInsurance.setCertifiedDate1(certifiedDate1);
				}
				else
					vendorInsurance.setCertifiedDate1(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_certStatus1"))
			{
				String certStatus1 = (String ) incomingRequest.get("VendorInsurance_certStatus1");
				vendorInsurance.setCertStatus1(certStatus1);
			}
			if (incomingRequest.containsKey("VendorInsurance_certifiedDate2"))
			{
				String certifiedDate2String = (String) incomingRequest.get("VendorInsurance_certifiedDate2");
				if (!Utility.isEmpty(certifiedDate2String)) {
					Date certifiedDate2 = Dates.getDate(certifiedDate2String);
					vendorInsurance.setCertifiedDate2(certifiedDate2);
				}
				else
					vendorInsurance.setCertifiedDate2(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_certStatus2"))
			{
				String certStatus2 = (String ) incomingRequest.get("VendorInsurance_certStatus2");
				vendorInsurance.setCertStatus2(certStatus2);
			}
			if (incomingRequest.containsKey("VendorInsurance_certifiedDate3"))
			{
				String certifiedDate3String = (String) incomingRequest.get("VendorInsurance_certifiedDate3");
				if (!Utility.isEmpty(certifiedDate3String)) {
					Date certifiedDate3 = Dates.getDate(certifiedDate3String);
					vendorInsurance.setCertifiedDate3(certifiedDate3);
				}
				else
					vendorInsurance.setCertifiedDate3(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_certStatus3"))
			{
				String certStatus3 = (String ) incomingRequest.get("VendorInsurance_certStatus3");
				vendorInsurance.setCertStatus3(certStatus3);
			}
			if (incomingRequest.containsKey("VendorInsurance_certifiedDate4"))
			{
				String certifiedDate4String = (String) incomingRequest.get("VendorInsurance_certifiedDate4");
				if (!Utility.isEmpty(certifiedDate4String)) {
					Date certifiedDate4 = Dates.getDate(certifiedDate4String);
					vendorInsurance.setCertifiedDate4(certifiedDate4);
				}
				else
					vendorInsurance.setCertifiedDate4(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_certStatus4"))
			{
				String certStatus4 = (String ) incomingRequest.get("VendorInsurance_certStatus4");
				vendorInsurance.setCertStatus4(certStatus4);
			}
			if (incomingRequest.containsKey("VendorInsurance_certifiedDate5"))
			{
				String certifiedDate5String = (String) incomingRequest.get("VendorInsurance_certifiedDate5");
				if (!Utility.isEmpty(certifiedDate5String)) {
					Date certifiedDate5 = Dates.getDate(certifiedDate5String);
					vendorInsurance.setCertifiedDate5(certifiedDate5);
				}
				else
					vendorInsurance.setCertifiedDate5(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_certStatus5"))
			{
				String certStatus5 = (String ) incomingRequest.get("VendorInsurance_certStatus5");
				vendorInsurance.setCertStatus5(certStatus5);
			}
			if (incomingRequest.containsKey("VendorInsurance_certUdf1"))
			{
				String certUdf1 = (String ) incomingRequest.get("VendorInsurance_certUdf1");
				vendorInsurance.setCertUdf1(certUdf1);
			}
			if (incomingRequest.containsKey("VendorInsurance_certUdf2"))
			{
				String certUdf2 = (String ) incomingRequest.get("VendorInsurance_certUdf2");
				vendorInsurance.setCertUdf2(certUdf2);
			}
			if (incomingRequest.containsKey("VendorInsurance_certUdf3"))
			{
				String certUdf3 = (String ) incomingRequest.get("VendorInsurance_certUdf3");
				vendorInsurance.setCertUdf3(certUdf3);
			}
			if (incomingRequest.containsKey("VendorInsurance_certUdf4"))
			{
				String certUdf4 = (String ) incomingRequest.get("VendorInsurance_certUdf4");
				vendorInsurance.setCertUdf4(certUdf4);
			}
			if (incomingRequest.containsKey("VendorInsurance_certUdf5"))
			{
				String certUdf5 = (String ) incomingRequest.get("VendorInsurance_certUdf5");
				vendorInsurance.setCertUdf5(certUdf5);
			}
			if (incomingRequest.containsKey("VendorInsurance_certContact"))
			{
				String certContact = (String ) incomingRequest.get("VendorInsurance_certContact");
				vendorInsurance.setCertContact(certContact);
			}
			if (incomingRequest.containsKey("VendorInsurance_contractNotes"))
			{
				String contractNotes = (String ) incomingRequest.get("VendorInsurance_contractNotes");
				vendorInsurance.setContractNotes(contractNotes);
			}
			if (incomingRequest.containsKey("VendorInsurance_complianceNotes"))
			{
				String complianceNotes = (String ) incomingRequest.get("VendorInsurance_complianceNotes");
				vendorInsurance.setComplianceNotes(complianceNotes);
			}
			if (incomingRequest.containsKey("VendorInsurance_insuranceNotes"))
			{
				String insuranceNotes = (String ) incomingRequest.get("VendorInsurance_insuranceNotes");
				vendorInsurance.setInsuranceNotes(insuranceNotes);
			}
			if (incomingRequest.containsKey("VendorInsurance_notifiedDate"))
			{
				String notifiedDateString = (String) incomingRequest.get("VendorInsurance_notifiedDate");
				if (!Utility.isEmpty(notifiedDateString)) {
					Date notifiedDate = Dates.getDate(notifiedDateString);
					vendorInsurance.setNotifiedDate(notifiedDate);
				}
			}
			if (incomingRequest.containsKey("VendorInsurance_dollarValue"))
			{
				String dollarValueString = (String) incomingRequest.get("VendorInsurance_dollarValue");
				if (Utility.isEmpty(dollarValueString))
				{
					dollarValueString = "0";
				}
				BigDecimal dollarValue = new BigDecimal ( dollarValueString );
				vendorInsurance.setDollarValue(dollarValue);
			}
			if (incomingRequest.containsKey("VendorInsurance_certifiedDate6"))
			{
                String certifiedDate6String = (String) incomingRequest.get("VendorInsurance_certifiedDate6");
                if (!Utility.isEmpty(certifiedDate6String)) {
                    Date certifiedDate6 = Dates.getDate(certifiedDate6String);
                    vendorInsurance.setCertifiedDate6(certifiedDate6);
                }
                else
                	vendorInsurance.setCertifiedDate6(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_certStatus6"))
			{
				String certStatus6 = (String) incomingRequest.get("VendorInsurance_certStatus6");
				vendorInsurance.setCertStatus6(certStatus6);
			}
			if (incomingRequest.containsKey("VendorInsurance_certUdf6"))
			{
				String certUdf6 = (String) incomingRequest.get("VendorInsurance_certUdf6");
				vendorInsurance.setCertUdf6(certUdf6);
			}


			if (incomingRequest.containsKey("VendorInsurance_certifiedDate7"))
			{
                String certifiedDate7String = (String) incomingRequest.get("VendorInsurance_certifiedDate7");
                if (!Utility.isEmpty(certifiedDate7String)) {
                    Date certifiedDate7 = Dates.getDate(certifiedDate7String);
                    vendorInsurance.setCertifiedDate7(certifiedDate7);
                }
                else
                	vendorInsurance.setCertifiedDate7(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_certStatus7"))
			{
				String certStatus7 = (String) incomingRequest.get("VendorInsurance_certStatus7");
				vendorInsurance.setCertStatus7(certStatus7);
			}
			if (incomingRequest.containsKey("VendorInsurance_certUdf7"))
			{
				String certUdf7 = (String) incomingRequest.get("VendorInsurance_certUdf7");
				vendorInsurance.setCertUdf7(certUdf7);
			}
			if (incomingRequest.containsKey("VendorInsurance_dateExpires1"))
			{
				String dateExpires1String = (String) incomingRequest.get("VendorInsurance_dateExpires1");
				if (!Utility.isEmpty(dateExpires1String)) {
					Date dateExpires1 = Dates.getDate(dateExpires1String);
					vendorInsurance.setDateExpires1(dateExpires1);
				}
				else
					vendorInsurance.setDateExpires1(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_dateExpires2"))
			{
				String dateExpires2String = (String) incomingRequest.get("VendorInsurance_dateExpires2");
				if (!Utility.isEmpty(dateExpires2String)) {
					Date dateExpires2 = Dates.getDate(dateExpires2String);
					vendorInsurance.setDateExpires2(dateExpires2);
				}
				else
					vendorInsurance.setDateExpires2(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_dateExpires3"))
			{
				String dateExpires3String = (String) incomingRequest.get("VendorInsurance_dateExpires3");
				if (!Utility.isEmpty(dateExpires3String)) {
					Date dateExpires3 = Dates.getDate(dateExpires3String);
					vendorInsurance.setDateExpires3(dateExpires3);
				}
				else
					vendorInsurance.setDateExpires3(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_dateExpires4"))
			{
				String dateExpires4String = (String) incomingRequest.get("VendorInsurance_dateExpires4");
				if (!Utility.isEmpty(dateExpires4String)) {
					Date dateExpires4 = Dates.getDate(dateExpires4String);
					vendorInsurance.setDateExpires4(dateExpires4);
				}
				else
					vendorInsurance.setDateExpires4(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_dateExpires5"))
			{
				String dateExpires5String = (String) incomingRequest.get("VendorInsurance_dateExpires5");
				if (!Utility.isEmpty(dateExpires5String)) {
					Date dateExpires5 = Dates.getDate(dateExpires5String);
					vendorInsurance.setDateExpires5(dateExpires5);
				}
				else
					vendorInsurance.setDateExpires5(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_dateExpires6"))
			{
				String dateExpires6String = (String) incomingRequest.get("VendorInsurance_dateExpires6");
				if (!Utility.isEmpty(dateExpires6String)) {
					Date dateExpires6 = Dates.getDate(dateExpires6String);
					vendorInsurance.setDateExpires6(dateExpires6);
				}
				else
					vendorInsurance.setDateExpires6(null);
			}
			if (incomingRequest.containsKey("VendorInsurance_dateExpires7"))
			{
				String dateExpires7String = (String) incomingRequest.get("VendorInsurance_dateExpires7");
				if (!Utility.isEmpty(dateExpires7String)) {
					Date dateExpires7 = Dates.getDate(dateExpires7String);
					vendorInsurance.setDateExpires7(dateExpires7);
				}
				else
					vendorInsurance.setDateExpires7(null);
			}

			result = vendorInsurance;
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
