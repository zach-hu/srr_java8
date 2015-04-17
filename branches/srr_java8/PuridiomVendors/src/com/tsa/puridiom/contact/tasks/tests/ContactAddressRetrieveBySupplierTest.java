package com.tsa.puridiom.contact.tasks.tests;

import com.tsagate.foundation.database.*;
import com.tsa.puridiom.contact.tasks.*;
import com.tsa.puridiom.entity.*;
import java.util.*;

public class ContactAddressRetrieveBySupplierTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			ContactAddressRetrieveBySupplier test = new ContactAddressRetrieveBySupplier();
			Map incomingRequest = new HashMap();

			incomingRequest.put("Contact_vendorId", "TSAINC");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			List list = (List) test.executeTask(incomingRequest);
			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					Object objects[] = (Object[]) list.get(i);
					Contact contact = (Contact) objects[0];
					Address address = (Address) objects[1];
					System.out.println(contact.toString());
					System.out.println(address.toString());
				}
			}

			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}