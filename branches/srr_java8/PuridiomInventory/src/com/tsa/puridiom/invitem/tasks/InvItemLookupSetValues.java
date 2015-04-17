/*
 * Created on Oct 10, 2003
 */
package com.tsa.puridiom.invitem.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.InvVendor;
import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsa.puridiom.unitofmeasure.tasks.UnitOfMeasureRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 */
public class InvItemLookupSetValues extends Task
{
	/**
	 * recieves a InvItem class and sets ups the values
	 * setValues
	 * @param object
	 * @param catalogItem
	 * @return
	 */
	public  Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		InvItem  invItem = null ;
		InvVendor  invVendor = null ;
		InvLocation  invLocation = null ;

		String reqType = (String) incomingRequest.get("RequisitionHeader_requisitionType");
		List invItemList = (List) incomingRequest.get("invItemList") ;
		if (invItemList != null) {
			if (invItemList.size() > 0) {
				invItem = (InvItem) invItemList.get(0) ;
			}
		} else {
			invItem = (InvItem) incomingRequest.get("invItem") ;
		}

		List invVendorList = (List) incomingRequest.get("invVendor");
		if (invVendorList != null) {
			if (invVendorList.size() > 0) {
				invVendor = (InvVendor) invVendorList.get(0) ;
			}
		}

		List invLocationList = (List) incomingRequest.get("invLocationList") ;
		if (invLocationList != null) {
			if (invLocationList.size() > 0) {
				invLocation = (InvLocation) invLocationList.get(0) ;
			}
		} else {
			invLocation = (InvLocation) incomingRequest.get("invLocation") ;
		}
		ItemLookup item = null ;
		this.setStatus(Status.SUCCEEDED) ;
		incomingRequest.put("lookupStatus","FOUND") ;

		if (invItem != null) {
			item = new ItemLookup() ;

			if (invItem.getIcText() == null) {
				invItem.setIcText(new BigDecimal(0)) ;
			}
			if (invItem.getCost() == null) {
				invItem.setCost(new BigDecimal(0)) ;
			}
			item.setCatalogId("INVENTORY");
			item.setSource("INV") ;
			item.setItemNumber(invItem.getItemNumber()) ;
			item.setLocation(invLocation.getComp_id().getItemLocation());
			item.setCommodity(invItem.getCommodity()) ;
			item.setDescription(invItem.getDescription()) ;
			//inventory items always use unit of issue
			item.setUnitOfOrder(invItem.getUnitOfOrder()) ;
			item.setUnitOfIssue(invItem.getUnitOfIssue()) ;
			item.setOrderCost(invItem.getCost()) ;
			item.setAvgCost(invItem.getAverageCost()) ;
			item.setIssueCost(invItem.getIssueCost()) ;
			item.setChargeable(invItem.getChargable()) ;
			item.setIcText(invItem.getIcText()) ;
			item.setIcHeader(new BigDecimal(0)) ;
			item.setKit(invItem.getKit()) ;
			item.setOwner(invItem.getOwner()) ;
			item.setReceiptRequired(invItem.getReceiptRequired()) ;
			item.setUmFactor(this.getUmFactor(incomingRequest, invItem.getUnitOfOrder()));
			item.setUnitOfIssueFactor(this.getUmFactor(incomingRequest, invItem.getUnitOfIssue()));
			item.setRestricted(invItem.getRestrictedItem()) ;
			item.setTaxable(invItem.getTaxable()) ;
			item.setDuomUmCode(invItem.getDuomUmCode()) ;

			item.setUdf01(invItem.getUdf1Code()) ;
			item.setUdf02(invItem.getUdf2Code()) ;
			item.setUdf03(invItem.getUdf3Code()) ;
			item.setUdf04(invItem.getUdf4Code()) ;
			item.setUdf05(invItem.getUdf5Code()) ;
			item.setUdf06(invItem.getUdf6Code()) ;
			item.setUdf07(invItem.getUdf7Code()) ;
			item.setUdf08(invItem.getUdf8Code()) ;
			item.setUdf09(invItem.getUdf9Code()) ;
			item.setUdf10(invItem.getUdf10Code());
			item.setShelfLifeRqd(invItem.getShelfLifeRqd());
			if(reqType != null && reqType.equalsIgnoreCase("M") && invVendor != null)
			{
				item.setVendorId(invVendor.getComp_id().getVendorId());
			}
			if(reqType.equals("M") && item.getSource().equals("INV"))
			{
				item.setMfgName(invItem.getMfgName()) ;
				item.setModel(invItem.getModelNumber()) ;
			}
			else{
				item.setMfgName("") ;
				item.setModel("") ;
			}
			
			String quantityString = (String) incomingRequest.get("quantity");
			try {
				BigDecimal quantity = new BigDecimal(quantityString);
				item.setQuantity(quantity);
			}
			catch (Exception e) {
				item.setQuantity(new BigDecimal(1)) ;
			}
			item.setAsset(invItem.getAssetCode());
			item.setActionCode(invItem.getActionCode());

			item.setIcAccount(invLocation.getIcInvAccount());
			if (item.getIcAccount().compareTo(new BigDecimal(0)) <= 0)
			{
				BigDecimal icAccount = CommodityManager.getInstance().getCommodityIcAccount((String) incomingRequest.get("organizationId"), item.getCommodity());
				item.setIcAccount(icAccount);
			}
			incomingRequest.put("itemAccount_icHeader", String.valueOf(item.getIcAccount()));
			if(invItem.getStatus().equals("03")){
				incomingRequest.put("invItemStatus", "inactive");
			}else{
				incomingRequest.put("invItemStatus", "active");
			}
		} else {
			incomingRequest.put("lookupStatus","NOTFOUND") ;
			incomingRequest.put("invItemStatus", "inactive");
			item = null ;
		}

		return item ;
	}


	private BigDecimal getUmFactor(Map incomingRequest, String uomCode) {
	    BigDecimal	uomFactor  = new BigDecimal(1);

		try {
            String	userId = (String) incomingRequest.get("userId");
            String	organizationId = (String) incomingRequest.get("organizationId");
            DBSession	dbsession = (DBSession) incomingRequest.get("dbsession");

            Map requestParams = new HashMap();
            requestParams.put("userId", userId);
						   requestParams.put("userTimeZone", incomingRequest.get("userTimeZone"));
            requestParams.put("organizationId", organizationId);
            requestParams.put("dbsession", dbsession);
            requestParams.put("UnitOfMeasure_umCode", uomCode);

            UnitOfMeasureRetrieveById uomRetrieve = new UnitOfMeasureRetrieveById();
            UnitOfMeasure unitOfMeasure = (UnitOfMeasure) uomRetrieve.executeTask(requestParams);

            if (unitOfMeasure != null) {
            	uomFactor = unitOfMeasure.getFactor();
            }
		} catch (Exception e) {
		    Log.error(this, "An error occured trying to get the UnitOfMeasure factor.");
		    uomFactor  = new BigDecimal(1);
		}

	    return uomFactor;
	}
}
