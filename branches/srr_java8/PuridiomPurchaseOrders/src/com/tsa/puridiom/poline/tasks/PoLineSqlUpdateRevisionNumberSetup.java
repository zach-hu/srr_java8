/*
 * Created on Sep 22, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks;.PoLineSqlUpdateRevisionNumber.java
 * 
 */
package com.tsa.puridiom.poline.tasks;

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
public class PoLineSqlUpdateRevisionNumberSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
       try
	    {
           Map incomingRequest = (Map)object;
           String revisionNumber = (String)incomingRequest.get("PoHeader_revisionNumber");
           String icLine = (String)incomingRequest.get("PoLine_icPoLine");
           String updated = (String)incomingRequest.get("lineUpdated");
           
           
	       this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e)
	    {
	        this.setStatus(Status.FAILED);
	        throw new TsaException(this.getName(), e);
	    }
        return super.executeTask(object);
    }
}
