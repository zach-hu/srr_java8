package com.tsa.puridiom.reviewfinalize.tasks.tests;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;
import java.math.BigDecimal;

public class ReviewFinalizeAddTestProcessTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("reviewfinalize-add.xml");
			Map incomingRequest = new HashMap();

			ReviewFinalize reviewfinalize=new ReviewFinalize();
			reviewfinalize.setIcHeader(new BigDecimal("2"));
			reviewfinalize.setStep("15");
			reviewfinalize.setOwner("Kate");
			reviewfinalize.setRevisedBy("Carl");
			reviewfinalize.setCompleted("Y");


			// TODO add your parameters to incomingRequest here
			incomingRequest.put("reviewFinalize", reviewfinalize);
			process.executeProcess(incomingRequest);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}