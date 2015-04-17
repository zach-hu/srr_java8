package com.tsa.puridiom.disbline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DisbLineSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DisbLine disbLine = (DisbLine) incomingRequest.get("disbLine");
			if (disbLine == null)
			{
				disbLine = new DisbLine();
			}

			if (incomingRequest.containsKey("DisbLine_icDsbLine"))
			{
				String icDsbLineString = (String) incomingRequest.get("DisbLine_icDsbLine");
				if (Utility.isEmpty(icDsbLineString))
				{
					icDsbLineString = "0";
				}
				BigDecimal icDsbLine = new BigDecimal ( icDsbLineString );
				disbLine.setIcDsbLine(icDsbLine);
			}
			if (incomingRequest.containsKey("DisbLine_lineNumber"))
			{
				String lineNumberString = (String) incomingRequest.get("DisbLine_lineNumber");
				if (Utility.isEmpty(lineNumberString))
				{
					lineNumberString = "0";
				}
				BigDecimal lineNumber = new BigDecimal ( lineNumberString );
				disbLine.setLineNumber(lineNumber);
			}
			if (incomingRequest.containsKey("DisbLine_disbNumber"))
			{
				String disbNumber = (String ) incomingRequest.get("DisbLine_disbNumber");
				disbLine.setDisbNumber(disbNumber);
			}
			if (incomingRequest.containsKey("DisbLine_icDsbHeader"))
			{
				String icDsbHeaderString = (String) incomingRequest.get("DisbLine_icDsbHeader");
				if (Utility.isEmpty(icDsbHeaderString))
				{
					icDsbHeaderString = "0";
				}
				BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
				disbLine.setIcDsbHeader(icDsbHeader);
			}
			if (incomingRequest.containsKey("DisbLine_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("DisbLine_itemNumber");
				disbLine.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("DisbLine_itemSource"))
			{
				String itemSource = (String ) incomingRequest.get("DisbLine_itemSource");
				disbLine.setItemSource(itemSource);
			}
			if (incomingRequest.containsKey("DisbLine_umCode"))
			{
				String umCode = (String ) incomingRequest.get("DisbLine_umCode");
				disbLine.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("DisbLine_quantity"))
			{
				String quantityString = (String) incomingRequest.get("DisbLine_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				disbLine.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("DisbLine_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("DisbLine_unitPrice");
				if (Utility.isEmpty(unitPriceString))
				{
					unitPriceString = "0";
				}
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				disbLine.setUnitPrice(unitPrice);
			}
			if (incomingRequest.containsKey("DisbLine_commodityCode"))
			{
				String commodityCode = (String ) incomingRequest.get("DisbLine_commodityCode");
				disbLine.setCommodityCode(commodityCode);
			}
			if (incomingRequest.containsKey("DisbLine_icReqLine"))
			{
				String icReqLineString = (String) incomingRequest.get("DisbLine_icReqLine");
				if (Utility.isEmpty(icReqLineString))
				{
					icReqLineString = "0";
				}
				BigDecimal icReqLine = new BigDecimal ( icReqLineString );
				disbLine.setIcReqLine(icReqLine);
			}
			if (incomingRequest.containsKey("DisbLine_status"))
			{
				String status = (String ) incomingRequest.get("DisbLine_status");
				disbLine.setStatus(status);
			}
			if (incomingRequest.containsKey("DisbLine_commentFlag"))
			{
				String commentFlag = (String ) incomingRequest.get("DisbLine_commentFlag");
				disbLine.setCommentFlag(commentFlag);
			}
			if (incomingRequest.containsKey("DisbLine_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("DisbLine_itemLocation");
				disbLine.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("DisbLine_icDsbAccount"))
			{
				String icDsbAccountString = (String) incomingRequest.get("DisbLine_icDsbAccount");
				if (Utility.isEmpty(icDsbAccountString))
				{
					icDsbAccountString = "0";
				}
				BigDecimal icDsbAccount = new BigDecimal ( icDsbAccountString );
				disbLine.setIcDsbAccount(icDsbAccount);
			}
			if (incomingRequest.containsKey("DisbLine_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("DisbLine_umFactor");
				if (Utility.isEmpty(umFactorString))
				{
					umFactorString = "0";
				}
				BigDecimal umFactor = new BigDecimal ( umFactorString );
				disbLine.setUmFactor(umFactor);
			}
			if (incomingRequest.containsKey("DisbLine_lineTotal"))
			{
				String lineTotalString = (String) incomingRequest.get("DisbLine_lineTotal");
				if (Utility.isEmpty(lineTotalString))
				{
					lineTotalString = "0";
				}
				BigDecimal lineTotal = new BigDecimal ( lineTotalString );
				disbLine.setLineTotal(lineTotal);
			}
			if (incomingRequest.containsKey("DisbLine_shiptoFlag"))
			{
				String shiptoFlag = (String ) incomingRequest.get("DisbLine_shiptoFlag");
				disbLine.setShiptoFlag(shiptoFlag);
			}
			if (incomingRequest.containsKey("DisbLine_aisle"))
			{
				String aisle = (String ) incomingRequest.get("DisbLine_aisle");
				disbLine.setAisle(aisle);
			}
			if (incomingRequest.containsKey("DisbLine_locrow"))
			{
				String locrow = (String ) incomingRequest.get("DisbLine_locrow");
				disbLine.setLocrow(locrow);
			}
			if (incomingRequest.containsKey("DisbLine_tier"))
			{
				String tier = (String ) incomingRequest.get("DisbLine_tier");
				disbLine.setTier(tier);
			}
			if (incomingRequest.containsKey("DisbLine_bin"))
			{
				String bin = (String ) incomingRequest.get("DisbLine_bin");
				disbLine.setBin(bin);
			}
			if (incomingRequest.containsKey("DisbLine_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("DisbLine_vendorId");
				disbLine.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("DisbLine_manufactNo"))
			{
				String manufactNo = (String ) incomingRequest.get("DisbLine_manufactNo");
				disbLine.setManufactNo(manufactNo);
			}
			if (incomingRequest.containsKey("DisbLine_lot"))
			{
				String lot = (String ) incomingRequest.get("DisbLine_lot");
				disbLine.setLot(lot);
			}
			if (incomingRequest.containsKey("DisbLine_locIc"))
			{
				String locIc = (String ) incomingRequest.get("DisbLine_locIc");
				disbLine.setLocIc(locIc);
			}
			if (incomingRequest.containsKey("DisbLine_qtyBkord"))
			{
				String qtyBkordString = (String) incomingRequest.get("DisbLine_qtyBkord");
				if (Utility.isEmpty(qtyBkordString))
				{
					qtyBkordString = "0";
				}
				BigDecimal qtyBkord = new BigDecimal ( qtyBkordString );
				disbLine.setQtyBkord(qtyBkord);
			}
			if (incomingRequest.containsKey("DisbLine_disbDate"))
			{
				String disbDateString = (String) incomingRequest.get("DisbLine_disbDate");
				Date disbDate = Dates.getDate(disbDateString);
				disbLine.setDisbDate(disbDate);
			}
			if (incomingRequest.containsKey("DisbLine_assetCode"))
			{
				String assetCode = (String ) incomingRequest.get("DisbLine_assetCode");
				disbLine.setAssetCode(assetCode);
			}
			if (incomingRequest.containsKey("DisbLine_icLineHistory"))
			{
				String icLineHistoryString = (String) incomingRequest.get("DisbLine_icLineHistory");
				if (Utility.isEmpty(icLineHistoryString))
				{
					icLineHistoryString = "0";
				}
				BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString );
				disbLine.setIcLineHistory(icLineHistory);
			}
			if (incomingRequest.containsKey("DisbLine_description"))
			{
				String description = (String ) incomingRequest.get("DisbLine_description");
				disbLine.setDescription(description);
			}
			if (incomingRequest.containsKey("DisbLine_icRc"))
			{
				String icRcString = (String) incomingRequest.get("DisbLine_icRc");
				if (Utility.isEmpty(icRcString))
				{
					icRcString = "0";
				}
				BigDecimal icRc = new BigDecimal ( icRcString );
				disbLine.setIcRc(icRc);
			}
			if (incomingRequest.containsKey("DisbLine_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("DisbLine_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				BigDecimal icAccount = new BigDecimal ( icAccountString );
				disbLine.setIcAccount(icAccount);
			}

			if (incomingRequest.containsKey("DisbLine_duomUmCode"))
			{
				String duomUmCode = (String ) incomingRequest.get("DisbLine_duomUmCode");
				disbLine.setDuomUmCode(duomUmCode);
			}
			if (incomingRequest.containsKey("DisbLine_duomQuantity"))
			{
				String quantityString = (String) incomingRequest.get("DisbLine_duomQuantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				disbLine.setDuomQuantity(quantity);
			}
			if (incomingRequest.containsKey("DisbLine_udf1Code"))
			{
				String udf1Code = (String)incomingRequest.get("DisbLine_udf1Code");
				disbLine.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("DisbLine_udf2Code"))
			{
				String udf2Code = (String)incomingRequest.get("DisbLine_udf2Code");
				disbLine.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("DisbLine_udf3Code"))
			{
				String udf3Code = (String)incomingRequest.get("DisbLine_udf3Code");
				disbLine.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("DisbLine_udf4Code"))
			{
				String udf4Code = (String)incomingRequest.get("DisbLine_udf4Code");
				disbLine.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("DisbLine_udf5Code"))
			{
				String udf5Code = (String)incomingRequest.get("DisbLine_udf5Code");
				disbLine.setUdf5Code(udf5Code);
			}

			result = disbLine;
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