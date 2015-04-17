package com.tsa.puridiom.paymentaccount.tasks;

import com.tsa.puridiom.entity.PaymentAccount;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PaymentAccountAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PaymentAccount paymentAccount = (PaymentAccount) incomingRequest.get("paymentAccount");
			if (paymentAccount == null)
			{
				throw new Exception ("PaymentAccount was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.add(paymentAccount);

			result = paymentAccount;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}