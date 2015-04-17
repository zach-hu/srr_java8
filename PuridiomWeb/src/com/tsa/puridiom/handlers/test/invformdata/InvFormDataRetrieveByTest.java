package com.tsa.puridiom.handlers.test.invformdata;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormDataRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormDataRetrieveByHandler invformdataretrievebyhandler = new InvFormDataRetrieveByHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformdataretrievebyhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}