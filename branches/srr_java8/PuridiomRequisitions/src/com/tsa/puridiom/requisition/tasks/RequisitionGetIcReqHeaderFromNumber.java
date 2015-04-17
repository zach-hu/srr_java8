package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionGetIcReqHeaderFromNumber extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String filename = ((String)incomingRequest.get("filename")).toString().toLowerCase();
			int index = filename.indexOf("requisition");
			if(index > -1)
			{
				int spaceIndex = filename.indexOf("email.eml", index + 12);
				ret = filename.substring(index + 12, spaceIndex);
				incomingRequest.put("RequisitionHeader_requisitionNumber", ret);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition could not be found! ", e);
		}
		// TODO Auto-generated method stub
		return ret;
	}


}
