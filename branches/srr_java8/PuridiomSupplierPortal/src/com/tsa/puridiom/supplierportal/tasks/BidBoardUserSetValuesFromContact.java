package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsagate.foundation.processengine.*;

import java.util.HashMap;
import java.util.Map;

public class BidBoardUserSetValuesFromContact extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BidBoardUser user = (BidBoardUser) incomingRequest.get("bidboardUser");
			Contact contact = (Contact) incomingRequest.get("contact");

			if (user == null)
			{
				String	organizationId = (String) incomingRequest.get("organizationId");

				user = new BidBoardUser();
				user.setOrganization(organizationId);
			}
			if (contact != null)
			{
			    String	vendorName = "";

			    try {
				    Map requestParams = new HashMap();

				    requestParams.put("organizationId", incomingRequest.get("organizationId"));
				    requestParams.put("Vendor_vendorId", contact.getComp_id().getVendorId());

				    PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader("organizationId");
				    PuridiomProcess process = processLoader.loadProcess("vendor-retrieve-by-id.xml");

				    process.executeProcess(requestParams);
				    Vendor vendor = (Vendor) requestParams.get("vendor");
				    if (vendor != null) {
				        vendorName = vendor.getVendorName();
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
				user.setUserPassword(contact.getContactPassword());
				user.setPassChanged(contact.getPassChanged());
				user.setStatus(contact.getStatus());
				user.setLockLogin(contact.getLockLogin());
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