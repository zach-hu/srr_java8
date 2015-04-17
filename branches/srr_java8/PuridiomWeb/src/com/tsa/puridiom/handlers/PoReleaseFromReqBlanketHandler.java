package com.tsa.puridiom.handlers;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class PoReleaseFromReqBlanketHandler implements IHandler
{
    public Map  handleRequest (Map incomingRequest) throws Exception
    {
        try
        {
            String orgId = (String)incomingRequest.get("organizationId");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("po-create-release-from-req-blanket.xml");
            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
                List  errorMsg = (List) incomingRequest.get("errorList");
                /*if (errorMsg != null && errorMsg.size() > 0)
                {
                    incomingRequest.put("viewPage", "/orders/po_display_errors.jsp");
                }
                else
                {
                    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
                }*/
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
            incomingRequest.put("exception", exception);
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