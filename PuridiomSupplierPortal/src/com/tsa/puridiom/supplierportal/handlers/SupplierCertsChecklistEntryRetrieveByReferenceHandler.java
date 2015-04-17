package com.tsa.puridiom.supplierportal.handlers;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import java.util.*;

public class SupplierCertsChecklistEntryRetrieveByReferenceHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("suppliercerts-checklistentry-retrieve-by-referencetype.xml");
			incomingRequest.put("ChecklistEntry_referenceType", "VCT");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		}
		finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}