/**
 * Created on Apr 23, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationRetrieveByLocation.java
 *
 */
package com.tsa.puridiom.invproperty.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvBinLocationRetrieveByLocation
 */
public class InvPropertyRetrieveByIcRc extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String status = HiltonUtility.ckNull((String)incomingRequest.get("DisbHeader_status"));
			String icDsbLine = HiltonUtility.ckNull((String)incomingRequest.get("DisbLine_icDsbLine"));

			StringBuffer queryString = new StringBuffer("from InvProperty as invProperty ");

			if (HiltonUtility.isEmpty(status) || status.compareTo(DocumentStatus.INV_INPROGRESS) == 0)
			{
				queryString.append("where status = '02'");

				if(incomingRequest.containsKey("InvBinLocation_icRc"))
				{
					String icRc = (String) incomingRequest.get("InvBinLocation_icRc");
					queryString.append(" AND invProperty.icRc = " + icRc);
				}
				else
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("Property can not be Empty!");
				}
			}
			else
			{
				queryString.append("where icDsbLine = " + icDsbLine);

				if(incomingRequest.containsKey("InvBinLocation_icRc"))
				{
					String icRc = (String) incomingRequest.get("InvBinLocation_icRc");
					queryString.append(" AND invProperty.icRc = " + icRc);
				}
				else
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("Property can not be Empty!");
				}
			}

//			queryString.append(" AND (invbinlocation.status is null OR invbinlocation.status <> '00')");

			result = dbs.query(queryString.toString());
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
