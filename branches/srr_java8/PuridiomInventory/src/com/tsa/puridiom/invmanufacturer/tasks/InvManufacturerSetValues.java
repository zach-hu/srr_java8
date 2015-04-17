package com.tsa.puridiom.invmanufacturer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvManufacturerSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvManufacturer invManufacturer = (InvManufacturer) incomingRequest.get("invManufacturer");
			if (invManufacturer == null)
			{
				invManufacturer = new InvManufacturer();
			}

			if (incomingRequest.containsKey("InvManufacturer_icManufacturer"))
			{
				String icManufacturerString = (String) incomingRequest.get("InvManufacturer_icManufacturer");
				if (Utility.isEmpty(icManufacturerString))
				{
					icManufacturerString = "0";
				}
				BigDecimal icManufacturer = new BigDecimal ( icManufacturerString );
				invManufacturer.setIcManufacturer(icManufacturer);
			}
			if (incomingRequest.containsKey("InvManufacturer_itemNumber"))
			{
				String itemNumber = (String) incomingRequest.get("InvManufacturer_itemNumber");
				invManufacturer.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvManufacturer_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("InvManufacturer_vendorId");
				invManufacturer.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("InvManufacturer_partNumber"))
			{
				String partNumber = (String) incomingRequest.get("InvManufacturer_partNumber");
				invManufacturer.setPartNumber(partNumber);
			}
			if (incomingRequest.containsKey("InvManufacturer_notes"))
			{
				String notes = (String) incomingRequest.get("InvManufacturer_notes");
				invManufacturer.setNotes(notes);
			}
			if (incomingRequest.containsKey("InvManufacturer_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvManufacturer_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invManufacturer.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvManufacturer_owner"))
			{
				String owner = (String) incomingRequest.get("InvManufacturer_owner");
				invManufacturer.setOwner(owner);
			}

			result = invManufacturer;
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