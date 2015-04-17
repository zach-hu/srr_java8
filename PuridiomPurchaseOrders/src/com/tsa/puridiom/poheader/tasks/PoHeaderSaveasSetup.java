package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderSaveasSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			if (poHeader == null)
			{
				throw new TsaException("The PoHeader entity was not found.");
			}
			String icHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
			String icLine = "0";
			String newIcHeader = poHeader.getIcPoHeader().toString();
			String newIcLine = "0";
			if (Utility.isEmpty(icHeader) || Utility.isEmpty(newIcHeader))
			{
				throw new TsaException("The value for PoHeader_icPoHeader and icPoHeader on the PoHeader entity cannot be empty.");
			}
			incomingRequest.put("Account_icHeader", icHeader);
			incomingRequest.put("Account_icLine", icLine);
			incomingRequest.put("DocComment_icHeader", icHeader);
			incomingRequest.put("DocComment_icLine", icLine);
			incomingRequest.put("DocAttachment_icHeader", icHeader);
			incomingRequest.put("PoHeader_poType", poHeader.getPoType());
			incomingRequest.put("PoLine_icPoHeader", icHeader);
			incomingRequest.put("PoLine_poNumber", poHeader.getPoNumber());
			incomingRequest.put("Schedule_icHeader", icHeader);
            incomingRequest.put("PerformanceDetail_icPoHeader", icHeader);

			incomingRequest.put("newAccount_icHeader", newIcHeader);
			incomingRequest.put("newAccount_icLine", newIcLine);
			incomingRequest.put("newDocComment_icHeader", newIcHeader);
			incomingRequest.put("newDocComment_icLine", newIcLine);
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader);
			incomingRequest.put("newDocAttachment_docSource", "POH");
			incomingRequest.put("newSchedule_icHeader", newIcHeader);
			incomingRequest.put("newSchedule_documentType", "POH") ;
			incomingRequest.put("newPoLine_icPoHeader", newIcHeader);
            incomingRequest.put("newPerformanceDetail_icPoHeader", newIcHeader);

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
