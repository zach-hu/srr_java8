package com.tsa.puridiom.common.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class SupplierEmailUtility
{
	public String getToEmail(Map incomingRequest)
	{
		String emailTo = (String) incomingRequest.get("emailTo");
		if (Utility.isEmpty(emailTo)) {		emailTo = "N";		}
		String email = "";

		if (emailTo.equalsIgnoreCase("N"))
		{
			email = this.getContactEmail(incomingRequest);
			if (Utility.isEmpty(email)) 	email = this.getVendorEmail(incomingRequest);
		}

		String cc = (String) incomingRequest.get("email");
		if (!Utility.isEmpty(cc)) {		email = email + ";" + cc;		}

		return email;
	}

	public String getVendorId(Map incomingRequest)
	{
		return "";
	}

	public String getContactCode(Map incomingRequest)
	{
		return "";
	}

	public String getContactEmail(Map incomingRequest)
	{
        String contactEmail = "";
        try
        {
            String vendorId = this.getVendorId(incomingRequest);
            String contactCode = this.getContactCode(incomingRequest);
            Log.debug("EmailUtility", "finding vendor email: " + vendorId);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("contact-retrieve-by-code.xml");
            Map newIncomingRequest = new HashMap();
            newIncomingRequest.put("Contact_vendorId", vendorId);
            newIncomingRequest.put("Contact_contactCode", contactCode);
            newIncomingRequest.put("userId", incomingRequest.get("userId"));
            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
            process.executeProcess(newIncomingRequest);
            if(process.getStatus() == Status.SUCCEEDED)
            {
            	Contact contact = (Contact)newIncomingRequest.get("contact");
                if(contact != null)
                {
                    contactEmail = contact.getEmailAddr();
                }
            }

        }
        catch (Exception e)
        {
            contactEmail = "";
        }
        Log.debug("EmailUtility", "Contact email : " + contactEmail);

        return contactEmail;
    }

	public String getVendorEmail(Map incomingRequest)
    {
        String vendorEmail = "";
        try
        {
        	String vendorId = this.getVendorId(incomingRequest);
            Log.debug("EmailUtility", "finding vendor email: " + vendorId);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("vendor-retrieve-by-id.xml");
            Map newIncomingRequest = new HashMap();
            newIncomingRequest.put("Vendor_vendorId", vendorId);
            newIncomingRequest.put("userId", incomingRequest.get("userId"));
            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
            process.executeProcess(newIncomingRequest);
            if(process.getStatus() == Status.SUCCEEDED)
            {
            	Vendor vendor = (Vendor)newIncomingRequest.get("vendor");
                if(vendor != null)
                {
                    vendorEmail = vendor.getEmailAddress();
                }
            }

        }
        catch (Exception e)
        {
            vendorEmail = "";
        }
        Log.debug("EmailUtility", "vendor email : " + vendorEmail);

        return vendorEmail;
    }
}
