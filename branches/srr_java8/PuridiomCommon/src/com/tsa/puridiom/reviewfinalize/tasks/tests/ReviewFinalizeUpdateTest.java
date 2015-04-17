package com.tsa.puridiom.reviewfinalize.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;
import com.tsa.puridiom.entity.ReviewFinalize;
import java.math.BigDecimal;

public class ReviewFinalizeUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("reviewfinalize-update.xml");

			ReviewFinalize reviewfinalize=new ReviewFinalize();
			reviewfinalize.setIcHeader(new BigDecimal("11"));

			reviewfinalize.setOwner("new owner");
			System.out.print("sdfs"+ reviewfinalize.getRevisedBy());

			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			incomingRequest.put("reviewfinalize", reviewfinalize);
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}