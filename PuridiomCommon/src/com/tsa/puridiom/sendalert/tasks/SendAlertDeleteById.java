package com.tsa.puridiom.sendalert.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class SendAlertDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		SendAlert sendAlert = (SendAlert)incomingRequest.get("sendAlert");
		if(sendAlert == null)
		{
			sendAlert = new SendAlert();
		}
		SendAlertSetValuesPK setValues = new SendAlertSetValuesPK();
		setValues.setValues(incomingRequest, sendAlert);
		dbs.delete(sendAlert);
		this.setStatus(dbs.getStatus()) ;
		return sendAlert ;
	}

}