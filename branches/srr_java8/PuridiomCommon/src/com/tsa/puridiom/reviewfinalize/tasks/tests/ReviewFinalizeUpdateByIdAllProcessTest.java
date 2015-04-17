package com.tsa.puridiom.reviewfinalize.tasks.tests;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReviewFinalizeUpdateByIdAllProcessTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("reviewfinalize-update.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("icHeader","1");
			incomingRequest.put("owner","Este owner is updated again");
			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);

			ReviewFinalize reviewfinalize=(ReviewFinalize)incomingRequest.get("reviewfinalizebyid");
			System.out.println("icHeader " + reviewfinalize.getIcHeader());
			System.out.println("owner " + reviewfinalize.getOwner());
			System.out.println("step " + reviewfinalize.getStep());
			System.out.println("revisedBy " + reviewfinalize.getRevisedBy());
			System.out.println("completed " + reviewfinalize.getCompleted());


//			List list=(List)incomingRequest.get("reviewfinalizebyid");
//
//			for (Iterator iterator = list.iterator(); iterator.hasNext();)
//			{
//				ReviewFinalize reviewfinalize = (ReviewFinalize) iterator.next();
//				System.out.println("icHeader " + reviewfinalize.getIcHeader());
//				System.out.println("owner " + reviewfinalize.getOwner());
//				System.out.println("step " + reviewfinalize.getStep());
//				System.out.println("revisedBy " + reviewfinalize.getRevisedBy());
//				System.out.println("completed " + reviewfinalize.getCompleted());
//			}



		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}