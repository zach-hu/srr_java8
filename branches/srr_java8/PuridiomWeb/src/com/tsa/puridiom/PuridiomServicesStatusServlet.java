package com.tsa.puridiom;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class PuridiomServicesStatusServlet extends HttpServlet
{
	private ServletContext context;

	private HashMap incomingRequest = new HashMap();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{

		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{

		String state, lastrun;
		StringBuffer sb = new StringBuffer();
		String oid = (String) request.getParameter("oid");

		incomingRequest.put("organizationId", oid);
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			PuridiomProcess process = processLoader.loadProcess("statuspuridiomservices-show.xml");
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				state = (String) incomingRequest.get("status");
				lastrun = (String) incomingRequest.get("lastRun");

				sb.append("<state>" + state + "</state>");
				sb.append("<lastrun>" + lastrun + "</lastrun>");

				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			} else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		} catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));

		} finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}

		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("<status>" + sb.toString() + "</status>");

		if (false)
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);

	}
}
