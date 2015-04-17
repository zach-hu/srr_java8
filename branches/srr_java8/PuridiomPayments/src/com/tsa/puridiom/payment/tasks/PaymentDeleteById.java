package com.tsa.puridiom.payment.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class PaymentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Payment payment = (Payment)incomingRequest.get("payment");
		if(payment == null)
		{
			payment = new Payment();
		}
		PaymentSetValuesPK setValues = new PaymentSetValuesPK();
		setValues.setValues(incomingRequest, payment);
		dbs.delete(payment);
		this.setStatus(dbs.getStatus()) ;
		return payment ;
	}

}