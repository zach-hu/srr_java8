/*
 * Created on Nov 21, 2006
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionsListCancelClose extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;

		try
		{
			Map incomingRequest = (Map) object;

			String icReqHeaderArray[] = (String[]) incomingRequest.get("RequisitionHeader_icReqHeader");
			String noteCancel = (String) incomingRequest.get("reason");
			String reqaction = (String) incomingRequest.get("reqaction");
			String userId = (String) incomingRequest.get("userId");
			String organizationId = (String) incomingRequest.get("organizationId");

			for (int i = 0; i < icReqHeaderArray.length - 2; i++)
			{
				try
				{
					this.cancelRequisition(icReqHeaderArray[i], reqaction, noteCancel, userId, organizationId);
				} catch (Exception e)
				{
					// TODO: handle exception
					Log.error(this, "Error cancelling Requisition with icReqHeader " + icReqHeaderArray[i]);
				}

			}

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition List cancel / close failed!" + e.getMessage(), e);
		}
		return ret;
	}

	private void cancelRequisition(String icReqHeader, String reqaction, String noteCancel, String userId, String organizationId) throws Exception
	{

		Map incomingRequest = new HashMap();
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		PuridiomProcess process = processLoader.loadProcess("requisition-cancel-close.xml");

		incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
		incomingRequest.put("RequisitionLine_icReqHeader", icReqHeader);
		incomingRequest.put("reqaction", reqaction);
		incomingRequest.put("RequisitionHeader_noteCancel", noteCancel);
		incomingRequest.put("userId", userId);
		incomingRequest.put("organizationId", organizationId);

		process.executeProcess(incomingRequest);
	}

}