package com.tsa.puridiom.requisitionline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionline-create.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("createAction","SAVE") ;
			incomingRequest.put("RequisitionHeader_icReqHeader","549101601000") ;
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}