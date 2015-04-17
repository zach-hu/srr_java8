

package com.tsa.puridiom.catalogsecurity.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class CatalogSecurityCreateSetup extends Task
{

	public Object executeTask (Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map) object;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			incomingRequest.put("CatalogSecurity_icHeader", ukg.getUniqueKey().toString());
			
			String isCatalog = "F";
			if (incomingRequest.containsKey("CatalogSecurity_catalogId") && incomingRequest.containsKey("CatalogSecurity_itemNumber"))
			{
				String catalogId = (String ) incomingRequest.get("CatalogSecurity_catalogId");
				String itemNumber = (String ) incomingRequest.get("CatalogSecurity_itemNumber");
				
				if(!catalogId.equalsIgnoreCase("") && itemNumber.equalsIgnoreCase("0")){				
					isCatalog = "V";					
				}
				incomingRequest.put("isCatalog", isCatalog);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}