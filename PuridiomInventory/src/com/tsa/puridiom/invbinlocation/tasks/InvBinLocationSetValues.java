package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvBinLocationSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvBinLocation invBinLocation = (InvBinLocation) incomingRequest.get("invBinLocation");
			if (invBinLocation == null)
			{
				invBinLocation = new InvBinLocation();
			}

			if (incomingRequest.containsKey("InvBinLocation_icRc"))
			{
				BigDecimal icRc = null;
				String icRcString = (String) incomingRequest.get("InvBinLocation_icRc");
				if (Utility.isEmpty(icRcString))
				{
					UniqueKeyGenerator uk = UniqueKeyGenerator.getInstance();
					BigDecimal icRcSet = new BigDecimal(uk.getUniqueKey().toString());
					icRc = icRcSet;
				}
				else
				{
					icRc = new BigDecimal ( icRcString );
				}
				invBinLocation.setIcRc(icRc);
			}
			if (incomingRequest.containsKey("InvBinLocation_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvBinLocation_itemNumber");
				invBinLocation.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvBinLocation_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("InvBinLocation_itemLocation");
				invBinLocation.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("InvBinLocation_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("InvBinLocation_vendorId");
				invBinLocation.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("InvBinLocation_manufactNo"))
			{
				String manufactNo = (String ) incomingRequest.get("InvBinLocation_manufactNo");
				invBinLocation.setManufactNo(manufactNo);
			}
			if (incomingRequest.containsKey("InvBinLocation_lot"))
			{
				String lot = (String ) incomingRequest.get("InvBinLocation_lot");
				invBinLocation.setLot(lot);
			}
			if (incomingRequest.containsKey("InvBinLocation_cost"))
			{
				String costString = (String) incomingRequest.get("InvBinLocation_cost");
				if (Utility.isEmpty(costString))
				{
					costString = "0";
				}
				BigDecimal cost = new BigDecimal ( costString );
				invBinLocation.setCost(cost);
			}
			if (incomingRequest.containsKey("InvBinLocation_itemDate"))
			{
				String itemDateString = (String) incomingRequest.get("InvBinLocation_itemDate");
				if (!(Utility.isEmpty(itemDateString)))
				{
					Date itemDate = Dates.getDate(itemDateString);
					invBinLocation.setItemDate(itemDate);
				}
			}
			/*if (incomingRequest.containsKey("InvBinLocation_itemDate"))
			{
				String itemDateString = (String) incomingRequest.get("InvBinLocation_itemDate");
				Date itemDate = Dates.getDate(itemDateString);
				invBinLocation.setItemDate(itemDate);
			}*/
			if (incomingRequest.containsKey("InvBinLocation_aisle"))
			{
				String aisle = (String ) incomingRequest.get("InvBinLocation_aisle");
				invBinLocation.setAisle(aisle);
			}
			if (incomingRequest.containsKey("InvBinLocation_locrow"))
			{
				String locrow = (String ) incomingRequest.get("InvBinLocation_locrow");
				invBinLocation.setLocrow(locrow);
			}
			if (incomingRequest.containsKey("InvBinLocation_tier"))
			{
				String tier = (String ) incomingRequest.get("InvBinLocation_tier");
				invBinLocation.setTier(tier);
			}
			if (incomingRequest.containsKey("InvBinLocation_bin"))
			{
				String bin = (String ) incomingRequest.get("InvBinLocation_bin");
				invBinLocation.setBin(bin);
			}
			if (incomingRequest.containsKey("InvBinLocation_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("InvBinLocation_udf1Code");
				invBinLocation.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("InvBinLocation_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("InvBinLocation_udf2Code");
				invBinLocation.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("InvBinLocation_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("InvBinLocation_udf3Code");
				invBinLocation.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("InvBinLocation_udf4Code"))
			{
				String udf4Code = (String ) incomingRequest.get("InvBinLocation_udf4Code");
				invBinLocation.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("InvBinLocation_udf5Code"))
			{
				String udf5Code = (String ) incomingRequest.get("InvBinLocation_udf5Code");
				invBinLocation.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("InvBinLocation_owner"))
			{
				String owner = (String ) incomingRequest.get("InvBinLocation_owner");
				invBinLocation.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvBinLocation_qtyOnHand"))
			{
				String qtyOnHandString = (String) incomingRequest.get("InvBinLocation_qtyOnHand");
				if (Utility.isEmpty(qtyOnHandString))
				{
					qtyOnHandString = "0";
				}
				BigDecimal qtyOnHand = new BigDecimal ( qtyOnHandString );
				invBinLocation.setQtyOnHand(qtyOnHand);
			}
			if (incomingRequest.containsKey("InvBinLocation_qtyAlloc"))
			{
				String qtyAllocString = (String) incomingRequest.get("InvBinLocation_qtyAlloc");
				if (Utility.isEmpty(qtyAllocString))
				{
					qtyAllocString = "0";
				}
				BigDecimal qtyAlloc = new BigDecimal ( qtyAllocString );
				invBinLocation.setQtyAlloc(qtyAlloc);
			}
			if (incomingRequest.containsKey("InvBinLocation_hdrIc"))
			{
				String hdrIc = (String ) incomingRequest.get("InvBinLocation_hdrIc");
				invBinLocation.setHdrIc(hdrIc);
			}
			if (incomingRequest.containsKey("InvBinLocation_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("InvBinLocation_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				invBinLocation.setIcRecLine(icRecLine);
			}
			if (incomingRequest.containsKey("InvBinLocation_physActual"))
			{
				String physActualString = (String) incomingRequest.get("InvBinLocation_physActual");
				if (Utility.isEmpty(physActualString))
				{
					physActualString = "0";
				}
				BigDecimal physActual = new BigDecimal ( physActualString );
				invBinLocation.setPhysActual(physActual);
			}
			if (incomingRequest.containsKey("InvBinLocation_physOriginal"))
			{
				String physOriginalString = (String) incomingRequest.get("InvBinLocation_physOriginal");
				if (Utility.isEmpty(physOriginalString))
				{
					physOriginalString = "0";
				}
				BigDecimal physOriginal = new BigDecimal ( physOriginalString );
				invBinLocation.setPhysOriginal(physOriginal);
			}
			if (incomingRequest.containsKey("InvBinLocation_originalAlloc"))
			{
				String originalAllocString = (String) incomingRequest.get("InvBinLocation_originalAlloc");
				if (Utility.isEmpty(originalAllocString))
				{
					originalAllocString = "0";
				}
				BigDecimal originalAlloc = new BigDecimal ( originalAllocString );
				invBinLocation.setOriginalAlloc(originalAlloc);
			}
			if (incomingRequest.containsKey("InvBinLocation_status"))
			{
				String status = (String ) incomingRequest.get("InvBinLocation_status");
				invBinLocation.setStatus(status);
			}
			if (incomingRequest.containsKey("InvBinLocation_tempIc"))
			{
				String tempIcString = (String) incomingRequest.get("InvBinLocation_tempIc");
				if (Utility.isEmpty(tempIcString))
				{
					tempIcString = "0";
				}
				BigDecimal tempIc = new BigDecimal ( tempIcString );
				invBinLocation.setTempIc(tempIc);
			}

			if (incomingRequest.containsKey("InvBinLocation_duomQtyOnHand"))
			{
				String qtyOnHandString = (String) incomingRequest.get("InvBinLocation_duomQtyOnHand");
				if (Utility.isEmpty(qtyOnHandString))
				{
					qtyOnHandString = "0";
				}
				BigDecimal qtyOnHand = new BigDecimal ( qtyOnHandString );
				invBinLocation.setDuomQtyOnHand(qtyOnHand);
			}
			if (incomingRequest.containsKey("InvBinLocation_duomQtyAlloc"))
			{
				String qtyAllocString = (String) incomingRequest.get("InvBinLocation_duomQtyAlloc");
				if (Utility.isEmpty(qtyAllocString))
				{
					qtyAllocString = "0";
				}
				BigDecimal qtyAlloc = new BigDecimal ( qtyAllocString );
				invBinLocation.setDuomQtyAlloc(qtyAlloc);
			}
			if (incomingRequest.containsKey("InvBinLocation_duomPhysActual"))
			{
				String physActualString = (String) incomingRequest.get("InvBinLocation_duomPhysActual");
				if (Utility.isEmpty(physActualString))
				{
					physActualString = "0";
				}
				BigDecimal physActual = new BigDecimal ( physActualString );
				invBinLocation.setDuomPhysActual(physActual);
			}
			if (incomingRequest.containsKey("InvBinLocation_duomPhysOriginal"))
			{
				String physOriginalString = (String) incomingRequest.get("InvBinLocation_duomPhysOriginal");
				if (Utility.isEmpty(physOriginalString))
				{
					physOriginalString = "0";
				}
				BigDecimal physOriginal = new BigDecimal ( physOriginalString );
				invBinLocation.setDuomPhysOriginal(physOriginal);
			}
			if (incomingRequest.containsKey("InvBinLocation_source"))
			{
				String source = (String ) incomingRequest.get("InvBinLocation_source");
				invBinLocation.setSource(source);
			}
			if (incomingRequest.containsKey("InvBinLocation_chargeCode"))
			{
				String chargeCode = (String ) incomingRequest.get("InvBinLocation_chargeCode");
				invBinLocation.setChargeCode(chargeCode);
			}

			result = invBinLocation;
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
