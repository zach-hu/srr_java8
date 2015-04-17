package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class PoUpdateStatusRequisition extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			List poLineList = (List)incomingRequest.get("poLineList");

			if (poHeader != null && poHeader.getIcReqHeader().compareTo(new BigDecimal(0)) > 0)
			{
				String icReqHeader = poHeader.getIcReqHeader().toString();
				String status = poHeader.getStatus();
				String sqlUpdate = "update Requisition_Header set status = ? where Ic_Req_Header = ?";
				Object [] args = {status, icReqHeader};
				Integer [] types = {Types.VARCHAR, Types.VARCHAR};
				
				dbs.sqlUpdate(sqlUpdate, args, types);
    			this.setStatus(dbs.getStatus());
			}

			if (poLineList != null && poLineList.size() > 0)
			{
				for (int i = 0; i < poLineList.size(); i++)
				{
					PoLine poLine = (PoLine)poLineList.get(i);
					if (poLine != null && poLine.getIcReqLine().compareTo(new BigDecimal(0)) > 0)
					{
						String icReqLine = poLine.getIcReqLine().toString();
						String status = poLine.getStatus();
						String sqlUpdate = "update Requisition_Line set status = ? where Ic_Req_Line = ?";
						Object [] args = {status, icReqLine};
						Integer [] types = {Types.VARCHAR, Types.VARCHAR};
						
						dbs.sqlUpdate(sqlUpdate, args, types);
		    			this.setStatus(dbs.getStatus());
					}
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
