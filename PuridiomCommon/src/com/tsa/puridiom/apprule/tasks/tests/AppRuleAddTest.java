package com.tsa.puridiom.apprule.tasks.tests;



import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AppRuleAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("approval-rule-add.xml");
			Map incomingRequest = new HashMap();
			
			// TODO add your parameters to incomingRequest here
			incomingRequest.put("AppRule_userId", "JEFF");
			incomingRequest.put("AppRule_amount", "10000");
			incomingRequest.put("AppRule_udf1Code", "*");
			incomingRequest.put("AppRule_udf2Code", "HARDWARE");
			incomingRequest.put("AppRule_udf3Code", " ");
			incomingRequest.put("AppRule_udf4Code", " ");
			incomingRequest.put("AppRule_udf5Code", " ");
			incomingRequest.put("AppRule_udf6Code", " ");
			incomingRequest.put("AppRule_udf7Code", " ");
			incomingRequest.put("AppRule_udf8Code", " ");
			incomingRequest.put("AppRule_udf9Code", " ");
			incomingRequest.put("AppRule_udf10Code", " ");
			
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}