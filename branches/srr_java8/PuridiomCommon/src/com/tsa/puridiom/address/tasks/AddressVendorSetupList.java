package com.tsa.puridiom.address.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AddressVendorSetupList extends Task {

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			List addressVendorList = (List) incomingRequest.get("addressVendorList");
			for(int x=0;x< addressVendorList.size();x++){
				Address address = (Address)addressVendorList.get(x);
				address.setStatus("03");
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}

