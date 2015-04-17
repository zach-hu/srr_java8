package com.tsa.puridiom.invformapp.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvFormAppBackorderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invformapp-backorder.xml");
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			//system.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}