/**
 * 
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class InvoiceLineValidateAccountsForValidLine extends Task
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
			List invoiceLineList = (List) incomingRequest.get("lineitems");
			String isValidLine = "true";

			for (Iterator it = invoiceLineList.iterator(); it.hasNext();)
			{
				InvoiceLine invoiceLine = (InvoiceLine) it.next();

				if ((invoiceLine.getLineTotal().compareTo(new BigDecimal(0)) != 0) && ((invoiceLine.getAccountList() == null) || invoiceLine.getAccountList().isEmpty()))
				{
					isValidLine = "false";
					break;
				}
			}

			result = isValidLine;

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "InvoiceLineValidateAccountsForValidLine error " + e.getMessage());

			throw e;
		}

		return result;
	}
}