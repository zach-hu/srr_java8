package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorAltContactQualifyList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			DBSession dbsession = (DBSession) incomingRequest.get("dbsession");
		    String	organizationId = (String) incomingRequest.get("organizationId");
			String	userId = (String) incomingRequest.get("userId");
			String	vendorId = (String) incomingRequest.get("Vendor_vendorId");
		    List	vendorRegisterAltContactList = (List) incomingRequest.get("vendorRegisterAltContactList");
			List contactList = new ArrayList();
			
			if (vendorRegisterAltContactList != null) {
				for (int i=0; i < vendorRegisterAltContactList.size(); i++) {
					VendorRegister vendorRegister = (VendorRegister) vendorRegisterAltContactList.get(i);
					Map requestParameters = new HashMap();
					
					requestParameters.put("dbsession", dbsession);
					requestParameters.put("organizationId", organizationId);
					requestParameters.put("userId", userId);
					requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					requestParameters.put("vendorId", vendorId);
					requestParameters.put("vendorRegister", vendorRegister);
					
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("vendorregister-qualify-alt-contact.xml");
	
					process.executeProcess(incomingRequest);
					
					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("VendorRegister Qualify Alt Contact process failed.");
					}
					
					Contact contact = (Contact) incomingRequest.get("contact");
					contactList.add(contact);
				}
			}

			result = contactList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}