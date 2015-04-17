package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.CatalogItemPK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class CatalogItemSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        String organizationId = (String) incomingRequest.get("organizationId");
        String dateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;

		try
		{
			CatalogItemPK comp_id = new CatalogItemPK();
			CatalogItem catalogItem = (CatalogItem) incomingRequest.get("catalogItem");
			if (catalogItem == null)
			{
				catalogItem = new CatalogItem();
			}

			if (incomingRequest.containsKey("CatalogItem_catalogId"))
			{
				String catalogId = (String ) incomingRequest.get("CatalogItem_catalogId");
				comp_id.setCatalogId(catalogId);
			}
			if (incomingRequest.containsKey("CatalogItem_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("CatalogItem_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("CatalogItem_umCode"))
			{
				String umCode = (String ) incomingRequest.get("CatalogItem_umCode");
				catalogItem.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("CatalogItem_commodity"))
			{
				String commodity = (String ) incomingRequest.get("CatalogItem_commodity");
				catalogItem.setCommodity(commodity);
			}
			if (incomingRequest.containsKey("CatalogItem_cost"))
			{
				String costString = (String) incomingRequest.get("CatalogItem_cost");
				if (Utility.isEmpty(costString))
				{
					costString = "0";
				}
				BigDecimal cost = new BigDecimal ( costString );
				catalogItem.setCost(cost);
			}
			if (incomingRequest.containsKey("CatalogItem_icText"))
			{
				String icTextString = (String) incomingRequest.get("CatalogItem_icText");
				if (Utility.isEmpty(icTextString))
				{
					icTextString = "0";
				}
				BigDecimal icText = new BigDecimal ( icTextString );
				catalogItem.setIcText(icText);
			}
			if (incomingRequest.containsKey("CatalogItem_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("CatalogItem_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString, dateFormat);
				catalogItem.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("CatalogItem_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("CatalogItem_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString, dateFormat);
				catalogItem.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("CatalogItem_status"))
			{
				String status = (String ) incomingRequest.get("CatalogItem_status");
				catalogItem.setStatus(status);
			}
			if (incomingRequest.containsKey("CatalogItem_owner"))
			{
				String owner = (String ) incomingRequest.get("CatalogItem_owner");
				catalogItem.setOwner(owner);
			}
			if (incomingRequest.containsKey("CatalogItem_source"))
			{
				String source = (String ) incomingRequest.get("CatalogItem_source");
				catalogItem.setSource(source);
			}
			if (incomingRequest.containsKey("CatalogItem_imageFile"))
			{
				String imageFile = (String ) incomingRequest.get("CatalogItem_imageFile");
				catalogItem.setImageFile(imageFile);
			}
			if (incomingRequest.containsKey("CatalogItem_asset"))
			{
				String asset = (String ) incomingRequest.get("CatalogItem_asset");
				catalogItem.setAsset(asset);
			}
			if (incomingRequest.containsKey("CatalogItem_taxable"))
			{
				String taxable = (String ) incomingRequest.get("CatalogItem_taxable");
				catalogItem.setTaxable(taxable);
			}
			if (incomingRequest.containsKey("CatalogItem_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("CatalogItem_udf1Code");
				catalogItem.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("CatalogItem_udf2Code");
				catalogItem.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("CatalogItem_udf3Code");
				catalogItem.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf4Code"))
			{
				String udf4Code = (String ) incomingRequest.get("CatalogItem_udf4Code");
				catalogItem.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf5Code"))
			{
				String udf5Code = (String ) incomingRequest.get("CatalogItem_udf5Code");
				catalogItem.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf6Code"))
			{
				String udf6Code = (String ) incomingRequest.get("CatalogItem_udf6Code");
				catalogItem.setUdf6Code(udf6Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf7Code"))
			{
				String udf7Code = (String ) incomingRequest.get("CatalogItem_udf7Code");
				catalogItem.setUdf7Code(udf7Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf8Code"))
			{
				String udf8Code = (String ) incomingRequest.get("CatalogItem_udf8Code");
				catalogItem.setUdf8Code(udf8Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf9Code"))
			{
				String udf9Code = (String ) incomingRequest.get("CatalogItem_udf9Code");
				catalogItem.setUdf9Code(udf9Code);
			}
			if (incomingRequest.containsKey("CatalogItem_udf10Code"))
			{
				String udf10Code = (String ) incomingRequest.get("CatalogItem_udf10Code");
				catalogItem.setUdf10Code(udf10Code);
			}
			if (incomingRequest.containsKey("CatalogItem_type"))
			{
				String type = (String ) incomingRequest.get("CatalogItem_type");
				catalogItem.setType(type);
			}
			if (incomingRequest.containsKey("CatalogItem_kit"))
			{
				String kit = (String ) incomingRequest.get("CatalogItem_kit");
				catalogItem.setKit(kit);
			}
			if (incomingRequest.containsKey("CatalogItem_mfgName"))
			{
				String mfgName = (String ) incomingRequest.get("CatalogItem_mfgName");
				catalogItem.setMfgName(mfgName);
			}
			if (incomingRequest.containsKey("CatalogItem_modelNumber"))
			{
				String modelNumber = (String ) incomingRequest.get("CatalogItem_modelNumber");
				catalogItem.setModelNumber(modelNumber);
			}
			if (incomingRequest.containsKey("CatalogItem_leadtime"))
			{
				String leadtimeString = (String) incomingRequest.get("CatalogItem_leadtime");
				if (Utility.isEmpty(leadtimeString))
				{
					leadtimeString = "0";
				}
				BigDecimal leadtime = new BigDecimal ( leadtimeString );
				catalogItem.setLeadtime(leadtime);
			}
			if (incomingRequest.containsKey("CatalogItem_dscQty1"))
			{
				String dscQty1String = (String) incomingRequest.get("CatalogItem_dscQty1");
				if (Utility.isEmpty(dscQty1String))
				{
					dscQty1String = "0";
				}
				BigDecimal dscQty1 = new BigDecimal ( dscQty1String );
				catalogItem.setDscQty1(dscQty1);
			}
			if (incomingRequest.containsKey("CatalogItem_dscCost1"))
			{
				String dscCost1String = (String) incomingRequest.get("CatalogItem_dscCost1");
				if (Utility.isEmpty(dscCost1String))
				{
					dscCost1String = "0";
				}
				BigDecimal dscCost1 = new BigDecimal ( dscCost1String );
				catalogItem.setDscCost1(dscCost1);
			}
			if (incomingRequest.containsKey("CatalogItem_dscQty2"))
			{
				String dscQty2String = (String) incomingRequest.get("CatalogItem_dscQty2");
				if (Utility.isEmpty(dscQty2String))
				{
					dscQty2String = "0";
				}
				BigDecimal dscQty2 = new BigDecimal ( dscQty2String );
				catalogItem.setDscQty2(dscQty2);
			}
			if (incomingRequest.containsKey("CatalogItem_dscCost2"))
			{
				String dscCost2String = (String) incomingRequest.get("CatalogItem_dscCost2");
				if (Utility.isEmpty(dscCost2String))
				{
					dscCost2String = "0";
				}
				BigDecimal dscCost2 = new BigDecimal ( dscCost2String );
				catalogItem.setDscCost2(dscCost2);
			}
			if (incomingRequest.containsKey("CatalogItem_dscQty3"))
			{
				String dscQty3String = (String) incomingRequest.get("CatalogItem_dscQty3");
				if (Utility.isEmpty(dscQty3String))
				{
					dscQty3String = "0";
				}
				BigDecimal dscQty3 = new BigDecimal ( dscQty3String );
				catalogItem.setDscQty3(dscQty3);
			}
			if (incomingRequest.containsKey("CatalogItem_dscCost3"))
			{
				String dscCost3String = (String) incomingRequest.get("CatalogItem_dscCost3");
				if (Utility.isEmpty(dscCost3String))
				{
					dscCost3String = "0";
				}
				BigDecimal dscCost3 = new BigDecimal ( dscCost3String );
				catalogItem.setDscCost3(dscCost3);
			}
			if (incomingRequest.containsKey("CatalogItem_umConv"))
			{
				String umConv = (String ) incomingRequest.get("CatalogItem_umConv");
				catalogItem.setUmConv(umConv);
			}
			if (incomingRequest.containsKey("CatalogItem_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("CatalogItem_umFactor");
				if (Utility.isEmpty(umFactorString))
				{
					umFactorString = "0";
				}
				BigDecimal umFactor = new BigDecimal ( umFactorString );
				catalogItem.setUmFactor(umFactor);
			}
			if (incomingRequest.containsKey("CatalogItem_stockEoq"))
			{
				String stockEoqString = (String) incomingRequest.get("CatalogItem_stockEoq");
				if (Utility.isEmpty(stockEoqString))
				{
					stockEoqString = "0";
				}
				BigDecimal stockEoq = new BigDecimal ( stockEoqString );
				catalogItem.setStockEoq(stockEoq);
			}
			if (incomingRequest.containsKey("CatalogItem_itemRestricted"))
			{
				String itemRestricted = (String ) incomingRequest.get("CatalogItem_itemRestricted");
				catalogItem.setItemRestricted(itemRestricted);
			}
			if (incomingRequest.containsKey("CatalogItem_minReqQty"))
			{
				String minReqQtyString = (String) incomingRequest.get("CatalogItem_minReqQty");
				if (Utility.isEmpty(minReqQtyString))
				{
					minReqQtyString = "0";
				}
				BigDecimal minReqQty = new BigDecimal ( minReqQtyString );
				catalogItem.setMinReqQty(minReqQty);
			}
			if (incomingRequest.containsKey("CatalogItem_maxReqQty"))
			{
				String maxReqQtyString = (String) incomingRequest.get("CatalogItem_maxReqQty");
				if (Utility.isEmpty(maxReqQtyString))
				{
					maxReqQtyString = "0";
				}
				BigDecimal maxReqQty = new BigDecimal ( maxReqQtyString );
				catalogItem.setMaxReqQty(maxReqQty);
			}
			if (incomingRequest.containsKey("CatalogItem_receiptRequired"))
			{
				String receiptRequired = (String ) incomingRequest.get("CatalogItem_receiptRequired");
				catalogItem.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("CatalogItem_stdCost"))
			{
				String stdCostString = (String) incomingRequest.get("CatalogItem_stdCost");
				if (Utility.isEmpty(stdCostString))
				{
					stdCostString = "0";
				}
				BigDecimal stdCost = new BigDecimal ( stdCostString );
				catalogItem.setStdCost(stdCost);
			}
			if (incomingRequest.containsKey("CatalogItem_description"))
			{
				String description = (String ) incomingRequest.get("CatalogItem_description");
				catalogItem.setDescription(description);
			}
			if (incomingRequest.containsKey("CatalogItem_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("CatalogItem_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				BigDecimal icAccount = new BigDecimal ( icAccountString );
				catalogItem.setIcAccount(icAccount);
			}

			if (incomingRequest.containsKey("CatalogItem_icLineComment"))
			{
				String icLineCommentString = (String) incomingRequest.get("CatalogItem_icLineComment");
				if (HiltonUtility.isEmpty(icLineCommentString))
				{
					icLineCommentString = "0";
				}
				catalogItem.setIcLineComment(new BigDecimal(icLineCommentString));
			}

			if (incomingRequest.containsKey("CatalogItem_shelfLifeRqd"))
			{
				String shelfLifeRqd = (String)incomingRequest.get("CatalogItem_shelfLifeRqd");
				catalogItem.setShelfLifeRqd(shelfLifeRqd);
			}

			catalogItem.setComp_id(comp_id);

			result = catalogItem;
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