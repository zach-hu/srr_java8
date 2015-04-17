/**
 * 
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class InvoiceLineRetrieveQtyInvoicedByRelatedPoLine extends Task
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
			String icRelPoLine = (String) incomingRequest.get("InvoiceLine_icRelPoLine");
			BigDecimal bdIcRelPoLine = new BigDecimal(icRelPoLine);

			String queryString = "select sum(il.quantity) from InvoiceLine as il where il.icRelPoLine = ? and il.invoiceNumber <> 'N/A' and il.status <> ? ";

			List resultList = dbs.query(queryString, new Object[] { bdIcRelPoLine, DocumentStatus.CANCELLED }, new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING });
			
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "InvoiceLineRetrieveQtyInvoicedByRelatedPoLine error " + e.getMessage());

			throw e;
		}

		return result;
	}
}
