package com.tsa.puridiom.invoice.tasks;

import java.util.Iterator;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.invoiceheader.tasks.InvoiceHeaderUpdate;
import com.tsa.puridiom.invoiceline.tasks.InvoiceLineUpdate;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ResetExportedInvoices extends Task
{
	Map incomingRequest = null;
	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	PuridiomProcess process= null;

	public Object executeTask(Object object) throws Exception {
        Object ret = null;

        try
        {
            incomingRequest = (Map) object;
            Object icIvcHeaderObj = incomingRequest.get("InvoiceHeader_icIvcHeader");

            if (icIvcHeaderObj instanceof String[])
            {
                String	icIvcHeaderArray[] = (String[]) icIvcHeaderObj;
                for (int i = 0; i < icIvcHeaderArray.length; i++)
                {
                	resetInvoice(icIvcHeaderArray[i]);
                }
            }
            else
            {
            	String icIvcHeader = (String) icIvcHeaderObj;
            	resetInvoice(icIvcHeader);
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("ResetExportedInvoices failed" + e.getMessage(), e);
        }
        return ret;
    }

	private void resetInvoice(String icIvcHeader) throws Exception
	{
		if (!HiltonUtility.isEmpty(icIvcHeader))
		{
			process = processLoader.loadProcess("invoice-retrieve.xml");
	        incomingRequest.put("InvoiceHeader_icIvcHeader", icIvcHeader);
	        process.executeProcess(incomingRequest);

	        InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");
	        if (ivh != null)
	        {
	        	InvoiceHeaderUpdate task = new InvoiceHeaderUpdate();
	        	InvoiceLineUpdate task2 = new InvoiceLineUpdate();

	        	ivh.setStatus("6010");
	        	ivh.setLastExtract("");
	        	ivh.setDateExported(null);

	        	incomingRequest.put("invoiceHeader", ivh);
        		task.executeTask(incomingRequest);

        		for (Iterator it = ivh.getInvoiceLineList().iterator(); it.hasNext(); )
    		    {
            		InvoiceLine ivl = (InvoiceLine) it.next();
            		ivl.setStatus("6010");
            		incomingRequest.put("invoiceLine", ivl);
            		task2.executeTask(incomingRequest);
    		    }
	        }
		}
	}
}
