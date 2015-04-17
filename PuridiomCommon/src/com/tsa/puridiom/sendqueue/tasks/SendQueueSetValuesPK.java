package com.tsa.puridiom.sendqueue.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SendQueueSetValuesPK
{
	public void setValues(Map incomingRequest, SendQueue sendqueue ) throws Exception
	{
		try
		{
			String queueidString = (String) incomingRequest.get("SendQueue_queueid");
			BigDecimal queueid = new BigDecimal ( queueidString );
			sendqueue.setQueueid(queueid);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}