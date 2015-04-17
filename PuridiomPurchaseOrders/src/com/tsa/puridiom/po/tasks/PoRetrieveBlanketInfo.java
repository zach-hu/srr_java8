/**
 * 
 * Created on Feb 13, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoRetrieveBlanketInfo.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


public class PoRetrieveBlanketInfo extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = (String)incomingRequest.get("poNumber");
			UserErrors errors = (UserErrors)incomingRequest.get("userErrors");
			
			String queryString = "from PoHeader as poHeader where poHeader.lastRevision = 'C' AND " +
					"(poHeader.poType = 'BO' OR poHeader.poType = 'SB' OR poHeader.poType = 'DO') AND" +
					"  poHeader.poNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {poNumber } , new Type[] { Hibernate.STRING}) ;
			if(resultList == null && resultList.size() < 1)
			{
				if(errors == null){	errors = new UserErrors();	}
				errors.addError(PoErrors.NOBLANKET, PoErrors.NOBLANKETCODE);
				incomingRequest.put("userErrors", errors);
			}
			else
			{
				result = resultList.get(0);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
