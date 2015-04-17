package com.tsa.puridiom.invitem.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.entity.*;

public class InvItemAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvItem invItem = (InvItem)incomingRequest.get("invItem");
			if (invItem == null)
			{
				throw new Exception ("InvItem was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(invItem);
		
			result = invItem;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
		    try {
		        if (e.getCause().getMessage().indexOf("unique constraint") >= 0) {
		            incomingRequest.put("errorMsg", "The item number entered already exists.");
		            this.status = Status.FAILED;
		            return result;
		        }  
	        } catch (Exception e2) {
	        }
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}
		finally
		{
			return result;
		}
	}
}