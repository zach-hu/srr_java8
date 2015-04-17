/**
 *
 * Created on Jan 22, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineRetrieveByHeader.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.*;
import org.hibernate.type.Type;

public class PoLineSetNewLastRevisionAndUpdate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String polineIcPoHeader = (String)incomingRequest.get("PoLine_icPoHeader");
			/*
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			BigDecimal releaseNumber = (BigDecimal) incomingRequest.get("PoHeader_releaseNumber");
			BigDecimal revisionNumber = (BigDecimal) incomingRequest.get("PoHeader_revisionNumber");
			String releaseNumberString = releaseNumber.toString();
			String revisionNumberString = revisionNumber.toString();
			 */
			String sql = "update po_line set status = ? where ic_po_header = ?";
			//sql = sql.toUpperCase();
			Object [] args = {DocumentStatus.PO_AWARDED, polineIcPoHeader};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR};
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
