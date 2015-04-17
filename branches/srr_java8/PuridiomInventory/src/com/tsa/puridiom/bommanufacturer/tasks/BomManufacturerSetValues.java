package com.tsa.puridiom.bommanufacturer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomManufacturerSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BomManufacturer bomManufacturer = (BomManufacturer) incomingRequest.get("bomManufacturer");
			if (bomManufacturer == null)
			{
				bomManufacturer = new BomManufacturer();
			}

			if (incomingRequest.containsKey("BomManufacturer_icManufacturer"))
			{
				String icManufacturerString = (String) incomingRequest.get("BomManufacturer_icManufacturer");
				if (Utility.isEmpty(icManufacturerString))
				{
					icManufacturerString = "0";
				}
				BigDecimal icManufacturer = new BigDecimal ( icManufacturerString );
				bomManufacturer.setIcManufacturer(icManufacturer);
			}
			if (incomingRequest.containsKey("BomManufacturer_parentItem"))
			{
				String parentItem = (String) incomingRequest.get("BomManufacturer_parentItem");
				bomManufacturer.setParentItem(parentItem);
			}
			if (incomingRequest.containsKey("BomManufacturer_componentItem"))
			{
				String componentItem = (String) incomingRequest.get("BomManufacturer_componentItem");
				bomManufacturer.setComponentItem(componentItem);
			}
			if (incomingRequest.containsKey("BomManufacturer_icComponent"))
			{
				String icComponentString = (String) incomingRequest.get("BomManufacturer_icComponent");
				if (Utility.isEmpty(icComponentString))
				{
					icComponentString = "0";
				}
				BigDecimal icComponent = new BigDecimal ( icComponentString );
				bomManufacturer.setIcComponent(icComponent);
			}
			if (incomingRequest.containsKey("BomManufacturer_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("BomManufacturer_vendorId");
				bomManufacturer.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("BomManufacturer_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("BomManufacturer_vendorName");
				bomManufacturer.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("BomManufacturer_methodId"))
			{
				String methodId = (String) incomingRequest.get("BomManufacturer_methodId");
				bomManufacturer.setMethodId(methodId);
			}
			if (incomingRequest.containsKey("BomManufacturer_stageId"))
			{
				String stageId = (String) incomingRequest.get("BomManufacturer_stageId");
				bomManufacturer.setStageId(stageId);
			}
			if (incomingRequest.containsKey("BomManufacturer_partNumber"))
			{
				String partNumber = (String) incomingRequest.get("BomManufacturer_partNumber");
				bomManufacturer.setPartNumber(partNumber);
			}
			if (incomingRequest.containsKey("BomManufacturer_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("BomManufacturer_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				bomManufacturer.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("BomManufacturer_owner"))
			{
				String owner = (String) incomingRequest.get("BomManufacturer_owner");
				bomManufacturer.setOwner(owner);
			}

			result = bomManufacturer;
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