package com.tsa.puridiom.alerts.tasks;

import java.util.Map;

import com.tsa.puridiom.alerts.Alert;
import com.tsa.puridiom.alerts.AlertManager;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AlertGetProcessFromType extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		String type = (String)incomingRequest.get("alerttype");
		String organizationId = (String)incomingRequest.get("organizationId");

		try
		{
			if(type.equalsIgnoreCase("PO"))
			{
				incomingRequest.put("PoHeader_icPoHeader", incomingRequest.get("mainIc").toString());
				ret = "po-retrieve.xml";
			}
			else if(type.equalsIgnoreCase("REQ"))
			{
				incomingRequest.put("RequisitionHeader_icReqHeader", incomingRequest.get("mainIc").toString());
				ret = "requisition-retrieve.xml";
			}
			else if(type.equalsIgnoreCase("RFQ"))
			{
				incomingRequest.put("RfqHeader_icRfqHeader", incomingRequest.get("mainIc").toString());
				ret = "rfq-retrieve.xml";
			}
			else if(type.equalsIgnoreCase("RSQ"))
			{
				incomingRequest.put("SendQueue_docic", incomingRequest.get("mainIc").toString());
				incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);

				ret = "reset_sendqueue_hten.xml";
			}
			else if(type.equalsIgnoreCase("PSQ"))
			{
				incomingRequest.put("SendQueue_docic", incomingRequest.get("mainIc").toString());
				incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);

				ret = "reset_sendqueue_po_hten.xml";
			}
			else
			{
				Alert alert = AlertManager.getInstance().getAlert(organizationId, (String)incomingRequest.get("alertname"));
				incomingRequest.put(alert.getRetrieveArgumentName(), incomingRequest.get("mainArgument"));
				ret = alert.getRetrieveProcess();
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Process Name was not found for alert type: " + type, e);
		}
		return ret;
	}


}
