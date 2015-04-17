package com.tsa.puridiom.hostuser.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class HostUserDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
	    DBSession dbs = null;

		try
		{
		    HostUser hostUser = (HostUser)incomingRequest.get("hostUser");
			if(hostUser == null)
			{
				hostUser = new HostUser();
				hostUser.setMailId((String) incomingRequest.get("HostUser_mailId"));
			}

			dbs = (DBSession)incomingRequest.get("dbsession") ;
			if (!dbs.getSessionOrganizationId().equalsIgnoreCase("host")) {
				try {
					//	Always use host database configuration for this table
					dbs = new DBSession("host") ;
					dbs.startTransaction();
					dbs.delete(hostUser);
					dbs.commit();
					
					this.setStatus(dbs.getStatus()) ;
					dbs.close();
				}
				catch (Exception e1)
				{
				    if (dbs != null)
				    {
				        dbs.rollback();
				    }
					this.setStatus(Status.FAILED);
					throw e1;
				}
			} else {
				dbs.delete(hostUser);
				this.setStatus(dbs.getStatus()) ;
			}

			result = hostUser;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}