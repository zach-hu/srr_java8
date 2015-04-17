package com.tsa.puridiom.catalogpricebrk.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CatalogPriceBrkRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String catalogId = (String) incomingRequest.get("CatalogPriceBrk_catalogId");
			String itemNumber = (String) incomingRequest.get("CatalogPriceBrk_itemNumber");
			String sequenceString = (String) incomingRequest.get("CatalogPriceBrk_sequence");
			BigDecimal sequence = new BigDecimal ( sequenceString );

			String queryString = "from CatalogPriceBrk as CatalogPriceBrk where CatalogPriceBrk.id.catalogId = ? and CatalogPriceBrk.id.itemNumber = ? and CatalogPriceBrk.id.sequence = ? ";
			List resultList = dbs.query(queryString, new Object[] {catalogId, itemNumber, sequence, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.BIG_DECIMAL});

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


