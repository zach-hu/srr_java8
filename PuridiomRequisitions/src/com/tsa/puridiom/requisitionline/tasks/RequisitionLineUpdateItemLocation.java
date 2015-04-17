package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineUpdateItemLocation extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		List      reqLineList = (List) incomingRequest.get("requisitionLineList");
		String    oid         = (String) incomingRequest.get("organizationId");
		DBSession dbs         = (DBSession)incomingRequest.get("dbsession");

		if (reqLineList != null)
		{
			for (int i = 0; i < reqLineList.size(); i++)
			{
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

				if (incomingRequest.containsKey("RequisitionHeader_itemLocation"))
				{
					String itemLocation = (String ) incomingRequest.get("RequisitionHeader_itemLocation");
					reqLine.setItemLocation(itemLocation);
					dbs.update(reqLine);
				}
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}