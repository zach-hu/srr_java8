/*
 * Created on July 16, 2004
 * @author Kelli
 * com.tsa.puridiom.tasks.addressAddressRetrieveBillTo.java
 */
 
package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.List;
import java.util.Map;

public class AddressRetrieveAllInventory extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		List result = null;
		try
		{
			Map incomingRequest = (Map) object;

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String queryString = "from Address as Address where Address.id.addressType = 'ADDR' and Address.inventory = 'Y' ";
			result = dbs.query(queryString);
			
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		
		return result;
	}
}
