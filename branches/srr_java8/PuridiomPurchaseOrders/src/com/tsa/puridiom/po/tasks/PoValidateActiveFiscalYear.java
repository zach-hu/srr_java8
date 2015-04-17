/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.po.tasks;

import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;

/**
 * @author Renzo
*/
public class PoValidateActiveFiscalYear extends Task
{

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	fiscalYear = (String) incomingRequest.get("AutoGen_genYear");
			String	type = (String) incomingRequest.get("AutoGen_documentType");
			String	activeYear = "N";

			String queryString = "select AutoGen.activeYear from AutoGen as AutoGen where AutoGen.id.documentType = ? and AutoGen.id.genYear = ? ";
			List resultList = dbs.query(queryString, new Object[] {type, fiscalYear, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				activeYear = (String) resultList.get(0);
			}
			if (activeYear != null && activeYear.equalsIgnoreCase("Y"))
			{
				result = "true";
			}
			else
			{
				Log.error(this,"An active fiscal year is required for fiscal year auto-numbering.");
				throw new Exception("An active fiscal year is required for fiscal year auto-numbering.");
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}
