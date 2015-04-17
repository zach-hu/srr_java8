package com.tsa.puridiom.vendorquestion.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorquestion.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorQuestionRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorQuestionRetrieveBy test = new VendorQuestionRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorQuestion_icRfqHeader", "123456");
			
			System.out.println("Database Status: " + dbs.getStatus());
		
			List vendorQuestionList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < vendorQuestionList.size(); i++)
			{
				VendorQuestion vendorQuestion = (VendorQuestion) vendorQuestionList.get(i);
				System.out.println("VendorQuestion: " + vendorQuestionList.toString());
			}
		
			System.out.println("VendorQuestionRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}