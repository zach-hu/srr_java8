package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoLineFromReqSetupUmConv extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			String umConv = "";

			if (reqLine != null)
			{
				String catalogId = reqLine.getCatalogId();
				String itemNumber = reqLine.getItemNumber();

				String sql = "select ci.umConv from CatalogItem as ci where ci.id.catalogId = ? and ci.id.itemNumber = ? order by ci.umConv";
				List lst = dbs.query(sql, new Object[] { catalogId, itemNumber }, new Type[] { Hibernate.STRING, Hibernate.STRING });

				if ((lst != null) && (lst.size() > 0))
				{
					umConv = (String) lst.get(0);
				}
			}

			incomingRequest.put("PoLine_umConv", umConv);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			Log.error(this, "PoLineFromReqSetupUmConv failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
