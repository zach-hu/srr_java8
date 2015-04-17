package com.tsa.puridiom.handlers.test.invformpart;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormPartAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormPartAddHandler invformpartaddhandler = new InvFormPartAddHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformpartaddhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}