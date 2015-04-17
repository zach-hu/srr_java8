package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class RequisitionHeaderAccountRetrieveSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String icHeader = requisitionHeader.getIcReqHeader().toString();
			incomingRequest.put("Account_icHeader", icHeader);
			incomingRequest.put("Account_icLine", "0");

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
