/**
 *
 */
package com.tsa.puridiom.approvallink.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLink;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Johnny Zapana
 */
public class ApprovalLinkSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ApprovalLink approvalLink = (ApprovalLink) incomingRequest.get("approvalLink");

			if (approvalLink == null)
			{
				approvalLink = new ApprovalLink();
			}

			if (incomingRequest.containsKey("ApprovalLink_icApprovalLink"))
			{
				String icApprovalLinkString = (String) incomingRequest.get("ApprovalLink_icApprovalLink");

				if (Utility.isEmpty(icApprovalLinkString))
				{
					icApprovalLinkString = "0";
				}
				BigDecimal icApprovalLink = new BigDecimal(icApprovalLinkString);
				approvalLink.setIcApprovalLink(icApprovalLink);
			}

			if (incomingRequest.containsKey("ApprovalLink_organizationId"))
			{
				String organizationId = (String) incomingRequest.get("ApprovalLink_organizationId");
				if (organizationId != null)
				{
					organizationId = organizationId.trim().toUpperCase();
				}
				approvalLink.setOrganizationId(organizationId);
			}

			if (incomingRequest.containsKey("ApprovalLink_userId"))
			{
				String uid = (String) incomingRequest.get("ApprovalLink_userId");
				approvalLink.setUserId(uid);
			}

			if (incomingRequest.containsKey("ApprovalLink_documentNumber"))
			{
				String documentNumber = (String) incomingRequest.get("ApprovalLink_documentNumber");
				approvalLink.setDocumentNumber(documentNumber);
			}

			if (incomingRequest.containsKey("ApprovalLink_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("ApprovalLink_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal(icHeaderString);
				approvalLink.setIcHeader(icHeader);
			}

			if (incomingRequest.containsKey("ApprovalLink_doctype"))
			{
				String doctype = (String) incomingRequest.get("ApprovalLink_doctype");
				approvalLink.setDoctype(doctype);
			}
			
			if (incomingRequest.containsKey("ApprovalLink_action"))
			{
				String action = (String) incomingRequest.get("ApprovalLink_action");
				approvalLink.setAction(action);
			}
			
			if (incomingRequest.containsKey("ApprovalLink_logDate"))
			{
				String logDate = (String) incomingRequest.get("ApprovalLink_logDate");
				approvalLink.setLogDate(logDate);
			}
			
			if (incomingRequest.containsKey("ApprovalLink_logTime"))
			{
				String logTime = (String) incomingRequest.get("ApprovalLink_logTime");
				approvalLink.setLogTime(logTime);
			}

			result = approvalLink;
			
			this.status = Status.SUCCEEDED;
		} catch (Exception e)
		{
			Log.error(this, "Error executing ApprovalLinkSetValues \r\n" + e.getMessage());

			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
