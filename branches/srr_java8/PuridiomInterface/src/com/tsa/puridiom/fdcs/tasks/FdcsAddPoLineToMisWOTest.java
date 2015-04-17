package com.tsa.puridiom.fdcs.tasks;

import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class FdcsAddPoLineToMisWOTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			FdcsAddPoLineToMisWO test = new FdcsAddPoLineToMisWO() ;
			Map incomingRequest = new HashMap() ;

			incomingRequest.put("organizationId", "TTR09P");
			incomingRequest.put("userId", "SYSADM");

//		      <param0><_PI_CHGCD>#95</_PI_CHGCD><_PI_DESC>labor
//		      test</_PI_DESC><_PI_OQTY>1</_PI_OQTY><_PI_PONUMB>10-000049</_PI_PONUMB><_PI_POSEQN>1</_PI_POSEQN><_PI_SPLRNM>3T
//		      TRANSPORT
//		      INC</_PI_SPLRNM><_PI_UNCS>30</_PI_UNCS><_PI_WONO>PA34389</_PI_WONO><_PI_WOOPNO></_PI_WOOPNO><_PI_WOSGNO>01</_PI_WOSGNO></param0>

			incomingRequest.put("poNumber","10-450002") ;
			incomingRequest.put("workNumber","PA34389");
			incomingRequest.put("opNumber", "") ;
			incomingRequest.put("segNumber","01") ;
			incomingRequest.put("custNumber","");
			incomingRequest.put("chargeCode","95");
			incomingRequest.put("vendorName", "3T TRANSPORT INC");

			incomingRequest.put("lineNumber", new BigDecimal(1)) ;
			incomingRequest.put("dbsKey","");
			incomingRequest.put("description","labor");
			incomingRequest.put("quantity", new BigDecimal(1));
			incomingRequest.put("cost", new BigDecimal(30.79));


			test.executeTask(incomingRequest) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}