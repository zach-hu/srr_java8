package com.tsa.puridiom.invoiceheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.invoiceline.tasks.InvoiceLineUpdateReqLineStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceHeaderSetReqStatusByLineList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
            List invoiceLineList = (List) incomingRequest.get("invoiceLineList");

            if (invoiceHeader == null)
            {
                throw new Exception("InvoiceHeader was not found");
            }
            if (invoiceLineList != null) {
                for (int i = 0; i < invoiceLineList.size(); i++) {
                    InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);
                    InvoiceLineUpdateReqLineStatus task = new InvoiceLineUpdateReqLineStatus();
                    incomingRequest.put("invoiceLine", invoiceLine); // aqui esta el problema
                    task.executeTask(incomingRequest);
                    this.setStatus(task.getStatus());
                    if(task.getStatus() != Status.SUCCEEDED)
                    {
                        throw new TsaException("An error occurred updating status for invoice #: " + invoiceLine.getInvoiceNumber());
                    }
                }
            }

            result = null;
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}