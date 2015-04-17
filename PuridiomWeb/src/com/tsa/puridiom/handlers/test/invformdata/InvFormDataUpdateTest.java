package com.tsa.puridiom.handlers.test.invformdata;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormDataUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormDataUpdateHandler invformdataupdatehandler = new InvFormDataUpdateHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformdataupdatehandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}