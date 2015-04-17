/**
 *
 * Created on Jan 21, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineLookupSetValues.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class PoLineLookupSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/*
			PoLine poLine = (PoLine) incomingRequest.get("poLine");

			if (poLine == null)
			{
				result = null ;
				this.status = Status.FAILED ;
				throw new Exception("PoLine was not found!");
			}
			else
			{
			*/
				ItemLookup item = (ItemLookup) incomingRequest.get("itemLookup") ;
				String	organizationId = (String) incomingRequest.get("organizationId");
				if (item == null)
				{
					result = null ;
					this.setStatus(Status.FAILED) ;
					throw new Exception("Item was not found!");
				}
				else
				{
					incomingRequest.put("PoLine_catalogId", item.getCatalogId()) ;
					incomingRequest.put("PoLine_itemNumber",item.getItemNumber()) ;
					String commodity = (String) HiltonUtility.ckNull(item.getCommodity());
					if(item.getSource().equalsIgnoreCase("XML") && !item.getCatalogId().equalsIgnoreCase("HAGEMEYER"))
					{
						incomingRequest.put("PoLine_commodity",commodity) ;
						incomingRequest.put("Commodity_commodity", commodity);
					}
					else if(!item.getSource().equalsIgnoreCase("XML"))
					{
						incomingRequest.put("PoLine_commodity",commodity) ;
						incomingRequest.put("Commodity_commodity", commodity);
					}
					incomingRequest.put("PoLine_description",item.getDescription()) ;
					incomingRequest.put("PoLine_itemLocation",item.getLocation()) ;
					incomingRequest.put("PoLine_itemSource",item.getSource()) ;
					incomingRequest.put("PoLine_receiptRequired",item.getReceiptRequired()) ;
					incomingRequest.put("PoLine_umCode",item.getUnitOfOrder()) ;
					incomingRequest.put("PoLine_unitPrice",item.getOrderCost().toString()) ;
					incomingRequest.put("PoLine_udf1Code",item.getUdf01()) ;
					incomingRequest.put("PoLine_udf2Code",item.getUdf02()) ;
					incomingRequest.put("PoLine_udf3Code",item.getUdf03()) ;
					incomingRequest.put("PoLine_udf4Code",item.getUdf04()) ;
					incomingRequest.put("PoLine_udf5Code",item.getUdf05()) ;
                    incomingRequest.put("PoLine_udf6Code",item.getUdf06()) ;
                    incomingRequest.put("PoLine_udf7Code",item.getUdf07()) ;
                    incomingRequest.put("PoLine_udf8Code",item.getUdf08()) ;
                    incomingRequest.put("PoLine_udf9Code",item.getUdf09()) ;
                    incomingRequest.put("PoLine_udf10Code",item.getUdf10()) ;
					incomingRequest.put("PoLine_mfgName",item.getMfgName()) ;
					incomingRequest.put("PoLine_modelNumber",item.getModel()) ;
					incomingRequest.put("PoLine_umConv", item.getUmConv());
					incomingRequest.put("PoLine_umFactor", item.getUmFactor().toString());
					incomingRequest.put("PoLine_lineTotal", "0");
					incomingRequest.put("PoLine_taxable", item.getTaxable());
					if (!incomingRequest.containsKey("PoLine_quantity")) {
						incomingRequest.put("PoLine_quantity",item.getQuantity().toString()) ;
					}

					if (!HiltonUtility.isEmpty(item.getCatalogId()) && item.getCatalogId().indexOf("CHEMICAL") >= 0) {
						incomingRequest.put("PoLine_chemicalItemNumber", item.getItemNumber());
					}
				}
			//}
			//result = poLine;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			Log.error(this, e.toString());
		}
		return result;
	}
}