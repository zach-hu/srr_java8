package com.tsa.puridiom.sungard.address.tasks;

import com.tsa.puridiom.entity.sungard.Address;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class SungardAddressSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Address address = (Address) incomingRequest.get("sungardAddress");
			if (address == null)
			{
				address = new Address();
			}

			if (incomingRequest.containsKey("SungardAddress_internalVendorId"))
			{
			    String internalVendorIdString = (String) incomingRequest.get("SungardAddress_internalVendorId");
				if (Utility.isEmpty(internalVendorIdString))
				{
				    internalVendorIdString = "0";
				}
				BigDecimal internalVendorId = new BigDecimal ( internalVendorIdString );
				address.setInternalVendorId(internalVendorId);
			}
			if (incomingRequest.containsKey("SungardAddress_associatedAddrId"))
			{
			    String associatedAddrIdString = (String) incomingRequest.get("SungardAddress_associatedAddrId");
				if (Utility.isEmpty(associatedAddrIdString))
				{
				    associatedAddrIdString = "0";
				}
				BigDecimal associatedAddrId = new BigDecimal ( associatedAddrIdString );
				address.setAssociatedAddrId(associatedAddrId);
			}
			if (incomingRequest.containsKey("SungardAddress_addressTypeInd"))
			{
				String addressTypeInd = (String) incomingRequest.get("SungardAddress_addressTypeInd");
				address.setAddressTypeInd(addressTypeInd);
			}
			if (incomingRequest.containsKey("SungardAddress_defaultInd"))
			{
				String defaultInd = (String) incomingRequest.get("SungardAddress_defaultInd");
				address.setDefaultInd(defaultInd);
			}
			if (incomingRequest.containsKey("SungardAddress_state"))
			{
				String state = (String) incomingRequest.get("SungardAddress_state");
				address.setState(state);
			}
			if (incomingRequest.containsKey("SungardAddress_zipcode"))
			{
				String zipcode = (String) incomingRequest.get("SungardAddress_zipcode");
				address.setZipcode(zipcode);
			}
			if (incomingRequest.containsKey("SungardAddress_countryCode"))
			{
				String countryCode = (String) incomingRequest.get("SungardAddress_countryCode");
				address.setCountryCode(countryCode);
			}
			if (incomingRequest.containsKey("SungardAddress_genericName2"))
			{
				String genericName2 = (String) incomingRequest.get("SungardAddress_genericName2");
				address.setGenericName2(genericName2);
			}
			if (incomingRequest.containsKey("SungardAddress_genericName3"))
			{
				String genericName3 = (String) incomingRequest.get("SungardAddress_genericName3");
				address.setGenericName3(genericName3);
			}
			if (incomingRequest.containsKey("SungardAddress_address1"))
			{
				String address1 = (String) incomingRequest.get("SungardAddress_address1");
				address.setAddress1(address1);
			}
			if (incomingRequest.containsKey("SungardAddress_address2"))
			{
				String address2 = (String) incomingRequest.get("SungardAddress_address2");
				address.setAddress2(address2);
			}
			if (incomingRequest.containsKey("SungardAddress_address3"))
			{
				String address3 = (String) incomingRequest.get("SungardAddress_address3");
				address.setAddress3(address3);
			}
			if (incomingRequest.containsKey("SungardAddress_city"))
			{
				String city = (String) incomingRequest.get("SungardAddress_city");
				address.setCity(city);
			}

			result = address;
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