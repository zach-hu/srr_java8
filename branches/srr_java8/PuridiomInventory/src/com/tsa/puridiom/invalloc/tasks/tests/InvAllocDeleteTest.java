package com.tsa.puridiom.invalloc.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvAllocDeleteTest
{
	public static void  test (String[] args) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("invalloc-delete.xml");
		Map incomingRequest = new HashMap();
		// TODO add your parameters to incomingRequest here
		process.executeProcess(incomingRequest);
		System.out.println(incomingRequest);
	}

}