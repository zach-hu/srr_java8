package com.tsa.puridiom.rfqheader.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqheader.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqHeaderUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RfqHeaderUpdate test = new RfqHeaderUpdate();
			Map incomingRequest = new HashMap();
			
			RfqHeader rfqHeader = new RfqHeader();
			rfqHeader.setIcRfqHeader(new BigDecimal("602703700000"));
			rfqHeader.setRfqNumber("9999-999999");
			rfqHeader.setRfqDescription("UPDATE TEST");
			rfqHeader.setVendorAwarded("");
			rfqHeader.setUdf1Code("TESTU1");
			rfqHeader.setUdf2Code("TESTU2");
			rfqHeader.setUdf3Code("TESTU3");
			rfqHeader.setUdf4Code("TESTU4");
			rfqHeader.setUdf5Code("TESTU5");
			rfqHeader.setUdf6Code("TESTU6");
			rfqHeader.setUdf7Code("TESTU7");
			rfqHeader.setUdf8Code("TESTU8");
			rfqHeader.setUdf9Code("TESTU9");
			rfqHeader.setUdf10Code("TESTU10");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqHeader", rfqHeader);
			
			test.executeTask(incomingRequest);
			
			if (dbs.getStatus() == Status.SUCCEEDED)
			{
				dbs.commit();
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}