package com.tsa.puridiom.disbline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DisbLineRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icDsbHeaderString = (String) incomingRequest.get("DisbHeader_icDsbHeader");
			BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );

			String queryString = "from DisbLine as disbLine where disbLine.icDsbHeader = ? ";

			String dontPrintCancelledItems = (String)incomingRequest.get("dontPrintCancelledItems");
			String cancelledItems = (String)incomingRequest.get("cancelledItems");
			if( (dontPrintCancelledItems != null && dontPrintCancelledItems.equalsIgnoreCase("Y")) ||
				(cancelledItems != null && cancelledItems.equalsIgnoreCase("Y")) )
			{
				queryString += " and disbLine.status <> '9020'";
			}

			result = dbs.query(queryString, new Object[] {icDsbHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

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