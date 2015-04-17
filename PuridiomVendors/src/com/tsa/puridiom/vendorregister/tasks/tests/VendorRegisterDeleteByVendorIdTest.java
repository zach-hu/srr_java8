package com.tsa.puridiom.vendorregister.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorregister.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorRegisterDeleteByVendorIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorRegisterDeleteByVendorId test = new VendorRegisterDeleteByVendorId();
			Map incomingRequest = new HashMap();
		
			VendorRegister vendorRegister = new VendorRegister();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("vendorRegister", vendorRegister);
		
			vendorRegister = (VendorRegister) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorRegisterDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("VendorRegisterDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}