package com.tsa.puridiom.handlers;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ErrorReportHandler implements IHandler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String) incomingRequest.get("oid"));
			PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
			Map newIncomingRequest = new HashMap();
			String organizationId = (String) incomingRequest.get("oid");
			String userId = (String) incomingRequest.get("uid");

			String sendFrom = (String) incomingRequest.get("error_emailFrom");
			String sendTo = "support@puridiom.com";
			StringBuffer message = new StringBuffer();
			String urlError = (String) incomingRequest.get("error_url");
			String question = (String) incomingRequest.get("error_question");
			String subject = (String) incomingRequest.get("error_subject");
			String errorMessage = (String) incomingRequest.get("error_message");

			message.append(subject + " from " + sendFrom + "\r\n");
			message.append("=========================================================\r\n");
			message.append("URL requested: " + urlError + "\r\n");
			message.append("Error Message: \r\n" + errorMessage + "\r\n");
			message.append("Question / Concern: \r\n" + question + "\r\n");

			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", userId);
			newIncomingRequest.put("SendQueue_subject", subject);
			newIncomingRequest.put("SendQueue_messagetext", message.toString());
			newIncomingRequest.put("SendQueue_sendfrom", sendFrom);
			newIncomingRequest.put("SendQueue_sendfromtype", "E");
			newIncomingRequest.put("SendQueue_sendto", sendTo);
			newIncomingRequest.put("SendQueue_sendtotype", "E");
			newIncomingRequest.put("SendQueue_action", "EN");

			process.executeProcess(newIncomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			} else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		} catch (Exception exception)
		{

			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
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