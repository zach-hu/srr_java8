package com.tsa.puridiom.coda.test;

import com.tsa.puridiom.coda.tasks.BrowseRetrieveElementFinder;
import com.tsa.puridiom.coda.tasks.ElementFinderRetrieve;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class CodaSupplierSyncTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("vendor-retrieve-all.xml");
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			process.executeProcess(incomingRequest);
			int count = 0;
			List vendorList = (List) incomingRequest.get("retrieve-allVendor") ;

			for (int row = 0; row < vendorList.size(); row++) {
				Vendor v = (Vendor) vendorList.get(row) ;
				if (v.getApReference() == null) {

					System.out.println("Puridiom Vendor: " + v.getVendorId() + " - " + v.getVendorName()) ;

					// Find Vendor by Statutory Code
					BrowseRetrieveElementFinder test = new BrowseRetrieveElementFinder() ;
					Map newRequest = new HashMap() ;

					newRequest.put("organizationId", "TTR09P");
					newRequest.put("userId", "SYSADM");
					newRequest.put("codaLevel","2");
					newRequest.put("codeFilter","V");
					newRequest.put("nameFilter","");

					test.executeTask(newRequest) ;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}