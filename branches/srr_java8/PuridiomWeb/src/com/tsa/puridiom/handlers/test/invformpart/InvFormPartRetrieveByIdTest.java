package com.tsa.puridiom.handlers.test.invformpart;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormPartRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormPartRetrieveByIdHandler invformpartretrievebyidhandler = new InvFormPartRetrieveByIdHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformpartretrievebyidhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}