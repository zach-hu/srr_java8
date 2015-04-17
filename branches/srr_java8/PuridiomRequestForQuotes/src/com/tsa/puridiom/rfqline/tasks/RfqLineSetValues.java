package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RfqLineSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			if (rfqLine == null)
			{
				rfqLine = new RfqLine();
			}

			if (incomingRequest.containsKey("RfqLine_icRfqLine"))
			{
				String icRfqLineString = (String) incomingRequest.get("RfqLine_icRfqLine");
				if (Utility.isEmpty(icRfqLineString))
				{
					icRfqLineString = "0";
				}
				BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
				rfqLine.setIcRfqLine(icRfqLine);
			}
			if (incomingRequest.containsKey("RfqLine_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("RfqLine_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				rfqLine.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("RfqLine_rfqLine"))
			{
				String rfqLineString = (String) incomingRequest.get("RfqLine_rfqLine");
				if (Utility.isEmpty(rfqLineString))
				{
					rfqLineString = "0";
				}
				BigDecimal bdRfqLine = new BigDecimal ( rfqLineString );
				rfqLine.setRfqLine(bdRfqLine);
			}
			if (incomingRequest.containsKey("RfqLine_rfqNumber"))
			{
				String rfqNumber = (String ) incomingRequest.get("RfqLine_rfqNumber");
				rfqLine.setRfqNumber(rfqNumber);
			}
			if (incomingRequest.containsKey("RfqLine_source"))
			{
				String source = (String ) incomingRequest.get("RfqLine_source");
				rfqLine.setSource(source);
			}
			if (incomingRequest.containsKey("RfqLine_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("RfqLine_itemNumber");
				rfqLine.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("RfqLine_umCode"))
			{
				String umCode = (String ) incomingRequest.get("RfqLine_umCode");
				rfqLine.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("RfqLine_quantity"))
			{
				String quantityString = (String) incomingRequest.get("RfqLine_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				try
				{
					BigDecimal quantity = new BigDecimal ( quantityString );
					rfqLine.setQuantity(quantity);
				}
				catch (Exception e)
				{
					rfqLine.setQuantity(new BigDecimal(0));
				}
			}
			if (incomingRequest.containsKey("RfqLine_taxable"))
			{
				String taxable = (String ) incomingRequest.get("RfqLine_taxable");
				rfqLine.setTaxable(taxable);
			}
			if (incomingRequest.containsKey("RfqLine_status"))
			{
				String status = (String ) incomingRequest.get("RfqLine_status");
				rfqLine.setStatus(status);
			}
			if (incomingRequest.containsKey("RfqLine_commodity"))
			{
				String commodity = (String ) incomingRequest.get("RfqLine_commodity");
				rfqLine.setCommodity(commodity);
			}
			if (incomingRequest.containsKey("RfqLine_icSource"))
			{
				String icSourceString = (String) incomingRequest.get("RfqLine_icSource");
				if (Utility.isEmpty(icSourceString))
				{
					icSourceString = "0";
				}
				try
				{
					BigDecimal icSource = new BigDecimal ( icSourceString );
					rfqLine.setIcSource(icSource);
				}
				catch (Exception e)
				{
					rfqLine.setIcSource(new BigDecimal(0));
				}
			}
			if (incomingRequest.containsKey("RfqLine_commentFlag"))
			{
				String commentFlag = (String ) incomingRequest.get("RfqLine_commentFlag");
				rfqLine.setCommentFlag(commentFlag);
			}
			if (incomingRequest.containsKey("RfqLine_asset"))
			{
				String asset = (String ) incomingRequest.get("RfqLine_asset");
				rfqLine.setAsset(asset);
			}
			if (incomingRequest.containsKey("RfqLine_splits"))
			{
				String splits = (String ) incomingRequest.get("RfqLine_splits");
				rfqLine.setSplits(splits);
			}
			if (incomingRequest.containsKey("RfqLine_catalogId"))
			{
				String catalogId = (String ) incomingRequest.get("RfqLine_catalogId");
				rfqLine.setCatalogId(catalogId);
			}
			if (incomingRequest.containsKey("RfqLine_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("RfqLine_umFactor");
				if (Utility.isEmpty(umFactorString))
				{
					umFactorString = "1";
				}
				try
				{
					BigDecimal umFactor = new BigDecimal ( umFactorString );
					rfqLine.setUmFactor(umFactor);
				}
				catch (Exception e)
				{
					rfqLine.setUmFactor(new BigDecimal(1));
				}
			}
			if (incomingRequest.containsKey("RfqLine_icReqLine"))
			{
				String icReqLineString = (String) incomingRequest.get("RfqLine_icReqLine");
				if (Utility.isEmpty(icReqLineString))
				{
					icReqLineString = "0";
				}
				try
				{
					BigDecimal icReqLine = new BigDecimal ( icReqLineString );
					rfqLine.setIcReqLine(icReqLine);
				}
				catch (Exception e)
				{
					rfqLine.setIcReqLine(new BigDecimal(0));
				}
			}
			if (incomingRequest.containsKey("RfqLine_shiptoFlag"))
			{
				String shiptoFlag = (String ) incomingRequest.get("RfqLine_shiptoFlag");
				rfqLine.setShiptoFlag(shiptoFlag);
			}
			if (incomingRequest.containsKey("RfqLine_allocated"))
			{
				String allocatedString = (String) incomingRequest.get("RfqLine_allocated");
				if (Utility.isEmpty(allocatedString))
				{
					allocatedString = "0";
				}
				try
				{
					BigDecimal allocated = new BigDecimal ( allocatedString );
					rfqLine.setAllocated(allocated);
				}
				catch (Exception e)
				{
					rfqLine.setAllocated(new BigDecimal(0));
				}
			}
			if (incomingRequest.containsKey("RfqLine_mfgName"))
			{
				String mfgName = (String ) incomingRequest.get("RfqLine_mfgName");
				rfqLine.setMfgName(mfgName);
			}
			if (incomingRequest.containsKey("RfqLine_modelNumber"))
			{
				String modelNumber = (String ) incomingRequest.get("RfqLine_modelNumber");
				rfqLine.setModelNumber(modelNumber);
			}
			if (incomingRequest.containsKey("RfqLine_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("RfqLine_udf1Code");
				rfqLine.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("RfqLine_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("RfqLine_udf2Code");
				rfqLine.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("RfqLine_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("RfqLine_udf3Code");
				rfqLine.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("RfqLine_udf4Code"))
			{
				String udf4Code = (String ) incomingRequest.get("RfqLine_udf4Code");
				rfqLine.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("RfqLine_udf5Code"))
			{
				String udf5Code = (String ) incomingRequest.get("RfqLine_udf5Code");
				rfqLine.setUdf5Code(udf5Code);
			}
            if (incomingRequest.containsKey("RfqLine_udf6Code"))
            {
                String udf6Code = (String ) incomingRequest.get("RfqLine_udf6Code");
                rfqLine.setUdf6Code(udf6Code);
            }
            if (incomingRequest.containsKey("RfqLine_udf7Code"))
            {
                String udf7Code = (String ) incomingRequest.get("RfqLine_udf7Code");
                rfqLine.setUdf7Code(udf7Code);
            }
            if (incomingRequest.containsKey("RfqLine_udf8Code"))
            {
                String udf8Code = (String ) incomingRequest.get("RfqLine_udf8Code");
                rfqLine.setUdf8Code(udf8Code);
            }
            if (incomingRequest.containsKey("RfqLine_udf9Code"))
            {
                String udf9Code = (String ) incomingRequest.get("RfqLine_udf9Code");
                rfqLine.setUdf9Code(udf9Code);
            }
            if (incomingRequest.containsKey("RfqLine_udf10Code"))
            {
                String udf10Code = (String ) incomingRequest.get("RfqLine_udf10Code");
                rfqLine.setUdf10Code(udf10Code);
            }
            if (incomingRequest.containsKey("RfqLine_memoLine"))
            {
                String memoLine = (String ) incomingRequest.get("RfqLine_memoLine");
                rfqLine.setMemoLine(memoLine);
            }

			if (incomingRequest.containsKey("RfqLine_lineRevNo"))
			{
				String lineRevNo = (String ) incomingRequest.get("RfqLine_lineRevNo");
				rfqLine.setLineRevNo(lineRevNo);
			}
			if (incomingRequest.containsKey("RfqLine_icLineHistory"))
			{
				String icLineHistoryString = (String) incomingRequest.get("RfqLine_icLineHistory");
				if (Utility.isEmpty(icLineHistoryString))
				{
					icLineHistoryString = "0";
				}
				try
				{
					BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString );
					rfqLine.setIcLineHistory(icLineHistory);
				}
				catch (Exception e)
				{
					rfqLine.setIcLineHistory(new BigDecimal(0));
				}
			}

			if (incomingRequest.containsKey("RfqLine_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("RfqLine_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				try
				{
					BigDecimal icAccount = new BigDecimal ( icAccountString );
					rfqLine.setIcAccount(icAccount);
				}
				catch (Exception e)
				{
					rfqLine.setIcAccount(new BigDecimal(0));
				}
			}

			if (incomingRequest.containsKey("RfqLine_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("RfqLine_itemLocation");
				rfqLine.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("RfqLine_description"))
			{
				String description = (String ) incomingRequest.get("RfqLine_description");
				rfqLine.setDescription(description);
			}
			if (incomingRequest.containsKey("RfqLine_vendorAwarded"))
			{
				String vendorAwarded = (String ) incomingRequest.get("RfqLine_vendorAwarded");
				rfqLine.setVendorAwarded(vendorAwarded);
			}
			if (incomingRequest.containsKey("RfqLine_shelfLifeRqd"))
			{
				String shelfLife = (String) incomingRequest.get("RfqLine_shelfLifeRqd");
				rfqLine.setShelfLifeRqd(shelfLife);
			}

			if (incomingRequest.containsKey("RfqLine_icXls"))
			{
				String icXlsString = (String) incomingRequest.get("RfqLine_icXls");
				if (Utility.isEmpty(icXlsString))
				{
					icXlsString = "0";
				}
				try
				{
					BigDecimal icXls = new BigDecimal ( icXlsString );
					rfqLine.setIcXls(icXls);
				}
				catch (Exception e)
				{
					rfqLine.setIcXls(new BigDecimal(0));
				}
			}

			result = rfqLine;
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