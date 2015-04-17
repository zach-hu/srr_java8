/*
 * Created on August 27, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqline.tasks.RfqLineListHistory.java
 *
 */
package com.tsa.puridiom.invoiceline.history.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceLineListHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map) object;
            List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
            if (invoiceLineList != null)
            {
	            for (Iterator iter = invoiceLineList.iterator(); iter.hasNext();)
	            {
	                InvoiceLine invoiceLine = (InvoiceLine) iter.next();
	                InvoiceLineHistory historyLine = new InvoiceLineHistory();

	                incomingRequest.put("newHistoryInvoiceLine", invoiceLine);
	                incomingRequest.put("invoiceHeader", incomingRequest.get("invoiceHeader"));
	                //incomingRequest.put("autoReleased", incomingRequest.get("invoiceFromRelease"));
	                incomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
	                incomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
	                incomingRequest.put("rejectedBy", incomingRequest.get("rejectedBy"));

	                historyLine.executeTask(incomingRequest);
	                this.setStatus(historyLine.getStatus());
	                if(this.getStatus() != Status.SUCCEEDED)
	                {
	                    throw new TsaException(this.getName() + "Error ocurred writing history for line: " + invoiceLine.getIcIvcLine() + ", item: " + invoiceLine.getItemNumber());
	                }
	            }
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}