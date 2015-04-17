package com.tsa.puridiom.paymentterm.tasks;

import com.tsa.puridiom.entity.PaymentTerm;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class PaymentTermDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		PaymentTerm paymentTerm = (PaymentTerm) incomingRequest.get("paymentTerm");
		if(paymentTerm == null)
		{
			paymentTerm = new PaymentTerm();
		}
		PaymentTermSetValuesPK setValues = new PaymentTermSetValuesPK();
		setValues.setValues(incomingRequest, paymentTerm);
		dbs.delete(paymentTerm);
		this.setStatus(dbs.getStatus()) ;
		return paymentTerm ;
	}

}