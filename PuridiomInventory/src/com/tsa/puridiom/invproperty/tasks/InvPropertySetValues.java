package com.tsa.puridiom.invproperty.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvPropertySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvProperty invProperty = (InvProperty) incomingRequest.get("invProperty");
			if (invProperty == null)
			{
				invProperty = new InvProperty();
			}

			if (incomingRequest.containsKey("InvProperty_icProperty"))
			{
				String icPropertyString = (String) incomingRequest.get("InvProperty_icProperty");
				if (Utility.isEmpty(icPropertyString))
				{
					icPropertyString = "0";
				}
				BigDecimal icProperty = new BigDecimal ( icPropertyString );
				invProperty.setIcProperty(icProperty);
			}
			if (incomingRequest.containsKey("InvProperty_propertyType"))
			{
				String propertyType = (String) incomingRequest.get("InvProperty_propertyType");
				invProperty.setPropertyType(propertyType);
			}
			if (incomingRequest.containsKey("InvProperty_tagNumber"))
			{
				String tagNumber = (String) incomingRequest.get("InvProperty_tagNumber");
				invProperty.setTagNumber(tagNumber);
			}
			if (incomingRequest.containsKey("InvProperty_icRc"))
			{
				String icRcString = (String) incomingRequest.get("InvProperty_icRc");
				if (Utility.isEmpty(icRcString) || icRcString.equalsIgnoreCase("null"))
				{
					icRcString = "0";
				}
				BigDecimal icRc = new BigDecimal ( icRcString );
				invProperty.setIcRc(icRc);
			}
			if (incomingRequest.containsKey("InvProperty_itemNumber"))
			{
				String itemNumber = (String) incomingRequest.get("InvProperty_itemNumber");
				invProperty.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvProperty_serialNumber"))
			{
				String serialNumber = (String) incomingRequest.get("InvProperty_serialNumber");
				invProperty.setSerialNumber(serialNumber);
			}
			if (incomingRequest.containsKey("InvProperty_poNumber"))
			{
				String poNumber = (String) incomingRequest.get("InvProperty_poNumber");
				invProperty.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("InvProperty_contract"))
			{
				String contract = (String) incomingRequest.get("InvProperty_contract");
				invProperty.setContract(contract);
			}
			if (incomingRequest.containsKey("InvProperty_receiptNumber"))
			{
				String receiptNumber = (String) incomingRequest.get("InvProperty_receiptNumber");
				invProperty.setReceiptNumber(receiptNumber);
			}
			if (incomingRequest.containsKey("InvProperty_shipNumber"))
			{
				String shipNumber = (String) incomingRequest.get("InvProperty_shipNumber");
				invProperty.setShipNumber(shipNumber);
			}
			if (incomingRequest.containsKey("InvProperty_remarks"))
			{
				String remarks = (String) incomingRequest.get("InvProperty_remarks");
				invProperty.setRemarks(remarks);
			}
			if (incomingRequest.containsKey("InvProperty_dateIn"))
			{
				String dateInString = (String) incomingRequest.get("InvProperty_dateIn");
				Date dateIn = Dates.getDate(dateInString);
				invProperty.setDateIn(dateIn);
			}
			if (incomingRequest.containsKey("InvProperty_dateOut"))
			{
				String dateOutString = (String) incomingRequest.get("InvProperty_dateOut");
				if(!HiltonUtility.isEmpty(dateOutString))
				{
					Date dateOut = Dates.getDate(dateOutString);
					invProperty.setDateOut(dateOut);
				}
				else
					invProperty.setDateOut(null);
			}
			if (incomingRequest.containsKey("InvProperty_status"))
			{
				String status = (String) incomingRequest.get("InvProperty_status");
				invProperty.setStatus(status);
			}
			if (incomingRequest.containsKey("InvProperty_owner"))
			{
				String owner = (String) incomingRequest.get("InvProperty_owner");
				invProperty.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvProperty_cblOutNumber"))
			{
				String cblOutNumber = (String) incomingRequest.get("InvProperty_cblOutNumber");
				invProperty.setCblOutNumber(cblOutNumber);
			}
			if (incomingRequest.containsKey("InvProperty_armyNumber"))
			{
				String armyNumber = (String) incomingRequest.get("InvProperty_armyNumber");
				invProperty.setArmyNumber(armyNumber);
			}
			if (incomingRequest.containsKey("InvProperty_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("InvProperty_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				invProperty.setIcRecLine(icRecLine);
			}
			if (incomingRequest.containsKey("InvProperty_icPoLine"))
			{
				String icPoLineString = (String) incomingRequest.get("InvProperty_icPoLine");
				if (Utility.isEmpty(icPoLineString))
				{
					icPoLineString = "0";
				}
				BigDecimal icPoLine = new BigDecimal ( icPoLineString );
				invProperty.setIcPoLine(icPoLine);
			}
			if (incomingRequest.containsKey("InvProperty_assetNumber"))
			{
				String assetNumber = (String) incomingRequest.get("InvProperty_assetNumber");
				invProperty.setAssetNumber(assetNumber);
			}
			if (incomingRequest.containsKey("InvProperty_source"))
			{
				String source = (String) incomingRequest.get("InvProperty_source");
				invProperty.setSource(source);
			}
			if (incomingRequest.containsKey("InvProperty_icDsbLine"))
			{
				String icDsbLineString = (String) incomingRequest.get("InvProperty_icDsbLine");
				if (Utility.isEmpty(icDsbLineString))
				{
					icDsbLineString = "0";
				}
				BigDecimal icDsbLine = new BigDecimal ( icDsbLineString );
				invProperty.setIcDsbLine(icDsbLine);
			}

			result = invProperty;
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