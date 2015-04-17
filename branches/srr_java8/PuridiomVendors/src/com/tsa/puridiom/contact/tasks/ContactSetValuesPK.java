package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ContactSetValuesPK
{
	public void setValues(Map incomingRequest, Contact contact ) throws Exception
	{
		try
		{
			String contactCode = (String ) incomingRequest.get("Contact_contactCode");
			String contactType = (String ) incomingRequest.get("Contact_contactType");
			String vendorId = (String ) incomingRequest.get("Contact_vendorId");
			ContactPK comp_id = new ContactPK();
			comp_id.setContactCode(contactCode);
			comp_id.setContactType(contactType);
			comp_id.setVendorId(vendorId);
			contact.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}