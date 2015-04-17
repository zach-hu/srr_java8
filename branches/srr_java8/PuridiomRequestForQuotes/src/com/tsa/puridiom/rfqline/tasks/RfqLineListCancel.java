package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqLineListCancel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
		String organizationId = (String) incomingRequest.get("organizationId");
		String userId = (String) incomingRequest.get("userId");

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("rfqline-list-cancel.xml");

		Map newIncomingRequest = new HashMap();
		newIncomingRequest.put("organizationId", organizationId);
		newIncomingRequest.put("userId", userId);

		for (int x = 0; x < requisitionLineList.size(); x++) {
			RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(x);
			newIncomingRequest.put("RfqLine_icReqLine",requisitionLine.getIcReqLine());
			newIncomingRequest.put("SendQueue_messagetext", incomingRequest.get("SendQueue_messagetext"));
			process.executeProcess(newIncomingRequest);
			incomingRequest.put("SendQueue_messagetext", newIncomingRequest.get("SendQueue_messagetext"));
		}
		this.status = Status.SUCCEEDED;
		return null ;
	}

}