package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

/**
 * @author Kathleen
 */
public class RequisitionHeaderRetrieveByPoSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			if (poHeader != null)
			{
				String icReqHeader = poHeader.getIcReqHeader().toString();

				incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
			} else {
				incomingRequest.put("RequisitionHeader_icReqHeader", "0");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}
}
