package com.tsa.puridiom.vendoraccount.tasks;

import com.tsa.puridiom.entity.VendorAccount;
import com.tsa.puridiom.entity.VendorAccountPK;
import java.util.Map;

public class VendorAccountSetValuesPK
{
	public void setValues(Map incomingRequest, VendorAccount vendoraccount ) throws Exception
	{
		try
		{
			String vendorId = (String ) incomingRequest.get("VendorAccount_vendorId");
			String accountNumber = (String ) incomingRequest.get("VendorAccount_accountNumber");
			VendorAccountPK comp_id = new VendorAccountPK();
			comp_id.setAccountNumber(accountNumber);
			comp_id.setVendorId(vendorId);
			vendoraccount.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}