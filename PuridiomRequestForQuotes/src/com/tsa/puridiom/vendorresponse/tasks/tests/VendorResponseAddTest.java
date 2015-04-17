package com.tsa.puridiom.vendorresponse.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorresponse.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class VendorResponseAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorResponseAdd test = new VendorResponseAdd();
			Map incomingRequest = new HashMap();
			
			VendorResponse vendorResponse = new VendorResponse();
			VendorResponsePK pk = new VendorResponsePK();
			pk.setIcRfqHeader(new BigDecimal("756864200000"));
			pk.setIcQuestion(new BigDecimal("781911900001"));
			pk.setVendorId("OFFICEMAX");
			vendorResponse.setComp_id(pk);
			vendorResponse.setResponse("Y");
			vendorResponse.setTextResponse("");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("vendorResponse", vendorResponse);

			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
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