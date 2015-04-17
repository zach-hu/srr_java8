/*
 * Created on Nov 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.Map;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvoiceCancelBudgetSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");
            incomingRequest.put("header",ivh );
			incomingRequest.put("Account_icHeader",ivh.getIcIvcHeader()) ;
            incomingRequest.put("lineitems", incomingRequest.get("invoiceLineList"));
            incomingRequest.put("budgetAction","2") ;
            incomingRequest.put("budgetType","REJ") ;
            incomingRequest.put("budgetMake","C") ;
            // Until we get a fiscal year
            incomingRequest.put("budgetYear", Integer.toString(ivh.getInvoiceDate().getYear()));
            incomingRequest.put("formtype","INV") ;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return super.executeTask(object);
    }
}
