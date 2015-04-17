/**
 * Created on May 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderRetrievelastByNumber.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoSetNewLastRevisionAndUpdate extends Task
{
	/*
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String newIcPoHeader = (String)incomingRequest.get("PoHeader_icPoHeader");

			String sql = "update po_header set last_revision = 'C' where ic_po_header = ?";
			Object [] args = new Object[1];
			Integer [] types = {Types.VARCHAR};

			args[0] = newIcPoHeader;

			dbs.sqlUpdate(sql, args, types);
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
