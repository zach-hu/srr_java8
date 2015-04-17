package com.tsa.puridiom.vendorinsurance.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorinsurance.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorInsuranceAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorInsuranceGetNewContNumber getNumberTask = new VendorInsuranceGetNewContNumber();
			VendorInsuranceAdd test = new VendorInsuranceAdd();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			
			String contNumber = (String) getNumberTask.executeTask(incomingRequest);
						
			VendorInsurance vendorInsurance = new VendorInsurance();
		
			vendorInsurance.setContNumber(contNumber);
			vendorInsurance.setVendorId("STAPELS");
			vendorInsurance.setCertStatus1("P");
			vendorInsurance.setCertStatus2("P");
			vendorInsurance.setCertStatus3("P");
			vendorInsurance.setCertStatus4("A");
			vendorInsurance.setCertStatus5("A");
			vendorInsurance.setCertUdf1("UDF1");
			vendorInsurance.setCertUdf2("UDF2");
			vendorInsurance.setCertUdf3("UDF3");
			vendorInsurance.setCertUdf4("UDF4");
			vendorInsurance.setCertUdf5("UDF5");
			
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("vendorInsurance", vendorInsurance);
		
			vendorInsurance = (VendorInsurance) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorInsuranceAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("VendorInsuranceAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}