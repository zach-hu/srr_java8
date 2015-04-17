package com.tsa.puridiom.handlers.test.invformpart;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormPartRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormPartRetrieveByHandler invformpartretrievebyhandler = new InvFormPartRetrieveByHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformpartretrievebyhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}