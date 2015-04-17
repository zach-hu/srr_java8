/*
 * Created on Nov 11, 2003 
 */
package com.tsa.puridiom.disbursement.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class DisbursementCreateCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			String requisitionHeaderIc = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
			
			incomingRequest.put("RequisitionLine_icReqHeader", requisitionHeaderIc);
			if(Utility.isEmpty(requisitionHeaderIc))
			{
				this.status = Status.FAILED;
				return incomingRequest;
			}
			incomingRequest.put("source", "REQ");
			String type = (String)incomingRequest.get("RequisitionHeader_requisitionType");
			if(Utility.isEmpty(type))
			{
				incomingRequest.put("RequisitionHeader_requisitionType", "S");
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}
		return null;
	}
}
