/*
 * Created on Nov 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.rfq.tasks;

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
public class RfqForwardBudgetSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
            incomingRequest.put("header",rfqHeader );
            incomingRequest.put("lineitems", incomingRequest.get("rfqLineList"));
            incomingRequest.put("budgetAction","2") ;
        	incomingRequest.put("budgetType","RFQ") ;
            incomingRequest.put("budgetMake","A") ;
            incomingRequest.put("budgetHeaderIc", rfqHeader.getIcRfqHeader().toString()) ;
            incomingRequest.put("budgetYear", rfqHeader.getFiscalYear());
            incomingRequest.put("formtype","RFQ") ;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return super.executeTask(object);
    }
}
