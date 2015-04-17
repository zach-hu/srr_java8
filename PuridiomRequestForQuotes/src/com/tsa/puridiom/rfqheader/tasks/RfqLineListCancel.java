package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqLineListCancel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		incomingRequest.put("rfqHeaderIdList",new ArrayList());

		List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
		String organizationId = (String) incomingRequest.get("organizationId");
		String userId = (String) incomingRequest.get("userId");

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("rfqheader-list-cancel.xml");

		Map newIncomingRequest = new HashMap();
		newIncomingRequest.put("organizationId", organizationId);
		newIncomingRequest.put("userId", userId);
		newIncomingRequest.put("rfqHeaderIdList", incomingRequest.get("rfqHeaderIdList"));

		for (int x = 0; x < requisitionLineList.size(); x++) {
			RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(x);
			newIncomingRequest.put("RfqLine_icReqLine", requisitionLine.getIcReqLine());
			process.executeProcess(newIncomingRequest);
		}
		this.status = Status.SUCCEEDED;
		return null ;
	}

}