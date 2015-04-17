package com.tsa.puridiom.rfq.tasks.tests;

import com.tsa.puridiom.rfq.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class RfqValidateActiveFiscalYearTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqValidateActiveFiscalYear getLineNumber = new RfqValidateActiveFiscalYear();
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("dbsession", new DBSession("puridiom"));
			incomingRequest.put("RfqHeader_fiscalYear","2003") ;
			
			Object obj = getLineNumber.executeTask(incomingRequest);
			
			System.out.println("Object: " + obj.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}