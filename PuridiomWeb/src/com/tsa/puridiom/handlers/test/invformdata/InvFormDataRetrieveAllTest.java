package com.tsa.puridiom.handlers.test.invformdata;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormDataRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormDataRetrieveAllHandler invformdataretrieveallhandler = new InvFormDataRetrieveAllHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformdataretrieveallhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}