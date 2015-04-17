package com.tsa.puridiom.reviewfinalize.tasks.tests;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReviewFinalizeDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("reviewfinalize-delete-by-id.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("icHeader","13");
			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);

//			ReviewFinalize reviewfinalize=(ReviewFinalize)incomingRequest.get("reviewfinalizebyid");
//			System.out.println("icHeader " + reviewfinalize.getIcHeader());
//			System.out.println("owner " + reviewfinalize.getOwner());
//			System.out.println("step " + reviewfinalize.getStep());
//			System.out.println("revisedBy " + reviewfinalize.getRevisedBy());
//			System.out.println("completed " + reviewfinalize.getCompleted());


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}