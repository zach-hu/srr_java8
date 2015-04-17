/**
 * Created on Feb 25, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoHeaderUpdateStatusForRelease.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderUpdateStatusForRelease extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			BigDecimal lineKey = (BigDecimal)incomingRequest.get("lineKey");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			
			String query = "Update poLine set lineStatus = ? where icLineKey = ?";
			Object [] args = {poLine.getStatus(), lineKey.toString()};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR};
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
