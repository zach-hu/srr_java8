package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;

import java.util.Map;

public class InvoiceSetStatus extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            String newStatus = (String) incomingRequest.get("newStatus");
            if (newStatus == null) {
                newStatus = DocumentStatus.IVC_APPROVED ;
            }

            InvoiceHeader ivh = (InvoiceHeader)incomingRequest.get("invoiceHeader");
            List ivcLineList = (List) incomingRequest.get("invoiceLineList") ;
            String originalStatus = ivh.getStatus();
            ivh.setStatus(newStatus);
            incomingRequest.put("InvoiceHeader_status", newStatus);
            incomingRequest.put("originalStatus", originalStatus);

            Log.debug(this, "Original Invoice " + ivh.getInvoiceNumber() + " newStatus is: " + originalStatus);
            Log.debug(this, "New Invoice " + ivh.getInvoiceNumber() + " newStatus is: " + newStatus);

            for (int i=0; i < ivcLineList.size(); i++) {
                InvoiceLine ivl = (InvoiceLine) ivcLineList.get(i);
                ivl.setStatus(newStatus);
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error ocurred submitting the current Invoice ", e);
        }
        return null ;
    }
}
