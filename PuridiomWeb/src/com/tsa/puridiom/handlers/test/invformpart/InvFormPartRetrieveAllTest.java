package com.tsa.puridiom.handlers.test.invformpart;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvFormPartRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvFormPartRetrieveAllHandler invformpartretrieveallhandler = new InvFormPartRetrieveAllHandler();
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			invformpartretrieveallhandler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}