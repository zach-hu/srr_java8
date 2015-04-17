/*
 * Created on Dec 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisbLineExtendedCancelSetupExtra extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
            incomingRequest.put("RequisitionLine_icReqLine", disbLine.getIcReqLine().toString());
			incomingRequest.put("InvBinLocation", disbLine.getIcRc());
			incomingRequest.put("InvLocation_itemNumber", disbLine.getItemNumber());
			incomingRequest.put("InvLocation_itemLocation", disbLine.getItemLocation());
			
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
