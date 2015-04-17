package com.tsa.puridiom.bomreference.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BomReferenceSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain bomReference */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BomReference	originalBomReference = (BomReference) incomingRequest.get("bomReference");
			BomReference	bomReference = new BomReference();

			bomReference.setIcReference(originalBomReference.getIcReference());
			bomReference.setParentItem(originalBomReference.getParentItem());
			bomReference.setComponentItem(originalBomReference.getComponentItem());
			bomReference.setIcComponent(originalBomReference.getIcComponent());
			bomReference.setReferenceId(originalBomReference.getReferenceId());
			bomReference.setRefNo(originalBomReference.getRefNo());
			bomReference.setQty(originalBomReference.getQty());
			bomReference.setMethodId(originalBomReference.getMethodId());
			bomReference.setStageId(originalBomReference.getStageId());
			bomReference.setIcRouting(originalBomReference.getIcRouting());
			bomReference.setDateEntered(originalBomReference.getDateEntered());
			bomReference.setOwner(originalBomReference.getOwner());

			incomingRequest.put("bomReference", bomReference);

			BomReferenceAdd addTask = new BomReferenceAdd();
			bomReference = (BomReference) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = bomReference;
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