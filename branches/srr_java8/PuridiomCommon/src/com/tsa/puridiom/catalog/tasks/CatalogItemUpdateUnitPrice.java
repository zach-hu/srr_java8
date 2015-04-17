/**
 * Created on Mar 2, 2004
 * @author renzo
 * com.tsa.puridiom.invitem.tasks.InvItemUpdateUnitPrice.java
 *
 */
package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class CatalogItemUpdateUnitPrice extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String cost = ((BigDecimal)incomingRequest.get("unitPrice")).toString();
			String item = (String)incomingRequest.get("itemNumber");
			String catalogId = (String)incomingRequest.get("catalogId");
			String sql = "UPDATE catalog_item SET cost = ? WHERE catalog_id = ? AND item_number = ?";
			Object [] args = new Object[3];
			Integer [] types = new Integer[3];
			args[0] = cost;
			types[0] = Types.VARCHAR;
			args[1] = catalogId;
			types[1] = Types.VARCHAR;
			args[2] = item;
			types[2] = Types.VARCHAR;
			this.setStatus(dbs.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
