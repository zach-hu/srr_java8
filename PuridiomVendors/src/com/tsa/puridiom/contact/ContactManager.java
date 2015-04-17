package com.tsa.puridiom.contact;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

public class ContactManager
{
	private static ContactManager INSTANCE = new ContactManager();

	private ContactManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.catalog.tasks.CatalogItemManager
	 */
	public static ContactManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new ContactManager();
		}
		return INSTANCE;
	}
    /**
     * @return com.tsa.puridiom.entity.CatalogItem
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
	public Object getContact(String organizationId, String contactCode, String contactType, String vendorId) throws java.lang.Exception
	{
		Object result = null;
		try
		{
			if (Utility.isEmpty(contactCode) || Utility.isEmpty(contactType) || Utility.isEmpty(vendorId) || Utility.isEmpty(organizationId))
			{
				result = new Contact();
				return result;
			}
			else
			{
				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("Contact_contactCode", contactCode);
				incomingRequest.put("Contact_contactType", contactType);
				incomingRequest.put("Contact_vendorId", vendorId);
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("contact-retrieve-by-id.xml");

				process.executeProcess(incomingRequest);

				result = incomingRequest.get("contact");
				if (result == null)
				{
					result = new Contact();
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}