/**
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoHeaderGetIcHeaderKeyFromPreviousPo extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			DBSession dbsession = (DBSession) incomingRequest.get("dbsession");

			PoHeaderRetrieveById poHeaderRetrieveById = new PoHeaderRetrieveById();
			BigDecimal icPoHeader = (BigDecimal) incomingRequest.get("originalIc");
			Map newIncomingRequest = new HashMap();
			PoHeader poHeader;

			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", userId);
			newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
			newIncomingRequest.put("dbsession", dbsession);
			newIncomingRequest.put("PoHeader_icPoHeader", icPoHeader.toString());

			poHeader = (PoHeader) poHeaderRetrieveById.executeTask(newIncomingRequest);

			incomingRequest.put("previousPo", poHeader);

			result = poHeader.getIcHeaderKey().toString();

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoHeaderGetIcHeaderKeyFromPreviousPo error " + e.getMessage());

			throw e;
		}

		return result;
	}

}