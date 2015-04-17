/**
 * 
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny
 */
public class RequisitionGetSingleVendorFromReqLines extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Object result = null;
		Map incomingRequest = (Map) object;

		DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		List requisitionLines = (List) incomingRequest.get("requisitionLineList");
		String vendorId = "";
		boolean isEqual = false;

		for (int i = 0; i < requisitionLines.size(); i++)
		{
			RequisitionLine requisitionLine = (RequisitionLine) requisitionLines.get(i);

			if (i == 0)
			{
				isEqual = true;
				vendorId = requisitionLine.getVendorId();
			}

			if (!vendorId.equals(requisitionLine.getVendorId()))
			{
				isEqual = false;
				break;
			}
		}

		if (isEqual)
		{

		}
		
		incomingRequest.put("hasEqualVendorId", String.valueOf(isEqual));
		incomingRequest.put("vendorAddress", null);

		this.setStatus(dbs.getStatus());

		return result;
	}

}
