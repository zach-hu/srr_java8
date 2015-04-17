package com.tsa.puridiom.catalogpricebrk.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class CatalogPriceBrkDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk)incomingRequest.get("catalogPriceBrk");
		if(catalogPriceBrk == null)
		{
			catalogPriceBrk = new CatalogPriceBrk();
		}
		CatalogPriceBrkSetValuesPK setValues = new CatalogPriceBrkSetValuesPK();
		setValues.setValues(incomingRequest, catalogPriceBrk);
		dbs.delete(catalogPriceBrk);
		this.setStatus(dbs.getStatus()) ;
		return catalogPriceBrk ;
	}

}