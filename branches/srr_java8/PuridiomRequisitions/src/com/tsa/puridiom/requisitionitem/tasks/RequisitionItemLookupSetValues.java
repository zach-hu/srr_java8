/*
 * Created on Dec 19, 2006
 */
package com.tsa.puridiom.requisitionitem.tasks;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Kelli
 */
public class RequisitionItemLookupSetValues extends Task {
	public  Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		RequisitionLine  requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;
		String organizationId = (String) incomingRequest.get("organizationId") ;
		ItemLookup item = null ;
		
		this.setStatus(Status.SUCCEEDED) ;
		
		incomingRequest.put("lookupStatus","FOUND") ;
		
		if (requisitionLine != null) {
			BigDecimal orderCost = new BigDecimal(0);
			item = new ItemLookup() ;
			
			item.setOrderCost(requisitionLine.getUnitPrice());
			item.setSource(requisitionLine.getItemSource()) ;
			item.setItemNumber(requisitionLine.getItemNumber()) ;
			item.setCatalogId(requisitionLine.getCatalogId()) ;
			item.setCommodity(requisitionLine.getCommodityCode()) ;
			item.setDescription(requisitionLine.getDescription()) ;
			item.setUnitOfOrder(requisitionLine.getUmCode()) ;
			item.setIcText(new BigDecimal(0)) ;
			item.setIcHeader(new BigDecimal(0)) ;
			item.setKit("N") ;
			item.setOwner("") ;
			item.setReceiptRequired(requisitionLine.getReceiptRequired()) ;

			item.setRestricted("") ;
			item.setTaxable(requisitionLine.getTaxable()) ;

			item.setUdf01(requisitionLine.getUdf1Code()) ;
			item.setUdf02(requisitionLine.getUdf2Code()) ;
			item.setUdf03(requisitionLine.getUdf3Code()) ;
			item.setUdf04(requisitionLine.getUdf4Code()) ;
			item.setUdf05(requisitionLine.getUdf5Code()) ;
			item.setMfgName(requisitionLine.getMfgName()) ;
			item.setModel(requisitionLine.getModelNumber()) ;
			
			String quantityString = (String) incomingRequest.get("quantity");
			try {
				BigDecimal quantity = new BigDecimal(quantityString);
				item.setQuantity(quantity);
			}
			catch (Exception e) {
				item.setQuantity(new BigDecimal(1)) ;
			}
			item.setAsset("");
			
			item.setIcAccount(requisitionLine.getIcAccount());
			if (item.getIcAccount().compareTo(new BigDecimal(0)) <= 0)
			{
				BigDecimal icAccount = CommodityManager.getInstance().getCommodityIcAccount((String) incomingRequest.get("organizationId"), item.getCommodity());
				item.setIcAccount(icAccount);
			}
			incomingRequest.put("itemAccount_icHeader", String.valueOf(item.getIcAccount()));
			
			incomingRequest.remove("requisitionLine");
		} else {
			incomingRequest.put("lookupStatus","NOTFOUND") ;
			item = null ;
		}
		
		return item ;
	}
}
