package com.tsa.puridiom.poheader.autoawardrevision.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderAutoAwardRevision extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = null;
			Map incomingRequest = (Map)object;

			String organizationId = (String)incomingRequest.get("organizationId");
			List poHeaderList = (List)incomingRequest.get("poHeaderList");

			if (poHeaderList != null && poHeaderList.size() > 0)
			{
				for (Iterator iterator = poHeaderList.iterator(); iterator.hasNext();)
				{
					PoHeader poHeader = (PoHeader) iterator.next();

					incomingRequest.put("userId", incomingRequest.get("userId"));
					incomingRequest.put("organizationId", organizationId);
					incomingRequest.put("dbsession", incomingRequest.get("dbsession"));

					incomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());
					incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
					incomingRequest.put("ApprovalLog_icHeader", poHeader.getIcPoHeader().toString());
					incomingRequest.put("PoHeader_status", poHeader.getStatus());
					incomingRequest.put("PoHeader_poType", poHeader.getPoType());
					incomingRequest.put("poaction", "FORWARD");
					incomingRequest.put("formtype", "PO");
					incomingRequest.put("newStatus", DocumentStatus.PO_AWARDED);

					process = processLoader.loadProcess("po-autoaward-revision.xml");
					process.executeProcess(incomingRequest);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, "PoHeaderAutoAwardRevision failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
			throw new TsaException("PoHeaderAutoAwardRevision failed to retrieve POs: " + e.getMessage(), e);
		}
		return result;
	}
}