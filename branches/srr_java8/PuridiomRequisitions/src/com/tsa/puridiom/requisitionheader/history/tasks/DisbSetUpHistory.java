/*
 * Created on Dec 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionheader.history.tasks;

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
public class DisbSetUpHistory extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            incomingRequest.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
            incomingRequest.put("newHistoryRequisitionLine", incomingRequest.get("requisitionLine"));
            //incomingRequest.put("RequisitionHeader_icReqHeader", reqLine.getIcReqHeader().toString());
			//incomingRequest.put("RequisitionLine_icReqHeader", reqLine.getIcReqHeader().toString());
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
