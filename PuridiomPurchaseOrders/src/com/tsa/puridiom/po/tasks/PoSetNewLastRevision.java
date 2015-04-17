/**
 *
 * Created on Jan 22, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineRetrieveByHeader.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoSetNewLastRevision extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String newIcPoHeader = (String)incomingRequest.get("PoHeader_icPoHeader");
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			BigDecimal releaseNumber = (BigDecimal) incomingRequest.get("PoHeader_releaseNumber");
			BigDecimal revisionNumber = (BigDecimal) incomingRequest.get("PoHeader_revisionNumber");
			String revisionNumberString = String.format("%03d", revisionNumber.intValue()) ;

			String sql = "update po_header set last_revision = ? where po_number = ? and release_number = ? and " + 
						 "ic_po_header <> ?";
			Object [] args = {revisionNumberString, poNumber.toString(), releaseNumber.toString(),
							  newIcPoHeader};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			dbs.sqlUpdate(sql, args, types);
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return result ;
	}
}
