package com.tsa.puridiom.invmanufacturer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvManufacturerSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invManufacturer */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvManufacturer	originalInvManufacturer = (InvManufacturer) incomingRequest.get("invManufacturer");
			InvManufacturer	invManufacturer = new InvManufacturer();

			invManufacturer.setIcManufacturer(originalInvManufacturer.getIcManufacturer());
			invManufacturer.setItemNumber(originalInvManufacturer.getItemNumber());
			invManufacturer.setVendorId(originalInvManufacturer.getVendorId());
			invManufacturer.setPartNumber(originalInvManufacturer.getPartNumber());
			invManufacturer.setNotes(originalInvManufacturer.getNotes());
			invManufacturer.setDateEntered(originalInvManufacturer.getDateEntered());
			invManufacturer.setOwner(originalInvManufacturer.getOwner());

			incomingRequest.put("invManufacturer", invManufacturer);

			InvManufacturerAdd addTask = new InvManufacturerAdd();
			invManufacturer = (InvManufacturer) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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