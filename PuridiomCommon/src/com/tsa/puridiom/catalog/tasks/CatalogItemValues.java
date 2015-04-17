/*
 * Created on Oct 10, 2003
 */
package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.CatalogItemPK;
import com.tsagate.foundation.utility.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
/**
 * @author renzo
 */
public class CatalogItemValues
{
	/**
	 * recieves a CatalogItem class and sets ups the values
	 * setValues
	 * @param object
	 * @param catalogItem
	 * @return
	 */
	public static CatalogItem setValues(Object object, CatalogItem catalogItem)
	{
		Map incomingRequest = (Map) object;
		String itemNumber = (String)incomingRequest.get("itemNumber");
		String catalogId = (String)incomingRequest.get("catalogId") ;
		String umcode = (String) incomingRequest.get("umcode") ;
		String commodity = (String) incomingRequest.get("commodity") ;
		String attention = (String) incomingRequest.get("attention") ;
		String columnName = (String) incomingRequest.get("columnName");
		//BigDecimal cost = (BigDecimal) incomingRequest.get("cost");
		if (incomingRequest.containsKey("cost"))
			{
				String costString = (String) incomingRequest.get("cost");
				if (Utility.isEmpty(costString))
				{
					costString = "0";
				}
				BigDecimal cost = new BigDecimal ( costString );
				catalogItem.setCost(cost);
			}
		BigDecimal icText = (BigDecimal) incomingRequest.get("icText");
		Date dateEntered = (Date) incomingRequest.get("dateEntered");
		Date dateExpires = (Date) incomingRequest.get("dateExpires");
		String status = (String) incomingRequest.get("status");
		String owner = (String) incomingRequest.get("owner");
		String source = (String) incomingRequest.get("source");
		String imageFile = (String) incomingRequest.get("image_file");
		String taxable = (String) incomingRequest.get("taxable");
		String udf1Code = (String) incomingRequest.get("udf1Code");
		String udf2Code = (String) incomingRequest.get("udf2Code");
		String udf3Code = (String) incomingRequest.get("udf3Code");
		String udf4Code = (String) incomingRequest.get("udf4Code");
		String udf5Code = (String) incomingRequest.get("udf5Code");
		String type = (String) incomingRequest.get("type");
		String kit = (String) incomingRequest.get("kit");
		String mfgName = (String) incomingRequest.get("mfgName");
		String modelNumber = (String) incomingRequest.get("modelNumber");
		BigDecimal leadtime = (BigDecimal) incomingRequest.get("leadtime");
		BigDecimal dscQty1 = (BigDecimal) incomingRequest.get("dscQty1");
		BigDecimal dscCost1 = (BigDecimal) incomingRequest.get("dscCost1");
		BigDecimal dscQty2 = (BigDecimal) incomingRequest.get("dscQty2");
		BigDecimal dscCost2 = (BigDecimal) incomingRequest.get("dscCost2");
		BigDecimal dscQty3 = (BigDecimal) incomingRequest.get("dscQty3");
		BigDecimal dscCost3 = (BigDecimal) incomingRequest.get("dscCost3");
		String umConv = (String) incomingRequest.get("umConv");
		//BigDecimal umFactor = (BigDecimal) incomingRequest.get("umFactor");
		if (incomingRequest.containsKey("umFactor"))
		{
			String umFactorString = (String) incomingRequest.get("umFactor");
			if (Utility.isEmpty(umFactorString))
			{
				umFactorString = "0";
			}
			BigDecimal umFactor = new BigDecimal ( umFactorString );
			catalogItem.setUmFactor(umFactor);
		}
		//BigDecimal stockEoq = (BigDecimal) incomingRequest.get("stockEoq");
		if (incomingRequest.containsKey("stockEoq"))
		{
			String stockEoqString = (String) incomingRequest.get("umFactor");
			if (Utility.isEmpty(stockEoqString))
			{
				stockEoqString = "0";
			}
			BigDecimal stockEoq = new BigDecimal ( stockEoqString );
			catalogItem.setStockEoq(stockEoq) ;
		}
		String itemRestricted = (String) incomingRequest.get("itemRestricted");
		BigDecimal minReqQty = (BigDecimal) incomingRequest.get("minReqQty");
		BigDecimal maxReqQty = (BigDecimal) incomingRequest.get("maxReqQty");
		String receiptRequired = (String) incomingRequest.get("receiptRequired");
		BigDecimal stdCost = (BigDecimal) incomingRequest.get("stdCost");
		String description = (String) incomingRequest.get("description");
		String shelfLifeRqd = (String) incomingRequest.get("shelfLifeRqd");


		CatalogItemPK catalogItemPK = new CatalogItemPK();
		catalogItemPK.setCatalogId(catalogId);
		catalogItemPK.setItemNumber(itemNumber);

		catalogItem.setComp_id(catalogItemPK);
		catalogItem.setUmCode(umcode);
		catalogItem.setCommodity(commodity);

		catalogItem.setIcText(icText);
		catalogItem.setDateEntered(dateEntered) ;
		catalogItem.setDateExpires(dateExpires) ;
		catalogItem.setStatus(status) ;
		catalogItem.setOwner(owner) ;
		catalogItem.setSource(source) ;
		catalogItem.setImageFile(imageFile) ;
		catalogItem.setTaxable(taxable) ;
		catalogItem.setUdf1Code(udf1Code) ;
		catalogItem.setUdf2Code(udf2Code) ;
		catalogItem.setUdf3Code(udf3Code) ;
		catalogItem.setUdf4Code(udf4Code) ;
		catalogItem.setUdf5Code(udf5Code) ;
		catalogItem.setType(type) ;
		catalogItem.setKit(kit) ;
		catalogItem.setMfgName(mfgName) ;
		catalogItem.setModelNumber(modelNumber) ;
		catalogItem.setLeadtime(leadtime) ;
		catalogItem.setDscQty1(dscQty1) ;
		catalogItem.setDscCost1(dscCost1) ;
		catalogItem.setDscQty2(dscQty2) ;
		catalogItem.setDscCost2(dscCost2) ;
		catalogItem.setDscQty3(dscQty3) ;
		catalogItem.setUmConv(umConv) ;


		catalogItem.setItemRestricted(itemRestricted) ;
		catalogItem.setMaxReqQty(maxReqQty) ;
		catalogItem.setMinReqQty(minReqQty) ;
		catalogItem.setReceiptRequired(receiptRequired) ;
		catalogItem.setStdCost(stdCost) ;
		catalogItem.setDescription(description) ;
		catalogItem.setShelfLifeRqd(shelfLifeRqd);

		return catalogItem;
	}
}
