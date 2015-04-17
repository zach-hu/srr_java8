package com.tsa.puridiom.receiptheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RecIcHeaderRetrieveByLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecLineString = (String) incomingRequest.get("ReceiptLine_icRecLine");
			if (Utility.isEmpty(icRecLineString))
			{
				throw new Exception("ReceiptLine_icRecLine cannot be empty.  Receipt could not be retrieved.");
			}
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );

			String queryString = "select rl.icRecHeader from ReceiptLine as rl where rl.icRecLine = ? ";
			List resultList = dbs.query(queryString, new Object[] { icRecLine } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
				incomingRequest.put("ReceiptHeader_icRecHeader", result.toString());
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}