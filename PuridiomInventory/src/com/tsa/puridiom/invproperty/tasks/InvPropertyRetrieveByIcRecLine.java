/**
 * Created on Apr 23, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationRetrieveByLocation.java
 *
 */
package com.tsa.puridiom.invproperty.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvBinLocationRetrieveByLocation
 */
public class InvPropertyRetrieveByIcRecLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from InvProperty as invProperty where 1=1 ");

			if(incomingRequest.containsKey("InvProperty_icRecLine"))
			{
				String icRecLine = "";
				Object icRecLineObj = (Object) incomingRequest.get("InvProperty_icRecLine");
				if (icRecLineObj instanceof String[]) {
					icRecLine = ((String[])icRecLineObj)[0];
				} else if (icRecLineObj instanceof String){
					icRecLine = (String)icRecLineObj;
				}
				queryString.append(" AND invProperty.icRecLine = " + icRecLine);
			}
			else
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("IC_REC_LINE can not be Empty!");
			}

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
