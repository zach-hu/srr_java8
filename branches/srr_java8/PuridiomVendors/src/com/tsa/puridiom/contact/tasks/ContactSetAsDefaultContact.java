package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ContactSetAsDefaultContact extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			Contact newDefault = (Contact) incomingRequest.get("newDefault");
			Contact currentDefault = (Contact) incomingRequest.get("currentDefault");

			String lastName = newDefault.getLastName();
			String firstName = newDefault.getFirstName();
			String middleInit = newDefault.getMiddleInit();
			String salutation = newDefault.getSalutation();
			String contactTitle = newDefault.getContactTitle();
			String phoneNumber = newDefault.getPhoneNumber();
			String phoneFormat = newDefault.getPhoneFormat();
			String faxNumber = newDefault.getFaxNumber();
			String faxFormat = newDefault.getFaxFormat();
			String addressCode = newDefault.getAddressCode();
			java.util.Date dateEntered = newDefault.getDateEntered();
			java.util.Date dateExpires = newDefault.getDateExpires();
			String status = newDefault.getStatus();
			String owner = newDefault.getOwner();
			String emailAddr = newDefault.getEmailAddr();
			String info1 = newDefault.getInfo1();
			String info2 = newDefault.getInfo2();
			String info3 = newDefault.getInfo3();
			String info4 = newDefault.getInfo4();
			String contactPassword = newDefault.getContactPassword();
			String passChanged = newDefault.getPassChanged();
			String mobileNumber = newDefault.getMobileNumber();
			String mobileFormat  = newDefault.getMobileFormat();

			newDefault.setLastName(currentDefault.getLastName());
			newDefault.setFirstName(currentDefault.getFirstName());
			newDefault.setMiddleInit(currentDefault.getMiddleInit());
			newDefault.setSalutation(currentDefault.getSalutation());
			newDefault.setContactTitle(currentDefault.getContactTitle());
			newDefault.setPhoneNumber(currentDefault.getPhoneNumber());
			newDefault.setPhoneFormat(currentDefault.getPhoneFormat());
			newDefault.setFaxNumber(currentDefault.getFaxNumber());
			newDefault.setFaxFormat(currentDefault.getFaxFormat());
			newDefault.setAddressCode(currentDefault.getAddressCode());
			newDefault.setDateEntered(currentDefault.getDateEntered());
			newDefault.setDateExpires(currentDefault.getDateExpires());
			newDefault.setStatus(currentDefault.getStatus());
			newDefault.setOwner(currentDefault.getOwner());
			newDefault.setEmailAddr(currentDefault.getEmailAddr());
			newDefault.setInfo1(currentDefault.getInfo1());
			newDefault.setInfo2(currentDefault.getInfo2());
			newDefault.setInfo3(currentDefault.getInfo3());
			newDefault.setInfo4(currentDefault.getInfo4());
			newDefault.setContactPassword(currentDefault.getContactPassword());
			newDefault.setPassChanged(currentDefault.getPassChanged());
			newDefault.setMobileNumber(currentDefault.getMobileNumber());
			newDefault.setMobileFormat(currentDefault.getMobileFormat());

			currentDefault.setLastName(lastName);
			currentDefault.setFirstName(firstName);
			currentDefault.setMiddleInit(middleInit);
			currentDefault.setSalutation(salutation);
			currentDefault.setContactTitle(contactTitle);
			currentDefault.setPhoneNumber(phoneNumber);
			currentDefault.setPhoneFormat(phoneFormat);
			currentDefault.setFaxNumber(faxNumber);
			currentDefault.setFaxFormat(faxFormat);
			currentDefault.setAddressCode(addressCode);
			currentDefault.setDateEntered(dateEntered);
			currentDefault.setDateExpires(dateExpires);
			currentDefault.setStatus(status);
			currentDefault.setOwner(owner);
			currentDefault.setEmailAddr(emailAddr);
			currentDefault.setInfo1(info1);
			currentDefault.setInfo2(info2);
			currentDefault.setInfo3(info3);
			currentDefault.setInfo4(info4);
			currentDefault.setContactPassword(contactPassword);
			currentDefault.setPassChanged(passChanged);
			currentDefault.setMobileNumber(mobileNumber);
			currentDefault.setMobileFormat(mobileFormat);

			Task task = new ContactUpdate();
			incomingRequest.put("contact", newDefault);
			task.executeTask(incomingRequest);
			incomingRequest.put("contact", currentDefault);
			task.executeTask(incomingRequest);

			/* String queryString = "DELETE FROM Address WHERE Address.Address_Type ='" + currentDefault.getComp_id().getVendorId() +
				"' AND Address_Code = '" + newDefault.getComp_id().getContactCode()+ "'";
			dbs.sqlUpdate(queryString); */

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