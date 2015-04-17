/*
 * Created on Nov 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.po.tasks;

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
public class PoCancelBudgetSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader poh = (PoHeader) incomingRequest.get("poHeader");
            incomingRequest.put("header",poh );
			incomingRequest.put("Account_icHeader",poh.getIcPoHeader()) ;
            incomingRequest.put("lineitems", incomingRequest.get("poLineList"));
            incomingRequest.put("budgetAction","2") ;
            incomingRequest.put("budgetType","REJ") ;
            incomingRequest.put("budgetMake","C") ;
            incomingRequest.put("budgetYear", poh.getFiscalYear());
            incomingRequest.put("formtype","PO") ;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return super.executeTask(object);
    }
}
