/**
 * Created on Feb 25, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoHeaderUpdateStatusForRelease.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineUpdateRevisions extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			
			String query = "Update po_header set status = ? where po_number = ? AND " +
							" release_number = ?";
			Object [] args = {poHeader.getStatus(), poHeader.getPoNumber(), poHeader.getReleaseNumber().toString()};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			this.setStatus(dbs.sqlUpdate(query, args, types));
			
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
