package com.tsa.puridiom.sungard.keyid.tasks;

import com.tsa.puridiom.entity.sungard.KeyId;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SungardKeyIdUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			KeyId keyId = (KeyId)incomingRequest.get("keyId");
			if (keyId == null)
			{
				throw new Exception ("KeyId was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(keyId);
		
			result = keyId;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}