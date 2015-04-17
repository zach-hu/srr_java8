package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsagate.foundation.processengine.*;

import java.util.*;

/**
 * @author Kelli Knisely
 */
public class VendorLogoffHandler implements IHandler
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("vendor-logoff.xml");

			process.executeProcess(incomingRequest);

			String errorMsg = (String) incomingRequest.get("errorMsg");
			if (errorMsg != null && errorMsg.trim().length() > 0)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
//			throw exception;
		}
		finally
		{
			return incomingRequest;
		}
	}
}
