/*
 * Created on Oct 10, 2003
 */
package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.CatalogItemPK;
import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author renzo
 */
public class CatalogItemLookupSetValues extends Task
{
	/**
	 * recieves a CatalogItem class and sets ups the values
	 * setValues
	 * @param object
	 * @param catalogItem
	 * @return
	 */
	public  Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		CatalogItem  catalogItem = null ;
		CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk) incomingRequest.get("catalogPriceBrk") ;
		Catalog catalog = (Catalog) incomingRequest.get("catalog");
        String warehouse = (String) incomingRequest.get("warehouse");
		String organizationId = (String) incomingRequest.get("organizationId") ;
		String formtype = (String)incomingRequest.get("formtype");

		List catalogItemList = (List) incomingRequest.get("catalogItemList") ;
		if (catalogItemList != null) {
			if (catalogItemList.size() > 0) {
				catalogItem = (CatalogItem) catalogItemList.get(0) ;
			}
		} else {
			catalogItem = (CatalogItem) incomingRequest.get("catalogItem") ;
		}

		ItemLookup item = null ;
		this.setStatus(Status.SUCCEEDED) ;
		incomingRequest.put("lookupStatus","FOUND") ;

		if (catalogItem != null) {
			CatalogItemPK catalogItemPK = catalogItem.getComp_id() ;
			BigDecimal orderCost = new BigDecimal(0);
			item = new ItemLookup() ;

			if (catalogItem.getIcText() == null) {
				catalogItem.setIcText(new BigDecimal(0)) ;
			}
			if (catalogPriceBrk != null && catalogPriceBrk.getUnitPrice() != null) {
			    orderCost = catalogPriceBrk.getUnitPrice();
			} else {
			    if (catalogItem.getCost() == null) {
			        orderCost = new BigDecimal(0);
				} else {
				    orderCost =catalogItem.getCost();
				}
			}

			if (catalog != null) {
			    String	currencyCode = catalog.getCurrencyCode();
			    if (!Utility.isEmpty(currencyCode)) {
			        orderCost = CurrencyManager.getInstance(organizationId).convertPriceToBaseCurrency(orderCost, currencyCode);
			    }
			    String blanketOrder = catalog.getPoNumber() ;
			    item.setBlanketOrder(blanketOrder) ;
			}

			item.setOrderCost(orderCost);
			item.setSource("CAT") ;
			item.setItemNumber(catalogItemPK.getItemNumber()) ;
			item.setCatalogId(catalogItemPK.getCatalogId()) ;
			item.setCommodity(catalogItem.getCommodity()) ;
			item.setDescription(catalogItem.getDescription()) ;
			item.setUnitOfOrder(catalogItem.getUmCode()) ;
			item.setUmConv(catalogItem.getUmConv());
			item.setUmFactor(catalogItem.getUmFactor()) ;
			item.setChargeable(item.getChargeable()) ;
			item.setIcText(catalogItem.getIcText()) ;
			item.setIcHeader(new BigDecimal(0)) ;
			item.setKit(catalogItem.getKit()) ;
			item.setOwner(catalogItem.getOwner()) ;
			item.setReceiptRequired(catalogItem.getReceiptRequired()) ;
			item.setShelfLifeRqd(catalogItem.getShelfLifeRqd());

			item.setRestricted("") ;
			item.setTaxable(catalogItem.getTaxable()) ;

			if (organizationId.equalsIgnoreCase("HOY08P") && formtype.equalsIgnoreCase("REQ")) {
				item.setUdf01("") ;
            } else {
            	item.setUdf01(catalogItem.getUdf1Code()) ;
            }

			item.setUdf02(catalogItem.getUdf2Code()) ;
			item.setUdf03(catalogItem.getUdf3Code()) ;
			item.setUdf04(catalogItem.getUdf4Code()) ;

            if (organizationId.equalsIgnoreCase("BLY07P") && !Utility.isEmpty(warehouse)) {
                item.setUdf05(warehouse) ;
            } else {
                item.setUdf05(catalogItem.getUdf5Code()) ;
            }
            if(!(organizationId.equalsIgnoreCase("BLY07P"))){
            	item.setUdf06(catalogItem.getUdf6Code()) ;
            }
            item.setUdf07(catalogItem.getUdf7Code()) ;
            item.setUdf08(catalogItem.getUdf8Code()) ;
            item.setUdf09(catalogItem.getUdf9Code()) ;

            if (organizationId.equalsIgnoreCase("BLY07P")) {
            	item.setUdf10(CommodityManager.getInstance().getCommodityType((String) incomingRequest.get("organizationId"), item.getCommodity())) ;
            } else {
            	item.setUdf10(catalogItem.getUdf10Code()) ;
            }

            item.setMfgName(catalogItem.getMfgName()) ;
			item.setModel(catalogItem.getModelNumber()) ;
			if (catalog != null) {
				item.setVendorId(catalog.getVendorId());
				item.setIcHeaderComment(catalog.getIcHeaderComment());
				item.setBlanketOrder(catalog.getPoNumber());
			}
			item.setIcLineComment(catalogItem.getIcLineComment());

			String quantityString = (String) incomingRequest.get("quantity");
			try {
				BigDecimal quantity = new BigDecimal(quantityString);
				if ((catalogItem.getMinReqQty().compareTo(new BigDecimal(0)) > 0) &&
					(quantity.compareTo(catalogItem.getMinReqQty()) < 0) &&
					(quantity.compareTo(new BigDecimal(0)) >= 0))
					quantity = catalogItem.getMinReqQty();
				item.setQuantity(quantity);
			}
			catch (Exception e) {
				if (catalogItem.getMinReqQty().compareTo(new BigDecimal(0)) > 0)
					item.setQuantity(catalogItem.getMinReqQty());
				else
					item.setQuantity(new BigDecimal(1));
			}

			item.setAsset("");
			Commodity commodity = CommodityManager.getInstance().getCommodity(organizationId, item.getCommodity());
			if (commodity != null)
			{
				item.setAsset(commodity.getAsset());
			}

			item.setIcAccount(catalogItem.getIcAccount());

			if (item.getIcAccount().compareTo(new BigDecimal(0)) <= 0)
			{
				if (catalog != null)
				{
					item.setIcAccount(catalog.getIcAccount());
				}
			}

			if (item.getIcAccount().compareTo(new BigDecimal(0)) <= 0)
			{
				BigDecimal icAccount = CommodityManager.getInstance().getCommodityIcAccount((String) incomingRequest.get("organizationId"), item.getCommodity());
				item.setIcAccount(icAccount);
			}

			if(catalogItem.getStatus().equals("03")){
				incomingRequest.put("catItemStatus", "inactive");
			} else {
				incomingRequest.put("catItemStatus", "active");
			} 
			incomingRequest.put("itemAccount_icHeader", String.valueOf(item.getIcAccount()));

		} else {
			incomingRequest.put("lookupStatus","NOTFOUND") ;
			incomingRequest.put("catItemStatus", "inactive");
			item = null ;
		}

		return item ;
	}
}
