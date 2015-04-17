/*
 * Created on Aug 17, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoScheduleSetup.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoScheduleSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String reqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
            incomingRequest.put("Schedule_icHeader", reqHeader.toString());
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        // TODO Auto-generated method stub
        return super.executeTask(object);
    }
}
