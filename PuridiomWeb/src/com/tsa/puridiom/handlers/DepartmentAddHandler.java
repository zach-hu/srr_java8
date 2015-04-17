package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class DepartmentAddHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("department-add.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("duplicateDepartmentErrorMsg");
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("duplicateDepartmentFailurePage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				    /*
				     *  kathleen added the following on 06-13-05
				     *  so that after a new department is successfully added
				     *  it then calls the department buyer update
				     */
				    process = processLoader.loadProcess("departmentbuyer-update-by-dept.xml");
					process.executeProcess(incomingRequest);
					if (process.getStatus() == Status.SUCCEEDED)
					{
						incomingRequest.put("viewPage", (String) incomingRequest.get("successPage"));

						/*
					     *  kathleen added the following on 06-13-05
					     *  so that after a new department buyer is successfully added
					     *  the user gets returned to the department browse page
					     */
					    process = processLoader.loadProcess("browse-retrieve.xml");
						process.executeProcess(incomingRequest);
					}
					else
					{
						incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
					}
				}
			}
			else
			{
				incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
			throw exception;
		}
		finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}