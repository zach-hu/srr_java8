package com.tsa.puridiom.vendorinsurance.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorinsurance.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorInsuranceUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorInsuranceUpdate test = new VendorInsuranceUpdate();
			Map incomingRequest = new HashMap();
		
			VendorInsurance vendorInsurance = new VendorInsurance();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("vendorInsurance", vendorInsurance);
		
			vendorInsurance = (VendorInsurance) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorInsuranceUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("VendorInsuranceUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}