package com.tsa.puridiom.handlers.test.invformdata;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormDataDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormDataDeleteHandler invformdatadeletehandler = new InvFormDataDeleteHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformdatadeletehandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}