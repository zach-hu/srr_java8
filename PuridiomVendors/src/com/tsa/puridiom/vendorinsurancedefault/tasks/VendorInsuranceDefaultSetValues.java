package com.tsa.puridiom.vendorinsurancedefault.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorInsuranceDefaultSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		String organizationId = (String)incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");
        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		try
		{
			VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault) incomingRequest.get("vendorInsuranceDefault");
			if (vendorInsuranceDefault == null)
			{
				vendorInsuranceDefault = new VendorInsuranceDefault();
			}

			if (incomingRequest.containsKey("VendorInsuranceDefault_vendorId"))
			{
				String vendorId = (String)incomingRequest.get("VendorInsuranceDefault_vendorId");
				vendorInsuranceDefault.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_insuranceContact"))
			{
				String insuranceContact = (String)incomingRequest.get("VendorInsuranceDefault_insuranceContact");
				vendorInsuranceDefault.setInsuranceContact(insuranceContact);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_insuranceOverride"))
			{
				String insuranceOverride = (String)incomingRequest.get("VendorInsuranceDefault_insuranceOverride");
				vendorInsuranceDefault.setInsuranceOverride(insuranceOverride);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_insuranceNotes"))
			{
				String insuranceNotes = (String)incomingRequest.get("VendorInsuranceDefault_insuranceNotes");
				vendorInsuranceDefault.setInsuranceNotes(insuranceNotes);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_coverage1"))
			{
				String coverage1 = (String)incomingRequest.get("VendorInsuranceDefault_coverage1");
				vendorInsuranceDefault.setCoverage1(coverage1);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_expires1"))
			{
				String expires1String = (String)incomingRequest.get("VendorInsuranceDefault_expires1");
				if (!Utility.isEmpty(expires1String)) {
					Date expires1 = Dates.getDate(expires1String, userDateFormat);
					vendorInsuranceDefault.setExpires1(expires1);
				}
				else
					vendorInsuranceDefault.setExpires1(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_named1"))
			{
				String named1 = (String)incomingRequest.get("VendorInsuranceDefault_named1");
				vendorInsuranceDefault.setNamed1(named1);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_waiver1"))
			{
				String waiver1 = (String)incomingRequest.get("VendorInsuranceDefault_waiver1");
				vendorInsuranceDefault.setWaiver1(waiver1);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_limit1"))
            {
                String limit1String = (String)incomingRequest.get("VendorInsuranceDefault_limit1");
                if (Utility.isEmpty(limit1String))
                {
                	limit1String = "0";
                }
                BigDecimal limit1 = new BigDecimal(limit1String);
                vendorInsuranceDefault.setLimit1(limit1);
            }
			if (incomingRequest.containsKey("VendorInsuranceDefault_coverage2"))
			{
				String coverage2 = (String)incomingRequest.get("VendorInsuranceDefault_coverage2");
				vendorInsuranceDefault.setCoverage2(coverage2);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_expires2"))
			{
				String expires2String = (String)incomingRequest.get("VendorInsuranceDefault_expires2");
				if (!Utility.isEmpty(expires2String)) {
					Date expires2 = Dates.getDate(expires2String, userDateFormat);
					vendorInsuranceDefault.setExpires2(expires2);
				}
				else
					vendorInsuranceDefault.setExpires2(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_named2"))
			{
				String named2 = (String)incomingRequest.get("VendorInsuranceDefault_named2");
				vendorInsuranceDefault.setNamed2(named2);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_waiver2"))
			{
				String waiver2 = (String)incomingRequest.get("VendorInsuranceDefault_waiver2");
				vendorInsuranceDefault.setWaiver2(waiver2);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_limit2"))
            {
                String limit2String = (String)incomingRequest.get("VendorInsuranceDefault_limit2");
                if (Utility.isEmpty(limit2String))
                {
                	limit2String = "0";
                }
                BigDecimal limit2 = new BigDecimal(limit2String);
                vendorInsuranceDefault.setLimit2(limit2);
            }
			if (incomingRequest.containsKey("VendorInsuranceDefault_coverage3"))
			{
				String coverage3 = (String)incomingRequest.get("VendorInsuranceDefault_coverage3");
				vendorInsuranceDefault.setCoverage3(coverage3);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_expires3"))
			{
				String expires3String = (String)incomingRequest.get("VendorInsuranceDefault_expires3");
				if (!Utility.isEmpty(expires3String)) {
					Date expires3 = Dates.getDate(expires3String, userDateFormat);
					vendorInsuranceDefault.setExpires3(expires3);
				}
				else
					vendorInsuranceDefault.setExpires3(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_named3"))
			{
				String named3 = (String)incomingRequest.get("VendorInsuranceDefault_named3");
				vendorInsuranceDefault.setNamed3(named3);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_waiver3"))
			{
				String waiver3 = (String)incomingRequest.get("VendorInsuranceDefault_waiver3");
				vendorInsuranceDefault.setWaiver3(waiver3);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_limit3"))
            {
                String limit3String = (String)incomingRequest.get("VendorInsuranceDefault_limit3");
                if (Utility.isEmpty(limit3String))
                {
                	limit3String = "0";
                }
                BigDecimal limit3 = new BigDecimal(limit3String);
                vendorInsuranceDefault.setLimit3(limit3);
            }
			if (incomingRequest.containsKey("VendorInsuranceDefault_coverage4"))
			{
				String coverage4 = (String)incomingRequest.get("VendorInsuranceDefault_coverage4");
				vendorInsuranceDefault.setCoverage4(coverage4);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_expires4"))
			{
				String expires4String = (String)incomingRequest.get("VendorInsuranceDefault_expires4");
				if (!Utility.isEmpty(expires4String)) {
					Date expires4 = Dates.getDate(expires4String, userDateFormat);
					vendorInsuranceDefault.setExpires4(expires4);
				}
				else
					vendorInsuranceDefault.setExpires4(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_named4"))
			{
				String named4 = (String)incomingRequest.get("VendorInsuranceDefault_named4");
				vendorInsuranceDefault.setNamed4(named4);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_waiver4"))
			{
				String waiver4 = (String)incomingRequest.get("VendorInsuranceDefault_waiver4");
				vendorInsuranceDefault.setWaiver4(waiver4);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_limit4"))
            {
                String limit4String = (String)incomingRequest.get("VendorInsuranceDefault_limit4");
                if (Utility.isEmpty(limit4String))
                {
                	limit4String = "0";
                }
                BigDecimal limit4 = new BigDecimal(limit4String);
                vendorInsuranceDefault.setLimit4(limit4);
            }
			if (incomingRequest.containsKey("VendorInsuranceDefault_coverage5"))
			{
				String coverage5 = (String)incomingRequest.get("VendorInsuranceDefault_coverage5");
				vendorInsuranceDefault.setCoverage5(coverage5);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_expires5"))
			{
				String expires5String = (String)incomingRequest.get("VendorInsuranceDefault_expires5");
				if (!Utility.isEmpty(expires5String)) {
					Date expires5 = Dates.getDate(expires5String, userDateFormat);
					vendorInsuranceDefault.setExpires5(expires5);
				}
				else
					vendorInsuranceDefault.setExpires5(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_named5"))
			{
				String named5 = (String)incomingRequest.get("VendorInsuranceDefault_named5");
				vendorInsuranceDefault.setNamed5(named5);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_waiver5"))
			{
				String waiver5 = (String)incomingRequest.get("VendorInsuranceDefault_waiver5");
				vendorInsuranceDefault.setWaiver5(waiver5);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_limit5"))
            {
                String limit5String = (String)incomingRequest.get("VendorInsuranceDefault_limit5");
                if (Utility.isEmpty(limit5String))
                {
                	limit5String = "0";
                }
                BigDecimal limit5 = new BigDecimal(limit5String);
                vendorInsuranceDefault.setLimit5(limit5);
            }
			if (incomingRequest.containsKey("VendorInsuranceDefault_coverage6"))
			{
				String coverage6 = (String)incomingRequest.get("VendorInsuranceDefault_coverage6");
				vendorInsuranceDefault.setCoverage6(coverage6);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_expires6"))
			{
				String expires6String = (String)incomingRequest.get("VendorInsuranceDefault_expires6");
				if (!Utility.isEmpty(expires6String)) {
					Date expires6 = Dates.getDate(expires6String, userDateFormat);
					vendorInsuranceDefault.setExpires6(expires6);
				}
				else
					vendorInsuranceDefault.setExpires6(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_named6"))
			{
				String named6 = (String)incomingRequest.get("VendorInsuranceDefault_named6");
				vendorInsuranceDefault.setNamed6(named6);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_waiver6"))
			{
				String waiver6 = (String)incomingRequest.get("VendorInsuranceDefault_waiver6");
				vendorInsuranceDefault.setWaiver6(waiver6);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_limit6"))
            {
                String limit6String = (String)incomingRequest.get("VendorInsuranceDefault_limit6");
                if (Utility.isEmpty(limit6String))
                {
                	limit6String = "0";
                }
                BigDecimal limit6 = new BigDecimal(limit6String);
                vendorInsuranceDefault.setLimit6(limit6);
            }
			if (incomingRequest.containsKey("VendorInsuranceDefault_insuranceStatus"))
			{
				String insuranceStatus = (String)incomingRequest.get("VendorInsuranceDefault_insuranceStatus");
				vendorInsuranceDefault.setInsuranceStatus(insuranceStatus);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_complianceContact"))
			{
				String complianceContact = (String)incomingRequest.get("VendorInsuranceDefault_complianceContact");
				vendorInsuranceDefault.setComplianceContact(complianceContact);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_complianceNotes"))
			{
				String complianceNotes = (String)incomingRequest.get("VendorInsuranceDefault_complianceNotes");
				vendorInsuranceDefault.setComplianceNotes(complianceNotes);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certifiedDate1"))
			{
				String certifiedDate1String = (String)incomingRequest.get("VendorInsuranceDefault_certifiedDate1");
				if (!Utility.isEmpty(certifiedDate1String)) {
					Date certifiedDate1 = Dates.getDate(certifiedDate1String, userDateFormat);
					vendorInsuranceDefault.setCertifiedDate1(certifiedDate1);
				}
				else
					vendorInsuranceDefault.setCertifiedDate1(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certStatus1"))
			{
				String certStatus1 = (String)incomingRequest.get("VendorInsuranceDefault_certStatus1");
				vendorInsuranceDefault.setCertStatus1(certStatus1);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certUdf1"))
			{
				String certUdf1 = (String)incomingRequest.get("VendorInsuranceDefault_certUdf1");
				vendorInsuranceDefault.setCertUdf1(certUdf1);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certifiedDate2"))
			{
				String certifiedDate2String = (String)incomingRequest.get("VendorInsuranceDefault_certifiedDate2");
				if (!Utility.isEmpty(certifiedDate2String)) {
					Date certifiedDate2 = Dates.getDate(certifiedDate2String, userDateFormat);
					vendorInsuranceDefault.setCertifiedDate2(certifiedDate2);
				}
				else
					vendorInsuranceDefault.setCertifiedDate2(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certStatus2"))
			{
				String certStatus2 = (String)incomingRequest.get("VendorInsuranceDefault_certStatus2");
				vendorInsuranceDefault.setCertStatus2(certStatus2);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certUdf2"))
			{
				String certUdf2 = (String)incomingRequest.get("VendorInsuranceDefault_certUdf2");
				vendorInsuranceDefault.setCertUdf2(certUdf2);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certifiedDate3"))
			{
				String certifiedDate3String = (String)incomingRequest.get("VendorInsuranceDefault_certifiedDate3");
				if (!Utility.isEmpty(certifiedDate3String)) {
					Date certifiedDate3 = Dates.getDate(certifiedDate3String, userDateFormat);
					vendorInsuranceDefault.setCertifiedDate3(certifiedDate3);
				}
				else
					vendorInsuranceDefault.setCertifiedDate3(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certStatus3"))
			{
				String certStatus3 = (String)incomingRequest.get("VendorInsuranceDefault_certStatus3");
				vendorInsuranceDefault.setCertStatus3(certStatus3);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certUdf3"))
			{
				String certUdf3 = (String)incomingRequest.get("VendorInsuranceDefault_certUdf3");
				vendorInsuranceDefault.setCertUdf3(certUdf3);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certifiedDate4"))
			{
				String certifiedDate4String = (String)incomingRequest.get("VendorInsuranceDefault_certifiedDate4");
				if (!Utility.isEmpty(certifiedDate4String)) {
					Date certifiedDate4 = Dates.getDate(certifiedDate4String, userDateFormat);
					vendorInsuranceDefault.setCertifiedDate4(certifiedDate4);
				}
				else
					vendorInsuranceDefault.setCertifiedDate4(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certStatus4"))
			{
				String certStatus4 = (String)incomingRequest.get("VendorInsuranceDefault_certStatus4");
				vendorInsuranceDefault.setCertStatus4(certStatus4);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certUdf4"))
			{
				String certUdf4 = (String)incomingRequest.get("VendorInsuranceDefault_certUdf4");
				vendorInsuranceDefault.setCertUdf4(certUdf4);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certifiedDate5"))
			{
				String certifiedDate5String = (String) incomingRequest.get("VendorInsuranceDefault_certifiedDate5");
				if (!Utility.isEmpty(certifiedDate5String)) {
					Date certifiedDate5 = Dates.getDate(certifiedDate5String, userDateFormat);
					vendorInsuranceDefault.setCertifiedDate5(certifiedDate5);
				}
				else
					vendorInsuranceDefault.setCertifiedDate5(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certStatus5"))
			{
				String certStatus5 = (String)incomingRequest.get("VendorInsuranceDefault_certStatus5");
				vendorInsuranceDefault.setCertStatus5(certStatus5);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certUdf5"))
			{
				String certUdf5 = (String)incomingRequest.get("VendorInsuranceDefault_certUdf5");
				vendorInsuranceDefault.setCertUdf5(certUdf5);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certifiedDate6"))
			{
                String certifiedDate6String = (String)incomingRequest.get("VendorInsuranceDefault_certifiedDate6");
                if (!Utility.isEmpty(certifiedDate6String)) {
                	Date certifiedDate6 = Dates.getDate(certifiedDate6String, userDateFormat);
                	vendorInsuranceDefault.setCertifiedDate6(certifiedDate6);
                }
				else
					vendorInsuranceDefault.setCertifiedDate6(null);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certStatus6"))
			{
				String certStatus6 = (String)incomingRequest.get("VendorInsuranceDefault_certStatus6");
				vendorInsuranceDefault.setCertStatus6(certStatus6);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_certUdf6"))
			{
				String certUdf6 = (String)incomingRequest.get("VendorInsuranceDefault_certUdf6");
				vendorInsuranceDefault.setCertUdf6(certUdf6);
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_expiredLetter"))
			{
				String expiredLetterString = (String)incomingRequest.get("VendorInsuranceDefault_expiredLetter");
				if (!Utility.isEmpty(expiredLetterString)) {
					Date expiredLetter = Dates.getDate(expiredLetterString, userDateFormat);
					vendorInsuranceDefault.setExpiredLetter(expiredLetter);
				}
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_notifiedDate"))
			{
				String notifiedDateString = (String)incomingRequest.get("VendorInsuranceDefault_notifiedDate");
				if (!Utility.isEmpty(notifiedDateString)) {
					Date notifiedDate = Dates.getDate(notifiedDateString, userDateFormat);
					vendorInsuranceDefault.setNotifiedDate(notifiedDate);
				}
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_lastChangeDate"))
			{
				String lastChangeDateString = (String)incomingRequest.get("VendorInsuranceDefault_lastChangeDate");
				if (!Utility.isEmpty(lastChangeDateString)) {
					Date lastChangeDate = Dates.getDate(lastChangeDateString, userDateFormat);
					vendorInsuranceDefault.setLastChangeDate(lastChangeDate);
				}
			}
			if (incomingRequest.containsKey("VendorInsuranceDefault_lastChangeBy"))
			{
				String lastChangeBy = (String)incomingRequest.get("VendorInsuranceDefault_lastChangeBy");
				vendorInsuranceDefault.setLastChangeBy(lastChangeBy);
			}

			result = vendorInsuranceDefault;
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
