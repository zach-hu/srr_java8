package com.tsa.puridiom.contact.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ContactVendorSetupList extends Task {

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			List contactVendorList = (List) incomingRequest.get("contactVendorList");
			for(int x=0;x< contactVendorList.size();x++){
				Contact contact = (Contact)contactVendorList.get(x);
				contact.setStatus("03");
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
