package com.tsa.puridiom.invoiceheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceHeaderListUpdatePoInfo extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            List invoiceHeaderList = (List) incomingRequest.get("invoiceHeaderList");

            if (invoiceHeaderList != null)
            {
                for (int i = 0; i < invoiceHeaderList.size(); i++)
                {
                    InvoiceHeader invoiceHeader = (InvoiceHeader) invoiceHeaderList.get(i);
                    InvoiceHeaderUpdatePoInfo task = new InvoiceHeaderUpdatePoInfo();
                    incomingRequest.put("invoiceHeader", invoiceHeader);
                    task.executeTask(incomingRequest);
                    this.setStatus(task.getStatus());
                    if(task.getStatus() != Status.SUCCEEDED)
                    {
                        throw new TsaException("An error occurred updating po revision info for Invoice #: " + invoiceHeader.getInvoiceNumber());
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