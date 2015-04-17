/**
 * 
 */
package com.tsa.puridiom.approvallink.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLink;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class ApprovalLinkServiceSetup extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ApprovalLink approvalLink = (ApprovalLink) incomingRequest.get("approvalLink");
			String docType = approvalLink.getDoctype();
			incomingRequest.put("serviceLinkAction", approvalLink.getAction());

			if (docType.equalsIgnoreCase("REQ"))
			{
				incomingRequest.put("RequisitionHeader_icReqHeader", approvalLink.getIcHeader().toString());
			} else if (docType.equalsIgnoreCase("IV"))
			{
				incomingRequest.put("InvoiceHeader_icIvcHeader", approvalLink.getIcHeader().toString());
			} else
			{
				incomingRequest.put("PoHeader_icPoHeader", approvalLink.getIcHeader().toString());
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "ApprovalLinkServiceSetup error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
