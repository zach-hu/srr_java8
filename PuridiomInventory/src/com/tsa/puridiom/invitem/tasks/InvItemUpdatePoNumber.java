/**
 * Created on Mar 10, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invitem.tasks.InvItemUpdatePoNumber.java
 *
 */
package com.tsa.puridiom.invitem.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvItemUpdatePoNumber extends Task
{

	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String itemNumber = (String)incomingRequest.get("InvItem_itemNumber");
			String poNumber = poHeader.getPoNumber();
			String sql = "Update inv_item set po_number = ? where item_number = ?"; 
			DBSession dbs = (DBSession)incomingRequest.get("bdsession");
			Object [] args = new Object[2];
			Integer [] types = new Integer[2];
			args[0] = poNumber;
			types[0] = Types.VARCHAR;
			args[1] = itemNumber;
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
