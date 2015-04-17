/**
 * Created on Apr 23, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationRetrieveByLocation.java
 *
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvBinLocationRetrieveByLocation 
 */
public class InvBinLocationRetrieveByLocation extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from InvBinLocation as invbinlocation where 1=1 ");
			
			if(incomingRequest.containsKey("InvBinLocation_itemLocation"))
			{			
				String itemLocation = (String) incomingRequest.get("InvBinLocation_itemLocation");
				queryString.append(" AND invbinlocation.itemLocation = '" + itemLocation + "'");
			}
			else
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("BinLocation can not be Empty!");
			}
			
			queryString.append(" AND (invbinlocation.status is null OR invbinlocation.status <> '00')");
			
			result = dbs.query(queryString.toString()) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}
