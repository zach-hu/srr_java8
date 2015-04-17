/**
 *
 */
package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

/**
 * @author Johnny Zapana
 */
public class ValidateOrganizationIdHandler implements IHandler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String) incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("validate-organization-id.xml");
			process.executeProcess(incomingRequest);

			String errorMsg = (String) incomingRequest.get("errorMsg");

			if (errorMsg != null && errorMsg.trim().length() > 0)
			{
				incomingRequest.put("viewPage", incomingRequest.get("registrationFailurePage"));
				incomingRequest.put("userId", "");
				incomingRequest.put("organizationId", "");
			} else
			{
				if (process.getStatus() == Status.SUCCEEDED)
				{
					incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				} else
				{
					incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
				}
			}

		} catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		} finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}