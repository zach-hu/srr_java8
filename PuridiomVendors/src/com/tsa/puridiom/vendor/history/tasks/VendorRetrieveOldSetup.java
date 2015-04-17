/*
 * Created onOctober 11, 2007
 *
 * project: HiltonVendors
 * package com.tsa.puridiom.vendor.history.tasks.VendorRetrieveOldSetup.java
 *
 */
package com.tsa.puridiom.vendor.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class VendorRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            Vendor newVendor	= (Vendor) incomingRequest.get("newHistoryVendor");
            if (newVendor == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Vendor was not found!");
            }

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
