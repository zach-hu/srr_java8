/*
 * Created on Dec 11, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.disblineDisbLineRetrieveByReqLine.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;


public class DisbLineRetrieveByReqLine extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqLineString = (String) incomingRequest.get("DisbLine_icReqLine");
			if(Utility.isEmpty(icReqLineString))
			{
				throw new Exception("invalid DisbLine_icReqLine");
			}
			BigDecimal icReqLine = new BigDecimal(icReqLineString);

			String queryString = "from DisbLine as disbLine where disbLine.icReqLine = ?";
			ret = dbs.query(queryString, new Object[] {icReqLine } , new Type[] { Hibernate.BIG_DECIMAL}) ;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}