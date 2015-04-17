/*
 * Created on Nov 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.tests.apprulesext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AppRulesExtTest
{

    public static void main(String[] args)
    {
        try
		{
            System.out.println("start");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("ext-approvals.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RRAMOS");
			incomingRequest.put("RequisitionLine_icReqHeader", "3796615300002");
			incomingRequest.put("RequisitionHeader_icReqHeader", "3796615300002");
			process.executeProcess(incomingRequest);
			
			if (process.getStatus() == Status.SUCCEEDED)
			{
			    List routingList = (List)incomingRequest.get("routingList");
			    System.out.println("approver list: " + routingList);
				System.out.println("success");
			}
			else
			{
				System.out.println("failed");
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
    }
}
