/**
 * Created on July 20, 2004
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionline.tasks.RequisitionLineCancelSetup.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineCancelSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			String icReqHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader");
			String icReqLine = (String)incomingRequest.get("RequisitionLine_icReqLine");
			
			incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
			incomingRequest.put("Account_icHeader", icReqHeader);
			incomingRequest.put("Account_icLine", icReqLine);
			incomingRequest.put("formtype", "REQ");
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}
