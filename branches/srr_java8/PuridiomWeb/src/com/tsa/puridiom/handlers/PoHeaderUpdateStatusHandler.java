package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsa.puridiom.po.exceptions.PoCancelException;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class PoHeaderUpdateStatusHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
    {
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("po-change-status.xml");
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
        catch (PoCancelException e)
        {
            incomingRequest.put("errorMsg", e.getMessage());
            incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
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






