/*
 * Created on Oct 10, 2003
 */
package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.common.documents.InventoryItemLookup;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author renzo
 */
public class OtcItemLookupSetValues extends Task
{
	/**
	 * recieves a InventoryItem class and sets ups the values
	 * setValues
	 * @param object
	 * @return
	 */
	public  Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		InvItem  invItem = null ;
		InvLocation  invLocation = null ;
		InvBinLocation bin = null;

		List invItemList = (List) incomingRequest.get("invItemList") ;
		if (invItemList != null)
		{
			if (invItemList.size() > 0)
			{
				invItem = (InvItem) invItemList.get(0) ;
			}
		}
		else
		{
			invItem = (InvItem) incomingRequest.get("invItem") ;
		}

		List invLocationList = (List) incomingRequest.get("invLocationList") ;
		if (invLocationList != null)
		{
			if (invLocationList.size() > 0)
			{
				invLocation = (InvLocation) invLocationList.get(0) ;
			}
		}
		else
		{
			invLocation = (InvLocation) incomingRequest.get("invLocation") ;
		}

		bin = (InvBinLocation)incomingRequest.get("invBinLocation");

		InventoryItemLookup item = null ;
		this.setStatus(Status.SUCCEEDED) ;
		incomingRequest.put("lookupStatus","FOUND") ;

		if (invItem != null) {
			item = new InventoryItemLookup() ;

			if (invItem.getIcText() == null) {
				invItem.setIcText(new BigDecimal(0)) ;
			}
			if (invItem.getCost() == null) {
				invItem.setCost(new BigDecimal(0)) ;
			}
			item.setSource("INV") ;
			item.setItemNumber(invItem.getItemNumber()) ;
			item.setLocation(invLocation.getComp_id().getItemLocation());
			item.setCommodity(invItem.getCommodity()) ;
			item.setDescription(invItem.getDescription()) ;
			item.setUnitOfOrder(invItem.getUnitOfOrder()) ;
			item.setUnitOfIssue(invItem.getUnitOfIssue()) ;
			item.setDuomUmCode(invItem.getDuomUmCode()) ;
			item.setOrderCost(invItem.getCost()) ;
			item.setAvgCost(invItem.getAverageCost()) ;
			item.setIssueCost(invItem.getIssueCost()) ;
			item.setChargeable(item.getChargeable()) ;
			item.setIcText(invItem.getIcText()) ;
			item.setIcAccount(invLocation.getIcInvAccount());
			incomingRequest.put("Account_icHeader", String.valueOf(invLocation.getIcInvAccount()));
			item.setIcHeader(new BigDecimal(0)) ;
			item.setKit(invItem.getKit()) ;
			item.setOwner(invItem.getOwner()) ;
			item.setReceiptRequired(invItem.getReceiptRequired()) ;
			if (invItem.getFactor().compareTo(new BigDecimal(0)) <= 0) {
				item.setUmFactor(new BigDecimal(1));
			} else {
				item.setUmFactor(invItem.getFactor());
			}

			if (bin == null) {
				bin = new InvBinLocation() ;
				bin.setQtyAlloc(invLocation.getQtyAlloc()) ;
				bin.setQtyOnHand(invLocation.getQtyOnHand()) ;
			}

			item.setAisle(bin.getAisle());
			item.setLocrow(bin.getLocrow());
			item.setTier(bin.getTier());
			item.setBin(bin.getBin());
			item.setUdf01(bin.getUdf1Code());
			item.setVendorId(bin.getVendorId());
			item.setQtyAvail(bin.getQtyOnHand().subtract(bin.getQtyAlloc()));
			item.setIcRc(bin.getIcRc());

			item.setMfgName("") ;
			item.setModel("") ;
			String quantityString = (String) incomingRequest.get("quantity");
			try
			{
				BigDecimal quantity = new BigDecimal(quantityString);
				item.setQuantity(quantity);
			}
			catch (Exception e)
			{
				item.setQuantity(new BigDecimal(1)) ;
			}
			item.setAsset(invItem.getAssetCode());

		}
		else
		{
			incomingRequest.put("lookupStatus","NOTFOUND") ;
			item = null ;
		}

		return item ;
	}
}
