/*
 * Created on Dec 12, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BuyerData extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            BuyerAllGraphData graph = new BuyerAllGraphData(incomingRequest);
            ret = graph;
            this.setStatus(Status.SUCCEEDED);
            
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return ret;
    }
}
