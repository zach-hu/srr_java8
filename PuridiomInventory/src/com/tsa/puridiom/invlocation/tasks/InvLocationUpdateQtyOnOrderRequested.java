/**
 * Created on Mar 10, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invlocation.tasks.InvLocationUpdateQtyOnOrderRequested.java
 *
 */
package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvLocationUpdateQtyOnOrderRequested
 */
public class InvLocationUpdateQtyOnOrderRequested extends Task
{
	/*
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			BigDecimal newQtyOnOrder = (BigDecimal)incomingRequest.get("newQtyOnOrder");
			BigDecimal newQtyRequested = (BigDecimal)incomingRequest.get("newQtyRequested");
			String item = (String) incomingRequest.get("InvLocation_itemNumber");
			String location = (String) incomingRequest.get("InvLocation_itemLocation");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String update = "UPDATE inv_location  SET qty_on_order = ?," +
						" qty_requested = ? WHERE ( item_number =?) AND " +
						" ( item_location = ? )";
			Object [] args = new Object[4];
			Integer [] types = new Integer[4];
			args[0] = newQtyOnOrder.toString();
			types[0] = Types.VARCHAR;
			args[1] = newQtyRequested.toString();
			types[1] = Types.VARCHAR;
			args[2] = item;
			types[2] = Types.VARCHAR;
			args[3] = location;
			types[3] = Types.VARCHAR;

			this.setStatus(dbs.sqlUpdate(update, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
