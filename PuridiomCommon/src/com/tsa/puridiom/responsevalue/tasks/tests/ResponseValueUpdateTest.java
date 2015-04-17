package com.tsa.puridiom.responsevalue.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.responsevalue.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ResponseValueUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ResponseValueUpdate test = new ResponseValueUpdate();
			Map incomingRequest = new HashMap();
		
			ResponseValue responseValue = new ResponseValue();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("responseValue", responseValue);
		
			responseValue = (ResponseValue) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("ResponseValueUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("ResponseValueUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}