package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.vendorregistration.VendorRegistrationProcessLoader;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class VendorRegisterAccoutingNotification extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

		    if (vendorRegister != null) {
		        String	organizationId = (String) incomingRequest.get("organizationId");
		        String	organizationName = OrganizationManager.getInstance().getOrganizationName(organizationId);
		        String	accountingEmail = PropertiesManager.getInstance(organizationId).getProperty("VENDOR-REGISTRATION", "Accounting Email Addr", "");
		        String	userId = (String) incomingRequest.get("userId");
		        UserProfile user = UserManager.getInstance().getUser(organizationId, userId);

		        String	message = "\n\nNew Supplier Profile Information";
		        message = message + "\nRegistered On " + HiltonUtility.getFormattedDate(vendorRegister.getDateEntered(), organizationId);

		        String addr2 = vendorRegister.getAddressLine2();
		        String addr3 = vendorRegister.getAddressLine3();
		        String addr4 = vendorRegister.getAddressLine4();
		        String citystatezip = vendorRegister.getAddressCityStateZip();

		        message = message + "\n\nCompany Name: " + vendorRegister.getVendorName();
		        if ( !HiltonUtility.isEmpty(addr2) ) {
		        	message = message +"\nAddress Line 2: " + addr2;
		        }
		        if ( !HiltonUtility.isEmpty(addr3) ) {
		        	message = message +"\nAddress Line 3: " + addr3;
		        }
		        if ( !HiltonUtility.isEmpty(addr4) ) {
		        	message = message +"\nAddress Line 4: " + addr4;
		        }
		        if ( !HiltonUtility.isEmpty(citystatezip) ) {
		        	message = message +"\nCity, State Zip: " + citystatezip;
		        }

		        String addr1 = vendorRegister.getRtAddressLine1();
		        addr2 = vendorRegister.getRtAddressLine2();
		        addr3 = vendorRegister.getRtAddressLine3();
		        addr4 = vendorRegister.getRtAddressLine4();
		        citystatezip = vendorRegister.getRtAddressCityStateZip();

		        if ( !HiltonUtility.isEmpty(addr1) ) {
		        	message = message + "\n\nRemit To: " + addr1;
		        }
		        if ( !HiltonUtility.isEmpty(addr2) ) {
		        	message = message +"\nAddress Line 2: " + addr2;
		        }
		        if ( !HiltonUtility.isEmpty(addr3) ) {
		        	message = message +"\nAddress Line 3: " + addr3;
		        }
		        if ( !HiltonUtility.isEmpty(addr4) ) {
		        	message = message +"\nAddress Line 4: " + addr4;
		        }
		        if ( !HiltonUtility.isEmpty(citystatezip) ) {
		        	message = message +"\nCity, State Zip: " + citystatezip;
		        }

		        //Commodity?
		        message = message +"\n\nSIC Code: " + vendorRegister.getVendorSic();
		        message = message +"\nNAICS Code: " + vendorRegister.getVendorNaics();
		        message = message +"\nDUNS Number: " + vendorRegister.getVendorDuns();
		        message = message +"\nEIN: " + vendorRegister.getVendorEin();
		        message = message +"\nTerms: " + vendorRegister.getVendorVendTerms();

		        message = message +"\n\nContact Information:";
		        message = message +"\n	" + vendorRegister.getContactDisplayName();
		        message = message +"\n	" + vendorRegister.getContactTitle();
		        message = message +"\n	" + vendorRegister.getContactPhoneNo();
		        if ( !HiltonUtility.isEmpty(vendorRegister.getContactFaxNumber()) )
		        {
		        	if ( !HiltonUtility.isEmpty(vendorRegister.getContactPhoneNo()) )
		        	{
		        		message = message + " / ";
		        	}
		        	message = message + vendorRegister.getContactFaxNumber() + " (fax)";
		        }
		        message = message +"\n	" + vendorRegister.getComp_id().getContactEmailAddr();

		        if ( !HiltonUtility.isEmpty(vendorRegister.getAltDisplayName()) )
		        {
		        	message = message +"\n\nAlternate Contact Information: ";
			        message = message +"\n	" + vendorRegister.getAltDisplayName();
			        message = message +"\n	" + vendorRegister.getAltTitle();
			        message = message +"\n	" + vendorRegister.getAltPhoneNo();
			        if ( !HiltonUtility.isEmpty(vendorRegister.getAltFaxNumber()) )
			        {
			        	if ( !HiltonUtility.isEmpty(vendorRegister.getAltPhoneNo()) )
			        	{
			        		message = message + " / ";
			        	}
			        	message = message + vendorRegister.getAltFaxNumber() + " (fax)";
			        }
			        message = message +"\n	" + vendorRegister.getAltEmailAddr();
		        }

		        /*
		        message = message +"\n\nYears in Business: " + vendorRegister.getVendorYears();
		        //message = message +"\nCommercial General Liability Insurance Company";
		        message = message +"\nBusiness Ownership: " ;
		        message = message +"\nVendor Class: " + vendorRegister.getVendorClass();
		        message = message +"\nDiverse Certified By: " + vendorRegister.getDiverseCertOrgs();
		        */

		        message = message + "\n\nIf you have questions, please email " + user.getDisplayName() + " at " + user.getMailId() + ".";
		        message = message + "\n\n\n";

				incomingRequest.put("SendQueue_subject", organizationName + " Supplier Registration Notification [" + vendorRegister.getComp_id().getVendorId() +"]");
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", user.getMailId());
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", accountingEmail);
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);

				PuridiomProcessLoader processLoader = new VendorRegistrationProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);
				this.status = process.getStatus() ;

				if (this.getStatus() != Status.SUCCEEDED) {
				    throw new Exception("The email record could not be written to the queue.");
				}
			}
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}