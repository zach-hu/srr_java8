package com.tsa.puridiom.paymentaccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PaymentAccountRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPaymentString = (String) incomingRequest.get("PaymentAccount_icPayment");
			BigDecimal icPayment = new BigDecimal ( icPaymentString );

			String queryString = "from PaymentAccount as PaymentAccount where PaymentAccount.icPayment = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPayment}, new Type[] {Hibernate.BIG_DECIMAL});

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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