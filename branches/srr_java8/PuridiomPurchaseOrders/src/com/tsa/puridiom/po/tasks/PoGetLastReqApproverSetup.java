/**
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 *
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoGetLastReqApproverSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;

        try
        {
        	PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
        	incomingRequest.put("ApprovalLog_icHeader", poHeader.getIcReqHeader().toString());

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
