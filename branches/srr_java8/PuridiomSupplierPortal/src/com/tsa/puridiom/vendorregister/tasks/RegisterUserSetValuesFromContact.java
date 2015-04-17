package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.vendorregistration.VendorRegistrationProcessLoader;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserSetValuesFromContact extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RegisterUser user = (RegisterUser) incomingRequest.get("registerUser");
			Contact contact = (Contact) incomingRequest.get("contact");
			String	organizationId = (String) incomingRequest.get("organizationId");

			if (user == null)
			{
				user = new RegisterUser();
				user.setOrganizationId(organizationId);
			}
			if (contact != null)
			{
			    String	vendorName = "";
			    String	termsAccepted = "";

			    try {
				    Map requestParams = new HashMap();

				    requestParams.put("organizationId", incomingRequest.get("organizationId"));
				    requestParams.put("Vendor_vendorId", contact.getComp_id().getVendorId());

				    PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
				    PuridiomProcess process = processLoader.loadProcess("vendor-retrieve-by-id.xml");

				    process.executeProcess(requestParams);
				    Vendor vendor = (Vendor) requestParams.get("vendor");
				    if (vendor != null) {
				        vendorName = vendor.getVendorName();
				        termsAccepted = vendor.getTermsAccepted();
				    }
			    } catch (Exception e) {
			    }

				user.setVendorId(contact.getComp_id().getVendorId());
				user.setVendorName(vendorName);
				user.setContactCode(contact.getComp_id().getContactCode());
				user.setContactType(contact.getComp_id().getContactType());
				user.setAddressCode(contact.getAddressCode());
				user.setEmailAddress(contact.getEmailAddr());
				user.setFirstName(contact.getFirstName());
				user.setLastName(contact.getLastName());
				user.setMiddleInitial(contact.getMiddleInit());
				user.setQualified(true);
				user.setContactPassword(contact.getContactPassword());
				user.setPassChanged(contact.getPassChanged());
				user.setStatus(contact.getStatus());
				user.setTermsAccepted(termsAccepted);

                String tempPassword = (String) incomingRequest.get("RegisterUser_tempPassword");
                if (tempPassword != null && tempPassword.equals("true")) {
                    user.setTempPassword(true);
                }
			}

			result = user;
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
