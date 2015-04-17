package com.tsa.puridiom.sendalert.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class SendAlertSetValuesPK
{
	public void setValues(Map incomingRequest, SendAlert sendAlert ) throws Exception
	{
		try
		{
			String queueidString = (String) incomingRequest.get("SendAlert_queueid");
			BigDecimal queueid = new BigDecimal ( queueidString );
			sendAlert.setQueueid(queueid);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}