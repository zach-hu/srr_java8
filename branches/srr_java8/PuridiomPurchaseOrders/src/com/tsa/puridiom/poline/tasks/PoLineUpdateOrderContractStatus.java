/**
 * Created on Jan 17, 2005
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoUpdateAmmendedRevision.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

public class PoLineUpdateOrderContractStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
		    BigDecimal icPoHeader = (BigDecimal)incomingRequest.get("originalIc");

			String sql = "Update po_line set status = ? where ic_po_header = ? and status != ?";

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			Object [] args = {DocumentStatus.CT_CLOSED, icPoHeader, DocumentStatus.CANCELLED};
			Integer [] types = {Types.VARCHAR, Types.DECIMAL, Types.VARCHAR};
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
