package com.tsa.puridiom.invoice.tasks;

import java.util.HashMap;
import java.util.Map;

//import com.tsa.puridiom.contact.tasks.ContactRetrieveByCode;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.Vendor;
//import com.tsa.puridiom.vendor.tasks.VendorRetrieveById;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class InvoiceEmailUtility
{
	 public static String getToEmail(Map incomingRequest)
    {
        String emailTo = (String)incomingRequest.get("emailTo");
        if (Utility.isEmpty(emailTo)) { emailTo = "N"; }
        String email = "";
        if(emailTo.equalsIgnoreCase("N"))
        {
            email = InvoiceEmailUtility.getContactEmail(incomingRequest);
        }
        else
        {
            email = (String)incomingRequest.get("email");
        }

        if(Utility.isEmpty(email))
        {
            email = InvoiceEmailUtility.getVendorEmail(incomingRequest);
        }

        return email;

    }

	 public static String getContactEmail(Map incomingRequest)
    {
        Object A;
		String contactEmail = "";
        try
        {
            String vendorId = (String)incomingRequest.get("Pdf_InvoiceHeader_vendorId");
            String contactCode = (String)incomingRequest.get("InvoiceHeader_contactEmail");

            Log.debug("PoEmailUtility", "finding vendor email: " + vendorId);
            //ContactRetrieveByCode contactTask = new ContactRetrieveByCode();
            Map newIncomingRequest = new HashMap();
            newIncomingRequest.put("Contact_vendorId", vendorId);
            newIncomingRequest.put("Contact_contactCode", contactCode);

            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            newIncomingRequest.put("userId", incomingRequest.get("userId"));
						   newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
            //Contact contact = (Contact)contactTask.executeTask(newIncomingRequest);


            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("email-invoice.xml");
            process.executeProcess(newIncomingRequest);

            Contact contact = (Contact) newIncomingRequest.get("emailContact");

            if(contact != null)
            {
                contactEmail = contact.getEmailAddr();
            }
        }
        catch (Exception e)
        {
            contactEmail = "";
        }
        Log.debug("InvoiceEmailUtility", "Contact email : " + contactEmail);

        return contactEmail;
    }

	 /**
     * @param vendorId
     * @return
     */
    public static String getVendorEmail(Map incomingRequest)
    {
        String vendorEmail = "";
        try
        {
            String vendorId = (String)incomingRequest.get("mailId");
            Log.debug("PoEmailUtility", "finding vendor email: " + vendorId);
  //          VendorRetrieveById vendorRetrieve = new VendorRetrieveById();
            Map newIncomingRequest = new HashMap();
            newIncomingRequest.put("Vendor_vendorId", vendorId);
            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            newIncomingRequest.put("userId", incomingRequest.get("userId"));
						   newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("email-vendor.xml");
            process.executeProcess(newIncomingRequest);

            Vendor vendor = (Vendor) newIncomingRequest.get("emailVendor");
            if(vendor != null)
            {
                vendorEmail = vendor.getEmailAddress();
            }
        }
        catch (Exception e)
        {
            vendorEmail = "";
        }
        Log.debug("InvoiceEmailUtility", "vendor email : " + vendorEmail);

        return vendorEmail;
    }

}
