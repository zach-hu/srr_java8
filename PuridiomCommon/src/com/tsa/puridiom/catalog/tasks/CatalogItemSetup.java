package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import java.util.Map;
import com.tsagate.foundation.processengine.*;

public class CatalogItemSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
		    Catalog catalog = (Catalog) incomingRequest.get("catalog");

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

		    if (!incomingRequest.containsKey("CatalogItem_catalogId"))	{
		        String catalogId = "";
		        if (catalog != null) {
		            incomingRequest.put("CatalogItem_catalogId", catalog.getCatalogId());
		        }
		        if (Utility.isEmpty(catalogId) && incomingRequest.containsKey("Catalog_catalogId")) {
		            catalogId = Utility.ckNull((String) incomingRequest.get("Catalog_catalogId"));
		        }
		        incomingRequest.put("CatalogItem_catalogId", catalogId);
		    }
		    if (!incomingRequest.containsKey("CatalogItem_itemNumber"))	{
		        incomingRequest.put("CatalogItem_itemNumber", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_umCode"))	{
		        incomingRequest.put("CatalogItem_umCode", "EA");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_commodity"))	{
		        incomingRequest.put("CatalogItem_commodity", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_cost"))	{
		        incomingRequest.put("CatalogItem_cost", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_icText"))	{
		        incomingRequest.put("CatalogItem_icText", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dateEntered"))	{
		        incomingRequest.put("CatalogItem_dateEntered", Dates.today(userDateFormat, userTimeZone));
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dateExpires"))	{
		        incomingRequest.put("CatalogItem_dateExpires", Dates.today(userDateFormat, userTimeZone));
		    }
		    if (!incomingRequest.containsKey("CatalogItem_status"))	{
		        incomingRequest.put("CatalogItem_status", "02");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_owner"))	{
		        incomingRequest.put("CatalogItem_owner", userId);
		    }
		    if (!incomingRequest.containsKey("CatalogItem_source"))	{
		        incomingRequest.put("CatalogItem_source", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_imageFile"))	{
		        incomingRequest.put("CatalogItem_imageFile", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_asset"))	{
		        incomingRequest.put("CatalogItem_asset", "N");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_taxable"))	{
		        incomingRequest.put("CatalogItem_taxable", "N");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_udf1Code"))	{
		        incomingRequest.put("CatalogItem_udf1Code", propertiesManager.getProperty("CATALOG ITEM DEFAULTS","Udf1Code",""));
		    }
		    if (!incomingRequest.containsKey("CatalogItem_udf2Code"))	{
		        incomingRequest.put("CatalogItem_udf2Code", propertiesManager.getProperty("CATALOG ITEM DEFAULTS","Udf2Code",""));
		    }
		    if (!incomingRequest.containsKey("CatalogItem_udf3Code"))	{
		        incomingRequest.put("CatalogItem_udf3Code", propertiesManager.getProperty("CATALOG ITEM DEFAULTS","Udf3Code",""));
		    }
		    if (!incomingRequest.containsKey("CatalogItem_udf4Code"))	{
		        incomingRequest.put("CatalogItem_udf4Code", propertiesManager.getProperty("CATALOG ITEM DEFAULTS","Udf4Code",""));
		    }
		    if (!incomingRequest.containsKey("CatalogItem_udf5Code"))	{
		        incomingRequest.put("CatalogItem_udf5Code", propertiesManager.getProperty("CATALOG ITEM DEFAULTS","Udf5Code",""));
		    }
		    if (!incomingRequest.containsKey("CatalogItem_type"))	{
   		        incomingRequest.put("CatalogItem_type", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_kit"))	{
		        incomingRequest.put("CatalogItem_kit", "N");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_mfgName"))	{
		        incomingRequest.put("CatalogItem_mfgName", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_modelNumber"))	{
		        incomingRequest.put("CatalogItem_modelNumber", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_leadtime"))	{
		        incomingRequest.put("CatalogItem_leadtime", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dscQty1"))	{
		        incomingRequest.put("CatalogItem_dscQty1", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dscCost1"))	{
		        incomingRequest.put("CatalogItem_dscCost1", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dscQty2"))	{
		        incomingRequest.put("CatalogItem_dscQty2", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dscCost2"))	{
		        incomingRequest.put("CatalogItem_dscCost2", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dscQty3"))	{
		        incomingRequest.put("CatalogItem_dscQty3", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_dscCost3"))	{
		        incomingRequest.put("CatalogItem_dscCost3", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_umConv"))	{
		        incomingRequest.put("CatalogItem_umConv", "1");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_umFactor"))	{
		        incomingRequest.put("CatalogItem_umFactor", "1");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_stockEoq"))	{
		        incomingRequest.put("CatalogItem_stockEoq", "1");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_itemRestricted"))	{
		        incomingRequest.put("CatalogItem_itemRestricted", "N");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_minReqQty"))	{
		        incomingRequest.put("CatalogItem_minReqQty", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_maxReqQty"))	{
		        incomingRequest.put("CatalogItem_maxReqQty", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_receiptRequired"))	{
		        incomingRequest.put("CatalogItem_receiptRequired", "R");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_stdCost"))	{
		        incomingRequest.put("CatalogItem_stdCost", "0");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_description"))	{
		        incomingRequest.put("CatalogItem_description", "");
		    }
		    if (!incomingRequest.containsKey("CatalogItem_shelfLifeRqd")) {
		    	incomingRequest.put("CatalogItem_shelfLifeRqd", "N");
		    }

		    incomingRequest.put("newItem", "true");

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