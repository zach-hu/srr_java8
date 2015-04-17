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

public class PoUpdateStatusSolicitation extends Task
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
				String icRfqHeader = poHeader.getIcRfqHeader().toString();
				String status = poHeader.getStatus();
				String sqlUpdate = "update Rfq_Header set status = ? where Ic_Rfq_Header = ?";
				Object [] args = {status, icRfqHeader};
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
						String icRfqLine = poLine.getIcRfqLine().toString();
						String status = poLine.getStatus();
						String sqlUpdate = "update Rfq_Line set status = ? where Ic_Rfq_Line = ?";
						Object [] args = {status, icRfqLine};
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
