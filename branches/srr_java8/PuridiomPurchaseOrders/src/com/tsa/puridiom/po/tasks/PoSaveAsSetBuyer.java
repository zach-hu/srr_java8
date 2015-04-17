package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 * When doing a save of a PO the buyer should be the person logged in(doing the save as)
 */
public class PoSaveAsSetBuyer extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			poHeader.setBuyerCode((String)incomingRequest.get("userId"));
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(e.getMessage() + ", when trying to set the buyer for a PO save as", e);
		}
		// TODO Auto-generated method stub
		return super.executeTask(object);
	}

}
