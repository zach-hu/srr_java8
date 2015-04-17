/**
 * Created on Apr 1, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoUpdateOriginalRevisionNumber.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoUpdateBlanketLastRevisionNumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String newRevisionNumber = (String)incomingRequest.get("newPoHeader_revisionNumber");
			
		    BigDecimal originalIc = (BigDecimal)incomingRequest.get("originalIc");
			String sql = "Update po_header set last_revision = ? where ic_po_header = ?";
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			Object [] args = {newRevisionNumber, originalIc};
			Integer [] types = {Types.VARCHAR, Types.DECIMAL};
			
			this.setStatus(dbs.sqlUpdate(sql, args, types));
			//dbs.update(original);
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
