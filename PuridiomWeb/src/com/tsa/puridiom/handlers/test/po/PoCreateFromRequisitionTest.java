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

public class PoCreateFromRequisitionTest
{
	public static void main(String[] args)
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("po-create-from-req.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("RequisitionHeader_icReqHeader", "478808700000");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error("PoCreateFromReqTest", e.toString());
		}
	}
}
