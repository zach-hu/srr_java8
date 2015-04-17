package com.tsa.puridiom.paymentterm.tasks;

import com.tsa.puridiom.entity.PaymentTerm;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PaymentTermAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			PaymentTerm paymentTerm = (PaymentTerm) incomingRequest.get("paymentTerm");
			if (paymentTerm == null)
			{
				throw new Exception ("PaymentTerm was not found.");
			}
		
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			dbs.add(paymentTerm);
		
			result = paymentTerm;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}