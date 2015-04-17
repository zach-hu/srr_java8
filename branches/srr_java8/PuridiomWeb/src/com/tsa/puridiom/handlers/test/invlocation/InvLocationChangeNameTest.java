package com.tsa.puridiom.handlers.test.invlocation;
import java.util.*;
import com.tsa.puridiom.handlers.*;

public class InvLocationChangeNameTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvLocationChangeNameHandler handler = new InvLocationChangeNameHandler();
			Map incomingRequest = new HashMap();
			incomingRequest.put("newLocation", "60");
			incomingRequest.put("oldLocation", "61");
			
			handler.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}