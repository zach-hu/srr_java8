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

public class SupplierFaxUtility
{
	public String getToFax(Map incomingRequest)
	{
		String faxTo = (String) incomingRequest.get("faxTo");
		if (Utility.isEmpty(faxTo)) {		faxTo = "N";		}
		String fax = "";

		if (faxTo.equalsIgnoreCase("N"))
		{
			fax = this.getContactFax(incomingRequest);
			if (Utility.isEmpty(fax)) 	fax = this.getVendorFax(incomingRequest);
		}

		String cc = (String) incomingRequest.get("fax");
		if (!Utility.isEmpty(cc)) {		fax = fax + ";" + cc;		}

		return fax;
	}

	public String getVendorId(Map incomingRequest)
	{
		return "";
	}

	public String getContactCode(Map incomingRequest)
	{
		return "";
	}


	public String getContactFax(Map incomingRequest)
	{
        String contactFax = "";
        try
        {
            String vendorId = this.getVendorId(incomingRequest);
            String contactCode = this.getContactCode(incomingRequest);
            Log.debug("FaxUtility", "finding vendor fax: " + vendorId);

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
                    contactFax = contact.getFaxNumber();
                    //TODO need to check for country rules
                    contactFax = contactFax.replaceAll("\\W|[a-zA-Z]", "");
                    if(contactFax.length()>10){
                    	if(contactFax != null && !contactFax.startsWith("1"))
                        {
                        	contactFax = "1" + contactFax;
                        }
                    }
                    else {
                    	contactFax = "1" + contactFax;
                    }
                }
            }

        }
        catch (Exception e)
        {
            contactFax = "";
        }
        Log.debug("FaxUtility", "Contact Fax : " + contactFax);

        return contactFax;
    }

	public String getVendorFax(Map incomingRequest)
    {
        String vendorFax = "";
        try
        {
        	String vendorId = this.getVendorId(incomingRequest);
            Log.debug("FaxUtility", "finding vendor fax: " + vendorId);

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
                    vendorFax = vendor.getFaxNumber();

                    //TODO need to check for country rules
                    vendorFax = vendorFax.replaceAll("\\W|[a-zA-Z]", "");
                    if(vendorFax.length()>10){
                    	if(vendorFax != null && !vendorFax.startsWith("1"))
                    	{
                    		vendorFax = "1" + vendorFax;
                    	}
                    }
                    else {
                    	vendorFax = "1" + vendorFax;
                    }
                }
            }

        }
        catch (Exception e)
        {
            vendorFax = "";
        }
        Log.debug("FaxUtility", "vendor fax : " + vendorFax);

        return vendorFax;
    }
}
