package com.tsa.puridiom.handlers.test.invformdata;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormDataRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormDataRetrieveByIdHandler invformdataretrievebyidhandler = new InvFormDataRetrieveByIdHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformdataretrievebyidhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}