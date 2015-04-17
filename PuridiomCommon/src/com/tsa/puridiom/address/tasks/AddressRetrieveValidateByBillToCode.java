package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class AddressRetrieveValidateByBillToCode extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			result = dbs.query("SELECT Address.id.addressCode FROM Address as Address " +
					"WHERE (Address.status <> '03' OR Address.status = 'null' OR Address.status is null) " +
					"AND Address.billTo = 'Y' AND Address.id.addressType <> 'BILLTO' ORDER BY Address.id.addressCode ASC");
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "AddressRetrieveValidateByCode: " + e.getMessage());
		}
		return result;
	}
}