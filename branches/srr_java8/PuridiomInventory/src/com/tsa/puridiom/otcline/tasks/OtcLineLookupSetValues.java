package com.tsa.puridiom.otcline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.InventoryItemLookup;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class OtcLineLookupSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String oid = (String) incomingRequest.get("organizationId");
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
		Object result = null;

		try
		{
			InventoryItemLookup item = (InventoryItemLookup) incomingRequest.get("itemLookup") ;
			if (item == null)
			{
				result = null ;
				this.setStatus(Status.FAILED) ;
			}
			else
			{
				incomingRequest.put("DisbLine_itemNumber", item.getItemNumber()) ;
				incomingRequest.put("DisbLine_commodityCode", item.getCommodity()) ;

				if (propertiesManager.getProperty("MISC","UseAverage","N").equals("Y")) {
					incomingRequest.put("DisbLine_unitPrice", item.getAvgCost().toString());
				} else {
					incomingRequest.put("DisbLine_unitPrice", item.getIssueCost().toString()) ;
				}
				incomingRequest.put("DisbLine_itemLocation", item.getLocation()) ;
				incomingRequest.put("DisbLine_umCode", item.getUnitOfOrder()) ;
				incomingRequest.put("DisbLine_umFactor", item.getUmFactor().toString());
				incomingRequest.put("DisbLine_mfgNo", item.getMfgName()) ;
				incomingRequest.put("DisbLine_umFactor", item.getUmFactor().toString());
				incomingRequest.put("DisbLine_locIc", item.getLocIc());
				incomingRequest.put("DisbLine_aisle", item.getAisle());
				incomingRequest.put("DisbLine_locrow", item.getLocrow());
				incomingRequest.put("DisbLine_tier", item.getTier());
				incomingRequest.put("DisbLine_bin", item.getBin());
				incomingRequest.put("DisbLine_udf1Code", item.getUdf01());
				incomingRequest.put("DisbLine_vendorId", item.getVendorId());
				incomingRequest.put("DisbLine_icRc", item.getIcRc().toString()) ;
				incomingRequest.put("DisbLine_description", item.getDescription()) ;
				incomingRequest.put("DisbLine_duomUmCode", item.getDuomUmCode()) ;


				if (!incomingRequest.containsKey("DisbLine_quantity")) {
					incomingRequest.put("DisbLine_quantity",item.getQuantity().toString()) ;
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