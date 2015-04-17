package com.tsa.puridiom.noncapitalentitydepartment;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.*;

public class NonCapitalEntityDepartmentDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		NonCapitalEntityDepartment nonCapitalEntityDepartment = (NonCapitalEntityDepartment)incomingRequest.get("nonCapitalEntityDepartment");

		try
		{
			if(nonCapitalEntityDepartment == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("NonCapitalEntityDepartment entity was not found!");
			}

			dbs.delete(nonCapitalEntityDepartment);
			this.setStatus(dbs.getStatus()) ;
			return nonCapitalEntityDepartment ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
	}

}
