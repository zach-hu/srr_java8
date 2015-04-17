package com.tsa.puridiom.vendorcommrel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorcommrel.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorCommRelAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorCommRelAdd test = new VendorCommRelAdd();
			Map incomingRequest = new HashMap();

			VendorCommRel vendorCommRel = new VendorCommRel();
			VendorCommRelPK pk = new VendorCommRelPK();
			pk.setVendorId("TULA");
			pk.setCommodityCode("JAVATEST");
			vendorCommRel.setComp_id(pk);
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("vendorCommRel", vendorCommRel);

			vendorCommRel = (VendorCommRel) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("VendorCommRelAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}