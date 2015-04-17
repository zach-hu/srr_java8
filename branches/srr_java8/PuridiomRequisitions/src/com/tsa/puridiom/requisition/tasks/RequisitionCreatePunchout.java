package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.punchoutcatalog.*;
import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.Catalog;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.*;

public class RequisitionCreatePunchout extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	requestId = (String) incomingRequest.get("puridiomRequestId");

			Map requestParameters = PunchOutRequestManager.getRequestParameters(requestId);

			Iterator iterator = requestParameters.keySet().iterator();
			while (iterator.hasNext()) {
			    String	key = (String) iterator.next();
			    if (!incomingRequest.containsKey(key)) {
			        incomingRequest.put(key, requestParameters.get(key));
			    }
			}

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("requisition-create-punchout-form.xml");

			process.executeProcess(requestParameters);

			String	icHeader = (String) requestParameters.get("RequisitionHeader_icReqHeader");
			incomingRequest.put("RequisitionHeader_icReqHeader", icHeader);

			PunchOutRequestManager.setRequestParameters(requestId, requestParameters);

			this.status = process.getStatus();
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}