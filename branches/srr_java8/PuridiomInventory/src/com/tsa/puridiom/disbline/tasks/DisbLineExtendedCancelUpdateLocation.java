/*
 * Created on Dec 28, 2004
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class DisbLineExtendedCancelUpdateLocation extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            InvLocation location = (InvLocation) incomingRequest.get("invLocation");
			BigDecimal locationAllocated = location.getQtyAlloc();
			BigDecimal qtyToCancel = (BigDecimal)incomingRequest.get("qtyToCancel");
			locationAllocated = locationAllocated.subtract(qtyToCancel);
			location.setQtyAlloc(locationAllocated);
			incomingRequest.put("invLocation", location);
			ret = location;
			
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
