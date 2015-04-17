package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
//import com.tsa.puridiom.entity.RequisitionHeader;
//import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.List;
import java.util.Map;

public class InvoiceHeaderSetStatusByLineList extends Task
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
                this.setStatus(Status.FAILED);
                throw new Exception("InvoiceHeader was not found");
            }
            Integer	ivcStatus = new Integer(0);
            boolean partiallyReceived = false;
            boolean fullyReceived = false;
            boolean noneReceived = false;

            if (invoiceLineList != null) {
                for (int i = 0; i < invoiceLineList.size(); i++) {
                    InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);
                    if (invoiceLine.getStatus().compareTo(DocumentStatus.RCV_PARTIALLYRECEIVED) < 0) {
                        noneReceived = true;
                    } else if (invoiceLine.getStatus().equals(DocumentStatus.RCV_PARTIALLYRECEIVED)) {
                        partiallyReceived = true;
                    } else if (invoiceLine.getStatus().equals(DocumentStatus.RCV_RECEIVED)) {
                        fullyReceived = true;
                    }

                    Integer lineStatus = new Integer(invoiceLine.getStatus());
                    if (i == 0) {
                        ivcStatus = lineStatus;
                    } else if (lineStatus.compareTo(ivcStatus) < 0) {
                        ivcStatus = lineStatus;
                    }
                }

                if (ivcStatus.compareTo(new Integer(DocumentStatus.PO_INPROGRESS)) < 0) {
                    invoiceHeader.setStatus(String.valueOf(ivcStatus));
                } else if (partiallyReceived || (noneReceived && fullyReceived)) {
                    invoiceHeader.setStatus(DocumentStatus.RCV_PARTIALLYRECEIVED);
                } else if (fullyReceived) {
                    invoiceHeader.setStatus(DocumentStatus.RCV_RECEIVED);
                } else {
                    invoiceHeader.setStatus(String.valueOf(ivcStatus));
                }
            }
            Log.debug(this, "Updating Requisition : " +
                    invoiceHeader.getInvoiceNumber() + " to status: " + invoiceHeader.getStatus());
            result = invoiceHeader;
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