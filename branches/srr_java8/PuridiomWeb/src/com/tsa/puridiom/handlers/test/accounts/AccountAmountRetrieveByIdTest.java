package com.tsa.puridiom.handlers.test.accounts;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class AccountAmountRetrieveByIdTest
{
	public AccountAmountRetrieveByIdTest(){}

	public void funcioncall()
	{
	try
		{
			AccountAllocAmountRetrieveHandler test = new AccountAllocAmountRetrieveHandler();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "TEST");
			incomingRequest.put("userId", "hiltontest");
			incomingRequest.put("icHeader", "4930180800007");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("Account - SUCCESS");
			}
		
			System.out.println("Tarea terminada");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
public static void  main (String[] args) throws Exception
{
	AccountAmountRetrieveByIdTest abs =new AccountAmountRetrieveByIdTest();
	abs.funcioncall();
}

}