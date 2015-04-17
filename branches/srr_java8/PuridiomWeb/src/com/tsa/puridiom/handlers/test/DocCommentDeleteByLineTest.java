package com.tsa.puridiom.handlers.test;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class DocCommentDeleteByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DocCommentDeleteByLineHandler test = new DocCommentDeleteByLineHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId","PURIDIOM");;
			incomingRequest.put("userId","KELLI");	
			incomingRequest.put("DocComment_icHeader", "756864200000");
			incomingRequest.put("DocComment_icLine", "756864200022");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			System.out.println("DocCommentDeleteByLineHandler - " + viewPage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}