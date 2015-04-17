package com.tsa.puridiom.doccomment.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DocCommentSaveasListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("doccomment-saveas.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("DocComment_icHeader", "835021300000");
			incomingRequest.put("DocComment_icLine", "0");
			incomingRequest.put("newDocComment_icHeader", "999999990");
			incomingRequest.put("newDocComment_icLine", "0");
						
			process.executeProcess(incomingRequest);
			
			System.out.println("RESULT: " + process.getStatus());
			System.out.println("PARAMETERS: " + incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}