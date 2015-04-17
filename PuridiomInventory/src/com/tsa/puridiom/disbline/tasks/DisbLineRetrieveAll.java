package com.tsa.puridiom.disbline.tasks;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class DisbLineRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		List result = null;
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String queryString = "from DisbLine as disbLine";
			result = dbs.query(queryString) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
		}
		finally
		{
			return result ;
		}
	}

}