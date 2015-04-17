package com.tsa.puridiom.handlers.test.stdtable;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class StdTableRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			StdTableRetrieveByHandler stdtableretrievebyhandler = new StdTableRetrieveByHandler();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "TESTUSER");
			incomingRequest.put("StdTable_tableType", "SHP");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
		
			stdtableretrievebyhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			List list = (List) incomingRequest.get("stdTableList");
			for (int i=0; i < list.size(); i++)
			{
				StdTable stdTable = (StdTable) list.get(i);
				System.out.println((i+1) + ") " + stdTable.getComp_id().getTableKey() + " - " + stdTable.getDescription());
			}
			
			System.out.println("StdTableRetrieveByHandlerTest - " + incomingRequest.get("viewPage"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}