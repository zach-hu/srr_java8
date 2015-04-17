package com.tsa.puridiom.catalogpricebrk.tasks;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class CatalogPriceBrkRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from CatalogPriceBrk as catalogPriceBrk ";
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		//system.out.println("task status: " + this.getStatus());
		return result ;
	}

}