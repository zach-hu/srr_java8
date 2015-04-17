package com.tsa.puridiom.handlers.test.po;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("po-create.xml");
			Map incomingRequest = new HashMap();
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}