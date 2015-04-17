/**
 *
 */
package com.tsa.puridiom.paymentaccount.tasks;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.PaymentAccount;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny
 */
public class PaymentAccountRetrieveSumByPoNumberCheckNumber extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			List paymentAccounts = (List) incomingRequest.get("paymentAccountList");
			String organizationId = (String) incomingRequest.get("organizationId");
			BigDecimal paymentTotal = new BigDecimal(0);
			Set checkNumbers = new HashSet();

			for (Iterator iterator = paymentAccounts.iterator(); iterator.hasNext();)
			{
				PaymentAccount paymentAccount = (PaymentAccount) iterator.next();

				if (!checkNumbers.contains(paymentAccount.getCheckNumber()) && organizationId.equalsIgnoreCase("bsc04p"))
				{
					paymentTotal = paymentTotal.add(paymentAccount.getInvoiceAmount());
					checkNumbers.add(paymentAccount.getCheckNumber());
				}
				else
				{
					if (!checkNumbers.contains(paymentAccount.getCheckNumber()))
						checkNumbers.add(paymentAccount.getCheckNumber());
					paymentTotal = paymentTotal.add(paymentAccount.getInvoiceAmount());
				}
			}

			incomingRequest.put("totalCheckRecords", String.valueOf(checkNumbers.size()));

			result = paymentTotal;

			this.setStatus(dbs.getStatus());
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
