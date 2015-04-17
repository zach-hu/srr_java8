package com.tsa.puridiom.vendoraccount.tasks;

import com.tsa.puridiom.entity.VendorAccount;
import com.tsa.puridiom.entity.VendorAccountPK;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorAccountSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorAccountPK comp_id = new VendorAccountPK();
			VendorAccount vendorAccount = (VendorAccount) incomingRequest.get("vendorAccount");
			if (vendorAccount == null)
			{
				vendorAccount = new VendorAccount();
			}

			if (incomingRequest.containsKey("VendorAccount_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("VendorAccount_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorAccount_accountNumber"))
			{
				String accountNumber = (String) incomingRequest.get("VendorAccount_accountNumber");
				comp_id.setAccountNumber(accountNumber);
			}
			if (incomingRequest.containsKey("VendorAccount_description"))
			{
				String description = (String) incomingRequest.get("VendorAccount_description");
				vendorAccount.setDescription(description);
			}

			vendorAccount.setComp_id(comp_id);

			result = vendorAccount;
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
