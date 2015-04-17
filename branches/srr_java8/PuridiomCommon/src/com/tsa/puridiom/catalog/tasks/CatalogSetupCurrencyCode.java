package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.Catalog;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class CatalogSetupCurrencyCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		if (incomingRequest.containsKey("catalog"))
		{
			Catalog catalog = (Catalog) incomingRequest.get("catalog");
			if (catalog != null) {

		    	incomingRequest.put("currencyCode", catalog.getCurrencyCode());
			}
		}
		this.setStatus(Status.SUCCEEDED);

		return null;
	}
}
