package com.tsa.puridiom.alerts.tasks;


//import java.util.List;
import java.util.Map;

//import com.tsa.puridiom.alerts.Alert;
import com.tsa.puridiom.alerts.AlertManager;
//import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AlertGetList extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		//String type = (String)incomingRequest.get("alerttype");
		String organizationId = (String)incomingRequest.get("organizationId");

		try
		{
			ret = AlertManager.getInstance().getAlertList(organizationId);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Process Name was not found for alerts for organization : " +organizationId, e);
		}
		return ret;
	}


}
