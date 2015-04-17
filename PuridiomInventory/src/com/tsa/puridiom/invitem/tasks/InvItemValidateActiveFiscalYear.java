/**
 * 
 */
package com.tsa.puridiom.invitem.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class InvItemValidateActiveFiscalYear extends Task
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String fiscalYear = (String) incomingRequest.get("AutoGen_genYear");
			String type = (String) incomingRequest.get("AutoGen_documentType");
			String activeYear = "N";

			String queryString = "select AutoGen.activeYear from AutoGen as AutoGen where AutoGen.id.documentType = ? and AutoGen.id.genYear = ? ";
			List resultList = dbs.query(queryString, new Object[] { type, fiscalYear, }, new Type[] { Hibernate.STRING, Hibernate.STRING });

			if (resultList != null && resultList.size() > 0)
			{
				activeYear = (String) resultList.get(0);
			}

			if (activeYear != null && activeYear.equalsIgnoreCase("Y"))
			{
				result = "true";
			} else
			{
				Log.error(this, "An active fiscal year is required for fiscal year auto-numbering.");
				throw new Exception("An active fiscal year is required for fiscal year auto-numbering.");
			}

			this.setStatus(Status.SUCCEEDED);
			
		} catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}