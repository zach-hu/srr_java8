/**
 *
 * Created on Jan 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.PoLineGetNewLineNumber.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class PoLineGetNewLineNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");

			String whereInsert = null;
			if(incomingRequest.get("whereInsert")!= null)
			{
				whereInsert = (String) incomingRequest.get("whereInsert");
			}

			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
			BigDecimal poLineNumber = new BigDecimal("0");

			String queryString = "select max(poline.lineNumber) from PoLine as poline where poline.icPoHeader = ? ";
			List resultList = dbs.query(queryString, icPoHeader,  Hibernate.BIG_DECIMAL) ;

			if (resultList != null && resultList.size() > 0)
			{
				poLineNumber = (BigDecimal) resultList.get(0);
				if (poLineNumber == null) {
					poLineNumber = new BigDecimal(0) ;
				}
				result = poLineNumber.add(new BigDecimal(1)).toString();

				this.setStatus(dbs.getStatus()) ;
			}
			else
			{
				result = "1";
				this.setStatus(Status.SUCCEEDED);
			}

			if(whereInsert != null && whereInsert.equalsIgnoreCase("Before"))
			{
				poLineNumber = new BigDecimal("0");
				result = String.valueOf(poLineNumber);
				this.setStatus(Status.SUCCEEDED);
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.getMessage() + "IC_POHEADER: " + incomingRequest.get("PoHeader_icPoHeader"));
			throw e;
		}
		return result;
	}
}