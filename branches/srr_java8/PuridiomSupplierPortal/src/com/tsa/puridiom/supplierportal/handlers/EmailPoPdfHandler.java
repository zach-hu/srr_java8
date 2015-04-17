package com.tsa.puridiom.supplierportal.handlers;

import java.util.Map;

import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class EmailPoPdfHandler implements IHandler
{
    public Map  handleRequest (Map incomingRequest) throws Exception
    {
        try
        {
        	String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("po-email-pdf-setup.xml");
            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
                incomingRequest.put("viewPage", (String) incomingRequest.get("successPage"));
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