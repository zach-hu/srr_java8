package com.tsa.puridiom.alert.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.database.DBSession;
import java.util.List;
import java.util.Map;

public class AlertRetrieveAll extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
		public Object  executeTask (Object object) throws Exception
		{
			Map incomingRequest = (Map)object;
			List result = null;

			try
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession");
				String queryString = "from AlertConfig as alertConfig";
				result = dbs.query(queryString) ;
				this.setStatus(dbs.getStatus());
			}
			catch (Exception e)
			{
				this.setStatus(Status.FAILED);
				throw e;
			}
			return result;
		}

	}