package com.tsa.puridiom.requisitionline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineAccountUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionline-account-update.xml");
			Map incomingRequest = new HashMap();
			String	allocMethod[] = {"P","P","P"};
			String	allocAmount[] = {"10.22","20","11.11"} ;
			String	allocPercent[] = {"33","34","33"} ;
			String	fld1[] = {"A1","B1","C1"} ;
			String	fld2[] = {"A2","B2","C2"} ;
			String	fld3[] = {"A3","B3","C3"} ;
			
			incomingRequest.put("Account_icHeader", "549101601000");
			incomingRequest.put("Account_icLine", "713135500000");
			incomingRequest.put("Account_allocMethod",allocMethod) ;
			incomingRequest.put("Account_allocAmount",allocAmount) ;
			incomingRequest.put("Account_allocPercent",allocPercent) ;
			incomingRequest.put("Account_fld1",fld1) ;
			incomingRequest.put("Account_fld2",fld2) ;
			incomingRequest.put("Account_fld3",fld3) ;
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}