/**
 *
 * Created on Jan 22, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineRetrieveByHeader.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.*;

public class PoLineRetrieveCatByHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	        String icHeader = (String)incomingRequest.get("PoLine_icPoHeader") ;
	        if(Utility.isEmpty(icHeader))
	        {
	        	icHeader = "0";
	        }
			BigDecimal bdHeader = new BigDecimal(icHeader) ;

			String printRevised   = (String)incomingRequest.get("hiddenPrintRevised");
			String printCancelled = (String)incomingRequest.get("hiddenPrintCancelled");

			String whereString = "";
			if(printRevised != null && printRevised.equalsIgnoreCase("Y"))
				whereString += " and line.lineRevNo <> '0'";
			if(printCancelled != null && printCancelled.equalsIgnoreCase("Y"))
				whereString += " and line.status = '9020'";

			String queryString = "From PoLine as line where line.icPoHeader = ? and (line.itemSource is null or line.itemSource = 'CAT')";
			queryString += whereString + " order by line.lineNumber ASC";

			result = dbs.query(queryString, bdHeader, Hibernate.BIG_DECIMAL) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}

		return result ;
	}

}
