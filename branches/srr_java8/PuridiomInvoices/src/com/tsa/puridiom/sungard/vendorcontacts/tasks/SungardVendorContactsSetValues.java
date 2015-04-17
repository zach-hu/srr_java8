package com.tsa.puridiom.sungard.vendorcontacts.tasks;

import com.tsa.puridiom.entity.sungard.VendorContacts;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class SungardVendorContactsSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorContacts vendorContacts = (VendorContacts) incomingRequest.get("sungardVendorContacts");
			if (vendorContacts == null)
			{
				vendorContacts = new VendorContacts();
			}

			if (incomingRequest.containsKey("SungardVendorContacts_internalVendorId"))
			{
			    String internalVendorIdString = (String) incomingRequest.get("SungardVendorContacts_internalVendorId");
				if (Utility.isEmpty(internalVendorIdString))
				{
				    internalVendorIdString = "0";
				}
				BigDecimal internalVendorId = new BigDecimal ( internalVendorIdString );
				vendorContacts.setInternalVendorId(internalVendorId);
			}
			if (incomingRequest.containsKey("SungardVendorContacts_associatedAddrId"))
			{
			    String associatedAddrIdString = (String) incomingRequest.get("SungardVendorContacts_associatedAddrId");
				if (Utility.isEmpty(associatedAddrIdString))
				{
				    associatedAddrIdString = "0";
				}
				BigDecimal associatedAddrId = new BigDecimal ( associatedAddrIdString );
				vendorContacts.setAssociatedAddrId(associatedAddrId);
			}
			if (incomingRequest.containsKey("SungardVendorContacts_contactPhoneNum"))
			{
				String contactPhoneNum = (String) incomingRequest.get("SungardVendorContacts_contactPhoneNum");
				vendorContacts.setContactPhoneNum(contactPhoneNum);
			}
			if (incomingRequest.containsKey("SungardVendorContacts_contactFaxNum"))
			{
				String contactFaxNum = (String) incomingRequest.get("SungardVendorContacts_contactFaxNum");
				vendorContacts.setContactFaxNum(contactFaxNum);
			}
			if (incomingRequest.containsKey("SungardVendorContacts_contactName"))
			{
				String contactName = (String) incomingRequest.get("SungardVendorContacts_contactName");
				vendorContacts.setContactName(contactName);
			}
			if (incomingRequest.containsKey("SungardVendorContacts_contactEmailAddr"))
			{
				String contactEmailAddr = (String) incomingRequest.get("SungardVendorContacts_contactEmailAddr");
				vendorContacts.setContactEmailAddr(contactEmailAddr);
			}

			result = vendorContacts;
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