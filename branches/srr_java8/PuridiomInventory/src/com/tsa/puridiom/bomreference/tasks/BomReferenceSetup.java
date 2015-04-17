package com.tsa.puridiom.bomreference.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BomReferenceSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BomReference_icReference", "0");
			incomingRequest.put("BomReference_parentItem", "");
			incomingRequest.put("BomReference_componentItem", "");
			incomingRequest.put("BomReference_icComponent", "0");
			incomingRequest.put("BomReference_referenceId", "");
			incomingRequest.put("BomReference_refNo", "0");
			incomingRequest.put("BomReference_qty", "0");
			incomingRequest.put("BomReference_methodId", "");
			incomingRequest.put("BomReference_stageId", "");
			incomingRequest.put("BomReference_icRouting", "0");
			incomingRequest.put("BomReference_dateEntered", Dates.today("", ""));
			incomingRequest.put("BomReference_owner", "");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}