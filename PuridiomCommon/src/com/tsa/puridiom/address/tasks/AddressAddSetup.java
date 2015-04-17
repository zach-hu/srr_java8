/*
 * Created on Dec 23, 2003
 * @author Kelli
 * com.tsa.puridiom.address.tasks.AddressAddSetup.java
 */

package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class AddressAddSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			Address address = new Address();

			incomingRequest.put("address", address);

			if (!incomingRequest.containsKey("Address_dateEntered")) {
                String userTimeZone = (String) incomingRequest.get("userTimeZone");
				incomingRequest.put("Address_dateEntered", Dates.today("yyyy-MM-dd", userTimeZone));
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			throw e;
		}
		return ret;
	}
}
