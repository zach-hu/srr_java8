/**
 * Created on Apr 1, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoUpdateOriginalLastRevision.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoUpdateOriginalLastRevision extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
			String lastRevision = (String)incomingRequest.get("lastRevision");
			if(!lastRevision.equals("C"))
			{
				String releaseNumber = (String)incomingRequest.get("PoHeader_releaseNumber");
				String sql = "Update po_header set last_revision = ' ' where po_number = ? AND " +
							 "release_number = ? AND last_revision = 'C'";
				DBSession dbs = (DBSession)incomingRequest.get("dbsession");
				Object [] args = {poNumber, releaseNumber};
				Integer [] types = {Types.VARCHAR, Types.VARCHAR};
				
				this.setStatus(dbs.sqlUpdate(sql, args, types));
			}
			else
			{
			    this.setStatus(Status.SUCCEEDED);
			}
			
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
