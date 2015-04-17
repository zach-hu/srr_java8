package com.tsa.puridiom.reviewfinalize.tasks.tests;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class ReviewFinalizeAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("reviewfinalize-add.xml");
			Map incomingRequest = new HashMap();

			/* Test1 Here we send a object ReviewFinalize to be inserted */
//			ReviewFinalize reviewfinalize = new ReviewFinalize();
//			reviewfinalize.setIcHeader(new BigDecimal("15"));
//			reviewfinalize.setStep("15");
//			reviewfinalize.setOwner("Owner15");
//			reviewfinalize.setRevisedBy("Revisor15");
//			reviewfinalize.setCompleted("Y");


			/* Test2 Here we send the attributes to create a new row in the table directly*/
			incomingRequest.put("icHeader","16");
			incomingRequest.put("step","16");
			incomingRequest.put("owner","Owner16");
			incomingRequest.put("revisedBy","Revisor16");
			incomingRequest.put("completed","N");

//			incomingRequest.put("reviewfinalize",reviewfinalize);
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);

			ReviewFinalize reviewfinalize2=(ReviewFinalize)incomingRequest.get("reviewfinalizeAdd");
			System.out.println("This is the object inserted");
			System.out.println("icHeader " + reviewfinalize2.getIcHeader());
			System.out.println("owner " + reviewfinalize2.getOwner());
			System.out.println("step " + reviewfinalize2.getStep());
			System.out.println("revisedBy " + reviewfinalize2.getRevisedBy());
			System.out.println("completed " + reviewfinalize2.getCompleted());



		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}