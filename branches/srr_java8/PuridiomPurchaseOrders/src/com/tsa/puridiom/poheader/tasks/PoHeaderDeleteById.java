package com.tsa.puridiom.poheader.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.*;

public class PoHeaderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			int status = Integer.parseInt(poHeader.getStatus());
			if(status <= 2990 && status >= 2900 ){
				incomingRequest.put("TypeOrder", "contract");
			}else {
				incomingRequest.put("TypeOrder", "order");
			}
			if(poHeader == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Po entity was not found!");
			}

			dbs.delete(poHeader);
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}