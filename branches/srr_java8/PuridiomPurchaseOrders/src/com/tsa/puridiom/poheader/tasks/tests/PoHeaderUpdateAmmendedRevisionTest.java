package com.tsa.puridiom.poheader.tasks.tests;

import com.tsa.puridiom.poheader.tasks.PoHeaderUpdateAmmendedRevision;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class PoHeaderUpdateAmmendedRevisionTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	organizationId = "PURIDIOM";
		    String	userId = "KELLI";
		    DBSession dbsession = new DBSession(organizationId);
		    PoHeaderUpdateAmmendedRevision test = new PoHeaderUpdateAmmendedRevision(); 
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