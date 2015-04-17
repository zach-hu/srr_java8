package com.tsa.puridiom.handlers.test;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class PoDeleteRevisionTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PoDeleteRevisionHandler test = new PoDeleteRevisionHandler();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId","VSE06P");;
			incomingRequest.put("PoHeader_icPoHeader", "10883952000030");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("PoDeleteHandler - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}