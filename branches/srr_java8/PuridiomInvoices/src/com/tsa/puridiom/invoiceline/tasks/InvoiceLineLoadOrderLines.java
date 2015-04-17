/**
 *
 * Created on August 24, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoiceline.tasks.InvoiceLineLoadOrderLines.java
 *
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceLineLoadOrderLines extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            List poLines = (List)incomingRequest.get("poLines");
            List receiptLines = (List)incomingRequest.get("receiptLineList");
            List invoiceLineList = new ArrayList();
            String userId = (String)incomingRequest.get("userId");
            int line = 1;
            int i = 0;
            InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            Log.error(this, "PoHeader ic = "+ HiltonUtility.ckNull(poHeader.getIcPoHeader()).toString()+" Invoice "+HiltonUtility.ckNull(invoiceHeader.getIcIvcHeader()).toString());
            for (Iterator iter = poLines.iterator(); iter.hasNext();)
            {
                PoLine poLine = (PoLine) iter.next();

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
    	        processLoader.setApplicationName(this.getApplicationName());
    	        PuridiomProcess process = processLoader.loadProcess("invoiceline-load-from-order.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("poLine", poLine);
                newIncomingRequest.put("lineNumber", String.valueOf(line));
                newIncomingRequest.put("createdfrom", "PO");
                newIncomingRequest.put("invoiceHeader", invoiceHeader);
                newIncomingRequest.put("InvoiceHeader_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
                newIncomingRequest.put("poHeader", poHeader);
                if (("PS-ACI".equalsIgnoreCase(userId)))
                {
                	ReceiptLine receiptLine = (ReceiptLine)receiptLines.get(i);
                	Log.error(this, " receiptHeader ic = "+ HiltonUtility.ckNull(receiptLine.getIcRecHeader()).toString()+" receiptLine "+HiltonUtility.ckNull(receiptLine.getIcRecLine()).toString());
                	newIncomingRequest.put("receiptLine",receiptLine);
                }


                if(newIncomingRequest.get("invoiceLine") != null)
                {
                    newIncomingRequest.put("invoiceLine", null);
                }

                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

                String oid = (String)incomingRequest.get("organizationId");
                if (!poLine.getStatus().equalsIgnoreCase("9020"))
                {
                	process.executeProcess(newIncomingRequest);
                	this.setStatus(process.getStatus());
                	if (this.getStatus() != Status.SUCCEEDED)
                	{
                		Log.error(this, "invoiceline-load-from-order failed with status: " + process.getStatus());
                		throw new TsaException("Error loading InvoiceLine info from OrderLine Number: " + poLine.getLineNumber());
                	}
                	else
                	{
                		invoiceLineList.add(newIncomingRequest.get("invoiceLine"));
                		line++;
                		i++;
                	}
                }
            }
            incomingRequest.put("invoiceLineList", invoiceLineList);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }
}