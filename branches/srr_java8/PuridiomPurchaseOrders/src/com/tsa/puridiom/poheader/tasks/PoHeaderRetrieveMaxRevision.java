package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
/**
 *
 * @author mdanz
 *
 */
public class PoHeaderRetrieveMaxRevision extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			if (poHeader == null)
			{
				throw new Exception("PoHeader can not be null");
			}
			String poNumber = poHeader.getPoNumber();
			BigDecimal releaseNumber = poHeader.getReleaseNumber();
			
			String queryString = "select max(PoHeader.revisionNumber) from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.releaseNumber = ?";
			List resultList = dbs.query(queryString, new Object[] {poNumber,releaseNumber}, new Type[] {Hibernate.STRING,Hibernate.BIG_DECIMAL});
			
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "There was a problem trying to retrieve the max revision number for the PO");
			e.printStackTrace();
			throw e;
		}

		return result;
	}
}
