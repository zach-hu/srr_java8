/*
 * Created on January 18, 2007
 */
package com.tsa.puridiom.kititem.tasks;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.CatalogItemPK;
import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Kathleen
 */
public class KitItemLookupSetValues extends Task
{
	/**
	 * recieves a KitItem class and sets ups the values
	 * setValues
	 * @param object
	 * @param kitItem
	 * @return
	 */
	public  Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		CatalogItem  catalogItem = null;
		Catalog catalog = (Catalog) incomingRequest.get("catalog");
		String organizationId = (String) incomingRequest.get("organizationId");

		List catalogItemList = (List) incomingRequest.get("catalogItemList");
		if (catalogItemList != null)
		{
			if (catalogItemList.size() > 0)
			{
				catalogItem = (CatalogItem) catalogItemList.get(0);
			}
		}
		else
		{
			catalogItem = (CatalogItem) incomingRequest.get("catalogItem");
		}

		ItemLookup item = null;
		this.setStatus(Status.SUCCEEDED);
		incomingRequest.put("lookupStatus","FOUND");

		if (catalogItem != null)
		{
			CatalogItemPK catalogItemPK = catalogItem.getComp_id();
			BigDecimal orderCost = new BigDecimal(0);
			item = new ItemLookup();

			if (catalogItem.getIcText() == null)
			{
				catalogItem.setIcText(new BigDecimal(0));
			}

		    if (catalogItem.getCost() == null)
		    {
		        orderCost = new BigDecimal(0);
			}
		    else
			{
			    orderCost =catalogItem.getCost();
			}


			item.setOrderCost(orderCost);
			item.setSource("CAT") ;
			item.setItemNumber(catalogItemPK.getItemNumber()) ;
			item.setCatalogId(catalogItemPK.getCatalogId()) ;
			item.setCommodity(catalogItem.getCommodity()) ;
			item.setDescription(catalogItem.getDescription()) ;
			item.setUnitOfOrder(catalogItem.getUmCode()) ;
			item.setChargeable(item.getChargeable()) ;
			item.setIcText(catalogItem.getIcText()) ;
			item.setIcHeader(new BigDecimal(0)) ;
			item.setKit(catalogItem.getKit()) ;
			item.setOwner(catalogItem.getOwner()) ;
			item.setReceiptRequired(catalogItem.getReceiptRequired()) ;

			item.setRestricted("") ;
			item.setTaxable(catalogItem.getTaxable()) ;

			item.setUdf01(catalogItem.getUdf1Code()) ;
			item.setUdf02(catalogItem.getUdf2Code()) ;
			item.setUdf03(catalogItem.getUdf3Code()) ;
			item.setUdf04(catalogItem.getUdf4Code()) ;
			item.setUdf05(catalogItem.getUdf5Code()) ;
			item.setMfgName(catalogItem.getMfgName()) ;
			item.setModel(catalogItem.getModelNumber()) ;

			String quantityString = (String) incomingRequest.get("quantity");
			try {
				BigDecimal quantity = new BigDecimal(quantityString);
				item.setQuantity(quantity);
			}
			catch (Exception e) {
				item.setQuantity(new BigDecimal(1)) ;
			}


		}
		else
		{
			incomingRequest.put("lookupStatus","NOTFOUND");
			item = null;
		}

		return item;
	}
}
