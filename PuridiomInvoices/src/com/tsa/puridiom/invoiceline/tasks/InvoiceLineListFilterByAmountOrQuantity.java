/**
 *
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 * April 02, 2008
 */
public class InvoiceLineListFilterByAmountOrQuantity extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			List invoiceLineList = (List)incomingRequest.get("invoiceLineList");
			List newInvoiceLineList = new ArrayList();
			for(int i = 0; i < invoiceLineList.size(); i++)
			{
				InvoiceLine invoiceLine = (InvoiceLine)invoiceLineList.get(i);
				if(invoiceLine.getQuantity().compareTo(new BigDecimal(0)) > 0 || invoiceLine.getAmountInvoiced().compareTo(new BigDecimal(0)) > 0)
				{
					newInvoiceLineList.add(invoiceLine);
				}
			}

			ret = newInvoiceLineList;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.getMessage());
		}
		return ret;
	}


}
