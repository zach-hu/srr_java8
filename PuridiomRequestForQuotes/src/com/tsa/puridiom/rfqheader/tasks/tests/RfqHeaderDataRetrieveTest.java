package com.tsa.puridiom.rfqheader.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqHeaderDataRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("rfqheader-data-retrieve.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("RfqHeader_icRfqHeader", "602703700000");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
			
			if (incomingRequest.containsKey("rfqHeader"))
			{
				RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
				System.out.println(rfqHeader.toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}