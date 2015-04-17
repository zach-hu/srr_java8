package com.tsa.puridiom.shipto.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ShipToRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("ShipTo_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("ShipTo_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String shipToCode = (String ) incomingRequest.get("ShipTo_shipToCode");
			String shipToPriority = (String ) incomingRequest.get("ShipTo_shipToPriority");

			String queryString = "from ShipTo as ShipTo where ShipTo.id.icHeader = ? and ShipTo.id.icLine = ? and ShipTo.id.shipToCode = ? and ShipTo.id.shipToPriority = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icLine, shipToCode, shipToPriority, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
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