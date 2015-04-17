package com.tsa.puridiom.contact.tasks;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.sql.Date;
import java.util.Map;

public class ContactSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String oid = (String)incomingRequest.get("organizationId");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid) ;

		try
		{
			ContactPK comp_id = new ContactPK();
			Contact contact = (Contact) incomingRequest.get("contact");
			if (contact == null)
			{
				contact = new Contact();
			}

			if (incomingRequest.containsKey("Contact_contactCode"))
			{
				String contactCode = (String ) incomingRequest.get("Contact_contactCode");
				comp_id.setContactCode(contactCode);
			}
			if (incomingRequest.containsKey("Contact_contactType"))
			{
				String contactType = (String ) incomingRequest.get("Contact_contactType");
				comp_id.setContactType(contactType);
			}
			if (incomingRequest.containsKey("Contact_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("Contact_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("Contact_lastName"))
			{
				String lastName = (String ) incomingRequest.get("Contact_lastName");
				contact.setLastName(lastName);
			}
			if (incomingRequest.containsKey("Contact_firstName"))
			{
				String firstName = (String ) incomingRequest.get("Contact_firstName");
				contact.setFirstName(firstName);
			}
			if (incomingRequest.containsKey("Contact_middleInit"))
			{
				String middleInit = (String ) incomingRequest.get("Contact_middleInit");
				contact.setMiddleInit(middleInit);
			}
			if (incomingRequest.containsKey("Contact_salutation"))
			{
				String salutation = (String ) incomingRequest.get("Contact_salutation");
				contact.setSalutation(salutation);
			}
			if (incomingRequest.containsKey("Contact_contactTitle"))
			{
				String contactTitle = (String ) incomingRequest.get("Contact_contactTitle");
				contact.setContactTitle(contactTitle);
			}
			if (incomingRequest.containsKey("Contact_phoneNumber"))
			{
				String phoneNumber = (String ) incomingRequest.get("Contact_phoneNumber");
				contact.setPhoneNumber(phoneNumber);
			}
			if (incomingRequest.containsKey("Contact_phoneFormat"))
			{
				String phoneFormat = (String ) incomingRequest.get("Contact_phoneFormat");
				contact.setPhoneFormat(phoneFormat);
			}
			if (incomingRequest.containsKey("Contact_mobileNumber"))
			{
				String mobileNumber = (String ) incomingRequest.get("Contact_mobileNumber");
				contact.setMobileNumber(mobileNumber);
			}
			if (incomingRequest.containsKey("Contact_mobileFormat"))
			{
				String mobileFormat = (String ) incomingRequest.get("Contact_mobileFormat");
				contact.setMobileFormat(mobileFormat);
			}
			if (incomingRequest.containsKey("Contact_faxNumber"))
			{
				String faxNumber = (String ) incomingRequest.get("Contact_faxNumber");
				contact.setFaxNumber(faxNumber);
			}
			if (incomingRequest.containsKey("Contact_faxFormat"))
			{
				String faxFormat = (String ) incomingRequest.get("Contact_faxFormat");
				contact.setFaxFormat(faxFormat);
			}
			if (incomingRequest.containsKey("Contact_addressCode"))
			{
				String addressCode = (String ) incomingRequest.get("Contact_addressCode");
				contact.setAddressCode(addressCode);
			}
			if (incomingRequest.containsKey("Contact_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Contact_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				contact.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Contact_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Contact_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				contact.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Contact_status"))
			{
				String status = (String ) incomingRequest.get("Contact_status");
				contact.setStatus(status);
			}
			if (incomingRequest.containsKey("Contact_owner"))
			{
				String owner = (String ) incomingRequest.get("Contact_owner");
				contact.setOwner(owner);
			}
			if (incomingRequest.containsKey("Contact_emailAddr"))
			{
				String emailAddr = (String ) incomingRequest.get("Contact_emailAddr");
				if (propertiesManager.getProperty("MISC", "CONVERTLOWERCASE", "Y").equals("Y"))
				   {contact.setEmailAddr(Utility.ckNull(emailAddr).toLowerCase());}
				else
				   {contact.setEmailAddr(Utility.ckNull(emailAddr));}
			}
			if (incomingRequest.containsKey("Contact_info1"))
			{
				String info1 = (String ) incomingRequest.get("Contact_info1");
				contact.setInfo1(info1);
			}
			if (incomingRequest.containsKey("Contact_info2"))
			{
				String info2 = (String ) incomingRequest.get("Contact_info2");
				contact.setInfo2(info2);
			}
			if (incomingRequest.containsKey("Contact_info3"))
			{
				String info3 = (String ) incomingRequest.get("Contact_info3");
				contact.setInfo3(info3);
			}
			if (incomingRequest.containsKey("Contact_info4"))
			{
				String info4 = (String ) incomingRequest.get("Contact_info4");
				contact.setInfo4(info4);
			}
			if (incomingRequest.containsKey("Contact_contactPassword"))
			{
				String contactPassword = (String ) incomingRequest.get("Contact_contactPassword");
				contact.setContactPassword(contactPassword);
			}
			if (incomingRequest.containsKey("Contact_priorPassword"))
			{
				String priorPassword = (String) incomingRequest.get("Contact_priorPassword");
				contact.setPriorPassword(priorPassword);
			}
			if (incomingRequest.containsKey("Contact_passChanged"))
			{
				String passChanged = (String ) incomingRequest.get("Contact_passChanged");
				contact.setPassChanged(passChanged);
			}
			if (incomingRequest.containsKey("Contact_lockLogin"))
			{
				String lockLogin = (String) incomingRequest.get("Contact_lockLogin");
				contact.setLockLogin(lockLogin);
			}
			contact.setComp_id(comp_id);

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