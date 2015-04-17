package com.tsa.puridiom.taxcode.tasks.tests;
import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class TaxCodeAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("taxcode-add.xml");
			Map incomingRequest = new HashMap();

			incomingRequest.put("TaxCode_taxCode", "PA");
			incomingRequest.put("TaxCode_description", "Pennsylvania Tax Code");
			incomingRequest.put("TaxCode_taxRate", "6");
			incomingRequest.put("TaxCode_dateEntered", Dates.today("", ""));
			incomingRequest.put("TaxCode_dateExpires", Dates.today("", ""));
			incomingRequest.put("TaxCode_owner", "");
			incomingRequest.put("TaxCode_status", "");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error(TaxCodeAddTest.class , e.toString());
		}
	}

}