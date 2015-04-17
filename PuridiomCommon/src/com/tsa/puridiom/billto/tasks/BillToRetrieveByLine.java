/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.billto.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator
 */
public class BillToRetrieveByLine extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();
		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeader = (String)incomingRequest.get("BillTo_icHeader");
			String icLine = (String)incomingRequest.get("BillTo_icLine") ;
			if (icLine == null) {	icLine = "0" ;	}

	        String queryString = "from BillTo as b where b.id.icHeader = ? AND b.id.icLine = ?" ;

			BigDecimal bdHeader = new BigDecimal(icHeader);
			BigDecimal bdLine = new BigDecimal(icLine);

			result = dbs.query(queryString,	new Object[] {bdHeader, bdLine}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "BillToRetrieveByLine Failed: " + e.getMessage());
		}
		return result  ;
	}
}
