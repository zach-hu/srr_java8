/**
 * 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class CatalogItemFromReqLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map) object;
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");

			incomingRequest.put("CatalogItem_itemNumber", requisitionLine.getItemNumber());

			incomingRequest.put("CatalogItem_description", requisitionLine.getDescription());

			incomingRequest.put("CatalogItem_umCode", requisitionLine.getUmCode());

			incomingRequest.put("CatalogItem_cost", requisitionLine.getUnitPrice().toString());

			incomingRequest.put("CatalogItem_commodity", requisitionLine.getCommodityCode());

			incomingRequest.put("CatalogItem_receiptRequired", requisitionLine.getReceiptRequired());

			incomingRequest.put("CatalogItem_udf1Code", requisitionLine.getUdf3Code());

			incomingRequest.put("CatalogItem_mfgName", requisitionLine.getMfgName());

			incomingRequest.put("CatalogItem_taxable", requisitionLine.getTaxable());

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, e.getMessage());

			throw e;
		}

		return result;
	}
}
