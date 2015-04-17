package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqLineLookupSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ItemLookup item = (ItemLookup) incomingRequest.get("itemLookup") ;
			if (item == null) {
				result = null ;
				this.setStatus(Status.FAILED) ;
			} else {
				incomingRequest.put("RfqLine_catalogId",item.getCatalogId()) ;
				incomingRequest.put("RfqLine_itemNumber",item.getItemNumber()) ;
				if(item.getSource().equalsIgnoreCase("XML") && !item.getCatalogId().equalsIgnoreCase("HAGEMEYER"))
				{
					incomingRequest.put("RfqLine_commodity",item.getCommodity()) ;
				}
				else if(!item.getSource().equalsIgnoreCase("XML"))
				{
					incomingRequest.put("RfqLine_commodity",item.getCommodity()) ;
				}
				incomingRequest.put("RfqLine_description",item.getDescription()) ;
				incomingRequest.put("RfqLine_itemLocation",item.getLocation()) ;
				incomingRequest.put("RfqLine_source",item.getSource()) ;
				incomingRequest.put("RfqLine_receiptRequired",item.getReceiptRequired()) ;
				incomingRequest.put("RfqLine_umCode",item.getUnitOfOrder()) ;
				incomingRequest.put("RfqLine_unitPrice",item.getOrderCost().toString()) ;
				incomingRequest.put("RfqLine_udf1Code",item.getUdf01()) ;
				incomingRequest.put("RfqLine_udf2Code",item.getUdf02()) ;
				incomingRequest.put("RfqLine_udf3Code",item.getUdf03()) ;
				incomingRequest.put("RfqLine_udf4Code",item.getUdf04()) ;
				incomingRequest.put("RfqLine_udf5Code",item.getUdf05()) ;
				incomingRequest.put("RfqLine_mfgName",item.getMfgName()) ;
				incomingRequest.put("RfqLine_modelNumber",item.getModel()) ;
				incomingRequest.put("RfqLine_asset",item.getAsset());
				incomingRequest.put("RfqLine_taxable",item.getTaxable());
				if (!incomingRequest.containsKey("RfqLine_quantity")) {
					incomingRequest.put("RfqLine_quantity",item.getQuantity().toString()) ;
				}
			}
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