package com.tsa.puridiom.payment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PaymentRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("Payment_icPoHeader");
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
			String sequenceString = (String) incomingRequest.get("Payment_sequence");
			BigDecimal sequence = new BigDecimal ( sequenceString );

			String queryString = "from Payment as Payment where Payment.id.icPoHeader = ? and Payment.id.sequence = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader, sequence, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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