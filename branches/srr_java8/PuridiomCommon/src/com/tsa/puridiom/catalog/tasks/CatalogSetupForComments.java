/**
 * 
 */
package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Johnny
 */
public class CatalogSetupForComments extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Catalog catalog = (Catalog) incomingRequest.get("catalog");
			CatalogItem catalogItem = (CatalogItem) incomingRequest.get("catalogItem");
			
			incomingRequest.put("isNotEmptyIcHeaderComment", "Y");
			incomingRequest.put("isNotEmptyIcLineComment", "Y");

			if (catalog.getIcHeaderComment().compareTo(new BigDecimal(0)) == 0)
			{

				incomingRequest.put("Catalog_icHeaderComment", ukg.getUniqueKey().toString());
				incomingRequest.put("isNotEmptyIcHeaderComment", "N");
			}

			if (catalogItem.getIcLineComment().compareTo(new BigDecimal(0)) == 0)
			{
				incomingRequest.put("CatalogItem_icLineComment", ukg.getUniqueKey().toString());
				incomingRequest.put("isNotEmptyIcLineComment", "N");
			}

			this.status = Status.SUCCEEDED;
		} catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}