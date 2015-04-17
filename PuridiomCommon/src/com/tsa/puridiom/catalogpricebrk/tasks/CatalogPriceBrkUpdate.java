package com.tsa.puridiom.catalogpricebrk.tasks;

import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CatalogPriceBrkUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk)incomingRequest.get("catalogPriceBrk");
			if (catalogPriceBrk == null)
			{
				throw new Exception ("CatalogPriceBrk was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.update(catalogPriceBrk);

			result = catalogPriceBrk;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}