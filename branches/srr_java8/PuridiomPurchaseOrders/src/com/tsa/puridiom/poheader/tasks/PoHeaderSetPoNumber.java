package com.tsa.puridiom.poheader.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderSetPoNumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("PoHeader_icPoHeader", (String) incomingRequest.get("PoHeader_icPoHeader"));
			newIncomingRequest.put("PoHeader_poType", (String) incomingRequest.get("PoHeader_poType"));
			newIncomingRequest.put("PoHeader_fiscalYear", (String) incomingRequest.get("PoHeader_fiscalYear"));
			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", (String) incomingRequest.get("userId"));
			newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));

			String	icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader") ;
			String  rfqNumber =(String) incomingRequest.get("RfqHeader_rfqNumber") ;
			String	icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader") ;

			newIncomingRequest.put("RfqHeader_icRfqHeader", icRfqHeader);
			newIncomingRequest.put("RfqHeader_rfqNumber", rfqNumber);
			newIncomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);

			newIncomingRequest.put("PoHeader_prefix", (String) incomingRequest.get("PoHeader_prefix"));

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("po-get-po-number.xml");
			process.executeProcess(newIncomingRequest);
			incomingRequest.put("PoHeader_poNumber", (String) newIncomingRequest.get("PoHeader_poNumber"));
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
