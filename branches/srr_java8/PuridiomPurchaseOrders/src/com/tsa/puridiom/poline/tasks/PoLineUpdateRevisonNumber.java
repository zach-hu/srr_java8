/*
 * Created on Sep 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks;.PoLineUpdateRevisonNumber.java
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
public class PoLineUpdateRevisonNumber extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        String ret = null;
		try
		{
		    Map incomingRequest = (Map)object;
		    String lineUpdated = (String)incomingRequest.get("lineUpdated");
		    if(lineUpdated == null)
		    {
		        lineUpdated = "false";
		    }
		    if(lineUpdated.equalsIgnoreCase("true"))
		    {
		        incomingRequest.put("PoLine_lineRevNo", incomingRequest.get("PoHeader_revisionNumber"));
		    }
		    this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
		    throw new TsaException(this.getName(), e);
		}
        return ret;
    }
}
