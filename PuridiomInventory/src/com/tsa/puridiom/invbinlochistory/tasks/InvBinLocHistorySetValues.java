package com.tsa.puridiom.invbinlochistory.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvBinLocHistorySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvBinLocHistory invBinLocHistory = (InvBinLocHistory) incomingRequest.get("invBinLocHistory");
			if (invBinLocHistory == null)
			{
				invBinLocHistory = new InvBinLocHistory();
			}

			if (incomingRequest.containsKey("InvBinLocHistory_itemNumber"))
			{
				String itemNumber = (String) incomingRequest.get("InvBinLocHistory_itemNumber");
				invBinLocHistory.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_itemLocation"))
			{
				String itemLocation = (String) incomingRequest.get("InvBinLocHistory_itemLocation");
				invBinLocHistory.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("InvBinLocHistory_vendorId");
				invBinLocHistory.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_manufactNo"))
			{
				String manufactNo = (String) incomingRequest.get("InvBinLocHistory_manufactNo");
				invBinLocHistory.setManufactNo(manufactNo);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_lot"))
			{
				String lot = (String) incomingRequest.get("InvBinLocHistory_lot");
				invBinLocHistory.setLot(lot);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_cost"))
			{
				String costString = (String) incomingRequest.get("InvBinLocHistory_cost");
				if (Utility.isEmpty(costString))
				{
					costString = "0";
				}
				BigDecimal cost = new BigDecimal ( costString );
				invBinLocHistory.setCost(cost);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_itemDate"))
			{
				String itemDateString = (String) incomingRequest.get("InvBinLocHistory_itemDate");
				Date itemDate = Dates.getDate(itemDateString);
				invBinLocHistory.setItemDate(itemDate);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_aisle"))
			{
				String aisle = (String) incomingRequest.get("InvBinLocHistory_aisle");
				invBinLocHistory.setAisle(aisle);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_locrow"))
			{
				String locrow = (String) incomingRequest.get("InvBinLocHistory_locrow");
				invBinLocHistory.setLocrow(locrow);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_tier"))
			{
				String tier = (String) incomingRequest.get("InvBinLocHistory_tier");
				invBinLocHistory.setTier(tier);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_bin"))
			{
				String bin = (String) incomingRequest.get("InvBinLocHistory_bin");
				invBinLocHistory.setBin(bin);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("InvBinLocHistory_udf1Code");
				invBinLocHistory.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("InvBinLocHistory_udf2Code");
				invBinLocHistory.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("InvBinLocHistory_udf3Code");
				invBinLocHistory.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("InvBinLocHistory_udf4Code");
				invBinLocHistory.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("InvBinLocHistory_udf5Code");
				invBinLocHistory.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_qtyOnHand"))
			{
				String qtyOnHandString = (String) incomingRequest.get("InvBinLocHistory_qtyOnHand");
				if (Utility.isEmpty(qtyOnHandString))
				{
					qtyOnHandString = "0";
				}
				BigDecimal qtyOnHand = new BigDecimal ( qtyOnHandString );
				invBinLocHistory.setQtyOnHand(qtyOnHand);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_qtyAlloc"))
			{
				String qtyAllocString = (String) incomingRequest.get("InvBinLocHistory_qtyAlloc");
				if (Utility.isEmpty(qtyAllocString))
				{
					qtyAllocString = "0";
				}
				BigDecimal qtyAlloc = new BigDecimal ( qtyAllocString );
				invBinLocHistory.setQtyAlloc(qtyAlloc);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_actionCode"))
			{
				String actionCode = (String) incomingRequest.get("InvBinLocHistory_actionCode");
				invBinLocHistory.setActionCode(actionCode);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_qtyMoved"))
			{
				String qtyMovedString = (String) incomingRequest.get("InvBinLocHistory_qtyMoved");
				if (Utility.isEmpty(qtyMovedString))
				{
					qtyMovedString = "0";
				}
				BigDecimal qtyMoved = new BigDecimal ( qtyMovedString );
				invBinLocHistory.setQtyMoved(qtyMoved);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_histText"))
			{
				String histText = (String) incomingRequest.get("InvBinLocHistory_histText");
				invBinLocHistory.setHistText(histText);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_transactionDate"))
			{
				String transactionDateString = (String) incomingRequest.get("InvBinLocHistory_transactionDate");
				Date transactionDate = Dates.getDate(transactionDateString);
				invBinLocHistory.setTransactionDate(transactionDate);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_icCode"))
			{
				String icCodeString = (String) incomingRequest.get("InvBinLocHistory_icCode");
				if (Utility.isEmpty(icCodeString))
				{
					icCodeString = "0";
				}
				BigDecimal icCode = new BigDecimal ( icCodeString );
				invBinLocHistory.setIcCode(icCode);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_transactionTime"))
			{
				String transactionTime = (String) incomingRequest.get("InvBinLocHistory_transactionTime");
				invBinLocHistory.setTransactionTime(transactionTime);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_userId"))
			{
				String userId = (String) incomingRequest.get("InvBinLocHistory_userId");
				invBinLocHistory.setUserId(userId);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_reasonCode"))
			{
				String reasonCode = (String) incomingRequest.get("InvBinLocHistory_reasonCode");
				invBinLocHistory.setReasonCode(reasonCode);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("InvBinLocHistory_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				invBinLocHistory.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_icDsbHeader"))
			{
				String icDsbHeaderString = (String) incomingRequest.get("InvBinLocHistory_icDsbHeader");
				if (Utility.isEmpty(icDsbHeaderString))
				{
					icDsbHeaderString = "0";
				}
				BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
				invBinLocHistory.setIcDsbHeader(icDsbHeader);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_icDsbLine"))
			{
				String icDsbLineString = (String) incomingRequest.get("InvBinLocHistory_icDsbLine");
				if (Utility.isEmpty(icDsbLineString))
				{
					icDsbLineString = "0";
				}
				BigDecimal icDsbLine = new BigDecimal ( icDsbLineString );
				invBinLocHistory.setIcDsbLine(icDsbLine);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_duomQtyOnHand"))
			{
				String duomQtyOnHandString = (String) incomingRequest.get("InvBinLocHistory_duomQtyOnHand");
				if (Utility.isEmpty(duomQtyOnHandString))
				{
					duomQtyOnHandString = "0";
				}
				BigDecimal duomQtyOnHand = new BigDecimal ( duomQtyOnHandString );
				invBinLocHistory.setDuomQtyOnHand(duomQtyOnHand);
			}
			if (incomingRequest.containsKey("InvBinLocHistory_duomQtyAlloc"))
			{
				String duomQtyAllocString = (String) incomingRequest.get("InvBinLocHistory_duomQtyAlloc");
				if (Utility.isEmpty(duomQtyAllocString))
				{
					duomQtyAllocString = "0";
				}
				BigDecimal duomQtyAlloc = new BigDecimal ( duomQtyAllocString );
				invBinLocHistory.setDuomQtyAlloc(duomQtyAlloc);
			}

			result = invBinLocHistory;
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