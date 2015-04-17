/**
 * Created on Jan 17, 2005
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoUpdateAmmendedRevision.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

public class PoHeaderUpdateAmmendedRevision extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			BigDecimal icPoHeader = (BigDecimal) incomingRequest.get("originalIc");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String sql = "Update po_header set status = ? where ic_po_header = ?";
			Object [] args = {DocumentStatus.PO_AMENDED, icPoHeader};
			Integer [] types = {Types.VARCHAR, Types.DECIMAL};
			this.setStatus(dbs.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}
