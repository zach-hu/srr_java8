package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class CatalogItemManager
{
	private static CatalogItemManager INSTANCE = new CatalogItemManager();

	private CatalogItemManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.catalog.tasks.CatalogItemManager
	 */
	public static CatalogItemManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new CatalogItemManager();
		}
		return INSTANCE;
	}
    /**
     * @return com.tsa.puridiom.entity.CatalogItem
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
	public Object getCatalogItem(String organizationId, String catalogId, String itemNumber) throws java.lang.Exception
	{
		Object result = null;
		try
		{
			if (Utility.isEmpty(catalogId) || Utility.isEmpty(itemNumber) || Utility.isEmpty(organizationId))
			{
				result = new CatalogItem();
				return result;
			}
			else
			{
				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("CatalogItem_catalogId", catalogId);
				incomingRequest.put("CatalogItem_itemNumber", itemNumber);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("catalog-item-retrieve-by-id.xml");

				process.executeProcess(incomingRequest);

				result = incomingRequest.get("catalogItem");
				if (result == null)
				{
					result = new CatalogItem();
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	 public BigDecimal getLeadDays(String organizationId, String catalogId, String itemNumber) throws java.lang.Exception {

	    	BigDecimal leadDays = new BigDecimal("0");
	    	Object result = null;
	        try
			{
				if (!Utility.isEmpty(catalogId) && !Utility.isEmpty(itemNumber) && !Utility.isEmpty(organizationId))
				{
					Map incomingRequest = new HashMap();
					incomingRequest.put("organizationId", organizationId);
					incomingRequest.put("CatalogItem_catalogId", catalogId);
					incomingRequest.put("CatalogItem_itemNumber", itemNumber);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("catalog-item-retrieve-by-id.xml");

					process.executeProcess(incomingRequest);
					result = incomingRequest.get("catalogItem");
				}
				if (result != null)
				{
					CatalogItem catalogItem = (CatalogItem) result;
					leadDays = catalogItem.getLeadtime();
				}
			}

	        catch (Exception e) {
	        	Log.debug(this, e.getMessage());
				e.printStackTrace();
				throw e;
	        }
	        finally {
	            return leadDays;
	        }
	    }

}