/**
 * 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.Dictionary;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny
 */
public class RequisitionLineSetCommodityDefault extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		String organizationId = (String) incomingRequest.get("organizationId");

		try
		{
			String bundle = "catalog-commodity";
			Dictionary dictionary = DictionaryManager.getInstance(bundle, organizationId);
			ItemLookup item = (ItemLookup) incomingRequest.get("itemLookup");
			String commodityCode;

			if (dictionary == null)
			{
				throw new TsaException("Dictionary catalog-commodity does not exist for organization " + organizationId);
			}

			commodityCode = dictionary.getProperty(item.getCatalogId());

			if (!HiltonUtility.isEmpty(commodityCode))
			{
				incomingRequest.put("RequisitionLine_commodityCode", commodityCode);
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (TsaException te)
		{
			this.setStatus(Status.SUCCEEDED);

			Log.info(this, "Dictionary catalog-commodity could not load for " + organizationId + " : " + te.getMessage());

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionLineSetCommodityDefault error " + e.getMessage());

			throw e;
		}

		return result;
	}
}
