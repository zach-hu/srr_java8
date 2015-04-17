package com.tsa.puridiom.property.tasks.test;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PropertyUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("property-update.xml");
		Map incomingRequest = new HashMap();
		
		String	sections[] = {"TESTOPTIONS", "TESTOPTIONS", "TESTS"};
		String	properties[] = {"TEST1", "TEST2", "TEST3"};
		String	values[] = {"1", "2", "3"};

		incomingRequest.put("Property_section", sections);
		incomingRequest.put("Property_property", properties);
		incomingRequest.put("Property_value", values);

		process.executeProcess(incomingRequest);
		
		System.out.println(incomingRequest);
	}
}