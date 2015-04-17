package com.tsa.puridiom.bommanufacturer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BomManufacturerSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain bomManufacturer */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BomManufacturer	originalBomManufacturer = (BomManufacturer) incomingRequest.get("bomManufacturer");
			BomManufacturer	bomManufacturer = new BomManufacturer();

			bomManufacturer.setIcManufacturer(originalBomManufacturer.getIcManufacturer());
			bomManufacturer.setParentItem(originalBomManufacturer.getParentItem());
			bomManufacturer.setComponentItem(originalBomManufacturer.getComponentItem());
			bomManufacturer.setIcComponent(originalBomManufacturer.getIcComponent());
			bomManufacturer.setVendorId(originalBomManufacturer.getVendorId());
			bomManufacturer.setVendorName(originalBomManufacturer.getVendorName());
			bomManufacturer.setMethodId(originalBomManufacturer.getMethodId());
			bomManufacturer.setStageId(originalBomManufacturer.getStageId());
			bomManufacturer.setPartNumber(originalBomManufacturer.getPartNumber());
			bomManufacturer.setDateEntered(originalBomManufacturer.getDateEntered());
			bomManufacturer.setOwner(originalBomManufacturer.getOwner());

			incomingRequest.put("bomManufacturer", bomManufacturer);

			BomManufacturerAdd addTask = new BomManufacturerAdd();
			bomManufacturer = (BomManufacturer) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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