/**
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoLineSetValuesFromPreviousPoLine extends Task
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

			PoLineRetrieveById poLineRetrieveById = new PoLineRetrieveById();
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			Map newIncomingRequest = new HashMap();
			PoLine poLine;

			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", userId);
			newIncomingRequest.put("dbsession", dbsession);
			newIncomingRequest.put("PoLine_icPoLine", reqLine.getIcRevisedPoLine().toString());

			poLine = (PoLine) poLineRetrieveById.executeTask(newIncomingRequest);

			if (poLine != null)
			{
				incomingRequest.put("previousPoLine", poLine);
				incomingRequest.put("PoLine_icLineKey", poLine.getIcLineKey().toString());
				incomingRequest.put("PoLine_qtyReceived", poLine.getQtyReceived().toString());
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoLineSetValuesFromPreviousPoLine error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}