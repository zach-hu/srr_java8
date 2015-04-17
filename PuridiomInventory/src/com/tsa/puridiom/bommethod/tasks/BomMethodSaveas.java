package com.tsa.puridiom.bommethod.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BomMethodSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain bomMethod */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BomMethod	originalBomMethod = (BomMethod) incomingRequest.get("bomMethod");
			BomMethod	bomMethod = new BomMethod();

			bomMethod.setIcMethod(originalBomMethod.getIcMethod());
			bomMethod.setParentItem(originalBomMethod.getParentItem());
			bomMethod.setComponentItem(originalBomMethod.getComponentItem());
			bomMethod.setMethodId(originalBomMethod.getMethodId());
			bomMethod.setBatchSize(originalBomMethod.getBatchSize());
			bomMethod.setUnitOfMeasure(originalBomMethod.getUnitOfMeasure());
			bomMethod.setDescription(originalBomMethod.getDescription());
			bomMethod.setNotes(originalBomMethod.getNotes());
			bomMethod.setDateEntered(originalBomMethod.getDateEntered());
			bomMethod.setOwner(originalBomMethod.getOwner());

			incomingRequest.put("bomMethod", bomMethod);

			BomMethodAdd addTask = new BomMethodAdd();
			bomMethod = (BomMethod) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = bomMethod;
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