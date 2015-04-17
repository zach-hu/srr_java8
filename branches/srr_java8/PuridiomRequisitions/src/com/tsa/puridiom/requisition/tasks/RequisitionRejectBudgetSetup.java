/*
 * Created on Nov 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

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
public class RequisitionRejectBudgetSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
            incomingRequest.put("header",rqh );
			incomingRequest.put("Account_icHeader",rqh.getIcReqHeader()) ;
            incomingRequest.put("lineitems", incomingRequest.get("requisitionLineList"));
            incomingRequest.put("budgetAction","1") ;
            incomingRequest.put("budgetType","REJ") ;
            incomingRequest.put("budgetMake","R") ;
            incomingRequest.put("budgetYear", rqh.getFiscalYear());
            incomingRequest.put("formtype","REQ") ;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return super.executeTask(object);
    }
}
