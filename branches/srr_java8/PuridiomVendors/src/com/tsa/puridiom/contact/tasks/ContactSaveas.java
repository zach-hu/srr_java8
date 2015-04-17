package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class ContactSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain contact */
			ContactPK comp_id = new ContactPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Contact	originalContact = (Contact) incomingRequest.get("contact");
			Contact	contact = new Contact();

			comp_id.setContactCode(originalContact.getComp_id().getContactCode());
			comp_id.setContactType(originalContact.getComp_id().getContactType());
			comp_id.setVendorId(originalContact.getComp_id().getVendorId());
			contact.setLastName(originalContact.getLastName());
			contact.setFirstName(originalContact.getFirstName());
			contact.setMiddleInit(originalContact.getMiddleInit());
			contact.setSalutation(originalContact.getSalutation());
			contact.setContactTitle(originalContact.getContactTitle());
			contact.setPhoneNumber(originalContact.getPhoneNumber());
			contact.setPhoneFormat(originalContact.getPhoneFormat());
			contact.setFaxNumber(originalContact.getFaxNumber());
			contact.setFaxFormat(originalContact.getFaxFormat());
			contact.setAddressCode(originalContact.getAddressCode());
			contact.setDateEntered(originalContact.getDateEntered());
			contact.setDateExpires(originalContact.getDateExpires());
			contact.setStatus(originalContact.getStatus());
			contact.setOwner(originalContact.getOwner());
			contact.setEmailAddr(originalContact.getEmailAddr());
			contact.setInfo1(originalContact.getInfo1());
			contact.setInfo2(originalContact.getInfo2());
			contact.setInfo3(originalContact.getInfo3());
			contact.setInfo4(originalContact.getInfo4());
			contact.setContactPassword(originalContact.getContactPassword());
			contact.setPassChanged(originalContact.getPassChanged());
			contact.setComp_id(comp_id);

			incomingRequest.put("contact", contact);

			ContactAdd addTask = new ContactAdd();
			contact = (Contact) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = contact;
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