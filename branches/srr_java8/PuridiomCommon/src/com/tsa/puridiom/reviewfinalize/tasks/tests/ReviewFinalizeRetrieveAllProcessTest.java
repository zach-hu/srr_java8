package com.tsa.puridiom.reviewfinalize.tasks.tests;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReviewFinalizeRetrieveAllProcessTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("reviewfinalize-retrieve-all.xml");
			Map incomingRequest = new HashMap();

			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			//System.out.println(incomingRequest);

			List list=(List)incomingRequest.get("retrieve-allReviewFinalize");

			for (Iterator iterator = list.iterator(); iterator.hasNext();)
			{
				ReviewFinalize reviewfinalize = (ReviewFinalize) iterator.next();
				System.out.println("icHeader " + reviewfinalize.getIcHeader());
				System.out.println("owner " + reviewfinalize.getOwner());
				System.out.println("step " + reviewfinalize.getStep());
				System.out.println("revisedBy " + reviewfinalize.getRevisedBy());
				System.out.println("completed " + reviewfinalize.getCompleted());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}