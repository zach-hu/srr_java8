package com.tsa.puridiom.handlers.test.invformpart;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormPartDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormPartDeleteHandler invformpartdeletehandler = new InvFormPartDeleteHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformpartdeletehandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}