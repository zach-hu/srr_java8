package com.tsa.puridiom.handlers.test.accounts;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AccountRetrieveByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("account-retrieve-by-line.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("Account_icHeader","835850900000") ;
			incomingRequest.put("Account_icLine","0") ;
			incomingRequest.put("Account_accountType","RQH") ;
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}