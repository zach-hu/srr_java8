package com.tsa.puridiom.catalogpricebrk.tasks;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class CatalogPriceBrkRetrieveByItem extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String catalogId = (String ) incomingRequest.get("CatalogPriceBrk_catalogId");

		String itemNumber = (String ) incomingRequest.get("CatalogPriceBrk_itemNumber");

		String queryString = "from CatalogPriceBrk as catalogPriceBrk where catalogPriceBrk.id.catalogId = ? AND catalogPriceBrk.id.itemNumber = ?";
		List result = dbs.query(queryString, new Object[] {catalogId, itemNumber} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}

}