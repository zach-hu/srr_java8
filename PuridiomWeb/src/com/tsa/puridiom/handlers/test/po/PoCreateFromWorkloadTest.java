/**
 * 
 * Created on Jan 27, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.tests.PoCreateFromRequisitionTest.java
 * 
 */
package com.tsa.puridiom.handlers.test.po;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class PoCreateFromWorkloadTest
{
	public static void main(String[] args)
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("po-create-workload.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("RequisitionLine_icReqLine", "3114583300027");

			process.executeProcess(incomingRequest);
			System.out.println("status: " + process.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error("PoCreateFromReqTest", e.toString());
		}
	}
}
