/*
 * Created on Oct 26, 2007
 */
package com.tsa.puridiom.paymentaccount.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Kelli
 */
public class PaymentAccountRetrieveSumByPoNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
        Object result = null;

		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        String poNumber = (String) incomingRequest.get("PaymentAccount_poNumber");
            String releaseNumber = (String) incomingRequest.get("PaymentAccount_releaseNumber");

	        String queryString = "select sum(pa.checkAmount) from PaymentAccount as pa where pa.poNumber = ? ";
            if (releaseNumber != null && !releaseNumber.equals("0")) {
                queryString = queryString + " and pa.releaseNumber = '" + releaseNumber + "'";
            }

	        List resultList = dbs.query(queryString, new Object[] {poNumber} , new Type[] {Hibernate.STRING});

			if (resultList != null && resultList.size() > 0)
			{
				BigDecimal paymentTotal = (BigDecimal) resultList.get(0);
                result = paymentTotal;
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
