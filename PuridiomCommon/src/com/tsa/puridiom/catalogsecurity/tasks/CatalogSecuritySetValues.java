package com.tsa.puridiom.catalogsecurity.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;


import com.tsa.puridiom.entity.CatalogSecurity;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;


public class CatalogSecuritySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CatalogSecurity catalogSecurity = (CatalogSecurity) incomingRequest.get("catalogSecurity");
			if (catalogSecurity == null)
			{
				catalogSecurity = new CatalogSecurity();
			}

			if (incomingRequest.containsKey("CatalogSecurity_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("CatalogSecurity_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal(icHeaderString) ;
				catalogSecurity.setIcHeader(icHeader);
			}

			if (incomingRequest.containsKey("CatalogSecurity_catalogId"))
			{
				String catalogId = (String ) incomingRequest.get("CatalogSecurity_catalogId");
				catalogSecurity.setCatalogId(catalogId);
			}
			if (incomingRequest.containsKey("CatalogSecurity_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("CatalogSecurity_itemNumber");
				catalogSecurity.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("CatalogSecurity_accessType"))
			{
				String accessType = (String) incomingRequest.get("CatalogSecurity_accessType");
				catalogSecurity.setAccessType(accessType);
			}
			if (incomingRequest.containsKey("CatalogSecurity_accessId"))
			{
				String accessId = (String ) incomingRequest.get("CatalogSecurity_accessId");
				catalogSecurity.setAccessId(accessId);
			}
			if (incomingRequest.containsKey("CatalogSecurity_accessDescription"))
			{
				String accessDescription = (String ) incomingRequest.get("CatalogSecurity_accessDescription");
				catalogSecurity.setAccessDescription(accessDescription);
			}
			if (incomingRequest.containsKey("CatalogSecurity_owner"))
			{
				String owner = (String ) incomingRequest.get("CatalogSecurity_owner");
				catalogSecurity.setOwner(owner);
			}
			if (incomingRequest.containsKey("CatalogSecurity_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("CatalogSecurity_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				catalogSecurity.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("CatalogSecurity_dateChanged"))
			{
				String dateChangeString = (String) incomingRequest.get("CatalogSecurity_dateChanged");
				Date dateChanged = Dates.getDate(dateChangeString);
				catalogSecurity.setDateChanged(dateChanged);
			}
			if (incomingRequest.containsKey("CatalogSecurity_lastChangedBy"))
			{
				String lastchange = (String ) incomingRequest.get("CatalogSecurity_lastChangedBy");
				catalogSecurity.setLastChangedBy(lastchange);
			}

			result = catalogSecurity;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
