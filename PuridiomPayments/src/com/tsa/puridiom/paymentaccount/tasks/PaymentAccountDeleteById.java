package com.tsa.puridiom.paymentaccount.tasks;

import com.tsa.puridiom.entity.PaymentAccount;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class PaymentAccountDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		PaymentAccount paymentAccount = (PaymentAccount) incomingRequest.get("paymentAccount");
		if(paymentAccount == null)
		{
			paymentAccount = new PaymentAccount();
		}
		PaymentAccountSetValuesPK setValues = new PaymentAccountSetValuesPK();
		setValues.setValues(incomingRequest, paymentAccount);
		dbs.delete(paymentAccount);
		this.setStatus(dbs.getStatus());
		return paymentAccount;
	}

}