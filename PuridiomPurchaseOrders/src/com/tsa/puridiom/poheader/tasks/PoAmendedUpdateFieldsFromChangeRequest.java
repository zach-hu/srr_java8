package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoAmendedUpdateFieldsFromChangeRequest extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			BigDecimal icPoHeader = (BigDecimal) incomingRequest.get("originalIc");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");

			if(!HiltonUtility.isEmpty(requisitionHeader.getBuyer()))
			{
				String sql = "Update po_header set buyer_code = ? where ic_po_header = ?";
				Log.debug(this, "Updated " + requisitionHeader.getBuyer() + " on Order " + icPoHeader);
				Object [] args = {requisitionHeader.getBuyer(), icPoHeader};
				Integer [] types = {Types.VARCHAR, Types.DECIMAL};
				this.setStatus(dbs.sqlUpdate(sql, args, types));
			}
			else
			{
				Log.debug(this, "Warning  " + requisitionHeader.getBuyer() + " is emtpy or null and update is no ran on " + icPoHeader);
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return null;
	}

}
