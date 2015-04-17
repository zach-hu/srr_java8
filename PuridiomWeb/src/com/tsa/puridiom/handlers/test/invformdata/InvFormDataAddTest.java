package com.tsa.puridiom.handlers.test.invformdata;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormDataAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormDataAddHandler invformdataaddhandler = new InvFormDataAddHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformdataaddhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}