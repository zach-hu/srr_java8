/*
 * Created on Jan 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.poheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RfqHeaderHistorySetupFromPo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            incomingRequest.put("RfqHeader_icRfqHeader", poHeader.getIcRfqHeader().toString());
            incomingRequest.put("RfqLine_icRfqHeader", poHeader.getIcRfqHeader().toString());
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        return super.executeTask(object);
    }
}
