package com.tsa.puridiom.poline.tasks.tests;

import com.tsa.puridiom.poline.tasks.PoLineUpdateAmmendedRevision;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class PoLineUpdateAmmendedRevisionTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	organizationId = "PURIDIOM";
		    String	userId = "KELLI";
		    DBSession dbsession = new DBSession(organizationId);
		    PoLineUpdateAmmendedRevision test = new PoLineUpdateAmmendedRevision(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbsession);
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", userId);
			incomingRequest.put("PoHeader_icPoHeader", "4297506700011");
			
			dbsession.startTransaction();
			
			test.executeTask(incomingRequest);
			
			if (dbsession.getStatus() == Status.SUCCEEDED) {
			    dbsession.commit();
			}
						
			System.out.println("Database Status: " + dbsession.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}