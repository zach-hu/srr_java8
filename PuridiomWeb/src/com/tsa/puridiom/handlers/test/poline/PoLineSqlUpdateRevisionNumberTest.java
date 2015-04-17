package com.tsa.puridiom.handlers.test.poline;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.handlers.PoLineSqlUpdateRevisionNumberHandler;

public class PoLineSqlUpdateRevisionNumberTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    PoLineSqlUpdateRevisionNumberHandler test = new PoLineSqlUpdateRevisionNumberHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RRAMOS");
			incomingRequest.put("PoLine_icPoLine", "3298349500040");
			incomingRequest.put("PoHeader_revisionNumber", "5");
			incomingRequest.put("lineUpdated", "true");
			
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			//system.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				//system.out.println("SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}