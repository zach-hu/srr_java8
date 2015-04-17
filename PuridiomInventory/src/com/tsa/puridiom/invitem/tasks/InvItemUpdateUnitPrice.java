/**
 * Created on Mar 2, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invitem.tasks.InvItemUpdateUnitPrice.java
 *
 */
package com.tsa.puridiom.invitem.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvItemUpdateUnitPrice extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String cost = ((BigDecimal)incomingRequest.get("unitPrice")).toString();
			String item = (String)incomingRequest.get("itemNumber");
			String sql = "UPDATE inv_item SET cost = ? WHERE item_number = ?";
			Object [] args = new Object[2];
			Integer [] types = new Integer[2];
			args[0] = cost;
			types[0] = Types.VARCHAR;
			args[1] = item;
			types[1] = Types.VARCHAR;
			
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
