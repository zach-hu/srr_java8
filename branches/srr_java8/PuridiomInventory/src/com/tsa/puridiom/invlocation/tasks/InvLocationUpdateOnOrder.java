/**
 * Created on Apr 8, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invlocation.tasks.InvLocationUpdateOnOrder.java
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

public class InvLocationUpdateOnOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			BigDecimal onOrder = (BigDecimal)incomingRequest.get("qtyOnOrder");
			String itemNumber = (String)incomingRequest.get("InvLocation_itemNumber");
			String itemLocation = (String)incomingRequest.get("InvLocation_itemLocation");
			String sql = "Update inv_Location set qty_on_order = ? where item_number = ? AND item_location = ?";
			DBSession dbsession = (DBSession)incomingRequest.get("dbsession");
			Object [] args = new Object[3];
			Integer [] types = new Integer[3];
			args[0] = onOrder.toString();
			types[0] = Types.VARCHAR;
			args[1] = itemNumber;
			types[1] = Types.VARCHAR;
			args[2] = itemLocation;
			types[2] = Types.VARCHAR;
			
			this.setStatus(dbsession.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
