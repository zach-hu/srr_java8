package com.tsa.puridiom.reportqueue.tasks;

import java.util.Map;

import com.tsa.puridiom.jasperreports.ReportUtils;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReportSendQueueSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String uid = (String)incomingRequest.get("userId");
			String oid = (String)incomingRequest.get("organizationId");
			incomingRequest.put("SendQueue_action", "RP");
			incomingRequest.put("SendQueue_subject", incomingRequest.get("reportName") );
			incomingRequest.put("SendQueue_messagetext", incomingRequest.get("datasource") );
            incomingRequest.put("SendQueue_sendto", ReportUtils.getUserEmail(uid, oid));
            incomingRequest.put("SendQueue_sendfrom", ReportUtils.getUserEmail(uid, oid));
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_doctype", incomingRequest.get("format"));
            incomingRequest.put("SendQueue_docic", "0");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Report could not be saved!");
		}
		return super.executeTask(object);
	}


}
