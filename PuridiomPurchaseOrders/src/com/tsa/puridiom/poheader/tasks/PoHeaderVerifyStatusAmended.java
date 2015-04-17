package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;

import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class PoHeaderVerifyStatusAmended extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String verifyStatusAmmended = "N";
			if (poHeader != null && poHeader.getStatus().equalsIgnoreCase(DocumentStatus.PO_AMENDED))
			{
				verifyStatusAmmended = "Y";
			}
			incomingRequest.put("verifyStatusAmmended", verifyStatusAmmended);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}