/*
 * Created on Dec 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.handlers.test.jasperreports;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.HqlQueryHandler;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HqlQueryTest
{
    public void test()
    {
        try
		{
            System.out.println("start test");
		    HqlQueryHandler testHandler= new HqlQueryHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RRAMOS");
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			testHandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) 
			{
				System.out.println("Test - SUCCESS");
			}
			else
			{
			    System.out.println("Test- Failure");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }

    public static void main(String[] args)
    {
        HqlQueryTest testReport = new HqlQueryTest();
        testReport.test();
    }
}
