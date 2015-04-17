/**
 * 
 * Created on Jan 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.tests.PoLineCreateTest.java
 * 
 */
package com.tsa.puridiom.poline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.util.*;


public class PoLineCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("poline-create.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("PoHeader_icPoHeader", "1179510800000");
			incomingRequest.put("createAction", "SAVE");
			
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error(e, e.toString());
			e.printStackTrace();
		}
	}

}