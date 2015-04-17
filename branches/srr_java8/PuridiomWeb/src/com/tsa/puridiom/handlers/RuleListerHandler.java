/*
 * Created on Jul 22, 2003
 */
package com.tsa.puridiom.handlers;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

/**
 * @author Administrator
 */
public class RuleListerHandler implements IHandler
{
    public Map handleRequest(Map incomingRequest) throws Exception
    {
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("rule-lister.xml");
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
