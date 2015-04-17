package com.tsa.puridiom.rfq.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.tasks.SupplierEmailUtility;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqVendor;

public class RfqSupplierEmailUtility extends SupplierEmailUtility
{

	public String getVendorId(Map incomingRequest)
	{
		return (String)incomingRequest.get("PdfRfq_vendorId");
	}

	public String getContactCode(Map incomingRequest)
	{
		String contactCode = "001";
		String vendorId = (String)incomingRequest.get("PdfRfq_vendorId");
		List rfqVendorList = (List)incomingRequest.get("rfqVendorList");
		for(int i =0; i < rfqVendorList.size(); i++)
		{
			RfqVendor rfqVendor = (RfqVendor)rfqVendorList.get(i);
			if(vendorId.equalsIgnoreCase(rfqVendor.getVendorId())){		contactCode = rfqVendor.getContactId();		}
		}
		if(HiltonUtility.isEmpty(contactCode)){		contactCode = "001";
		}
		return contactCode;
	}
}
