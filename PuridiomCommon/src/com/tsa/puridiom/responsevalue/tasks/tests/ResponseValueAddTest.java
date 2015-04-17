package com.tsa.puridiom.responsevalue.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.responsevalue.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ResponseValueAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ResponseValueAdd test = new ResponseValueAdd();
			Map incomingRequest = new HashMap();
		
			ResponseValue responseValue = new ResponseValue();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("responseValue", responseValue);
		
			responseValue = (ResponseValue) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("ResponseValueAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("ResponseValueAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}