package com.tsa.puridiom.vendorcommrel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorcommrel.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorCommRelDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorCommRelDeleteById test = new VendorCommRelDeleteById();
			Map incomingRequest = new HashMap();

			VendorCommRel vendorCommRel = new VendorCommRel();
			VendorCommRelPK pk = new VendorCommRelPK();
			pk.setVendorId("TULA");
			pk.setCommodityCode("OFFICE");
			vendorCommRel.setComp_id(pk);
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("vendorCommRel", vendorCommRel);

			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("VendorCommRelDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}