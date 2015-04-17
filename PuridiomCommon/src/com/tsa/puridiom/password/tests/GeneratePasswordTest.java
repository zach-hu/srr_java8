package com.tsa.puridiom.password.tests;

import com.tsa.puridiom.password.tasks.*;
import java.util.*;

public class GeneratePasswordTest
{
	public static void  main (String[] args) throws Exception
	{
		GeneratePassword test = new GeneratePassword();

		Map incomingRequest = new HashMap();
		incomingRequest.put("organizationId", "PURTEST101");

		String password = (String) test.executeTask(incomingRequest);

		System.out.println("password = " + password);
	}
}