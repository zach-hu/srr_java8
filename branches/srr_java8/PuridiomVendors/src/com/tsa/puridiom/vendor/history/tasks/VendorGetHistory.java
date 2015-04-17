/*
 * Created on October 12, 2007
 *
 * project: HiltonVendors
 * package com.tsa.puridiom.vendor.history.tasks.VendorGetHistory.java
 *
 */
package com.tsa.puridiom.vendor.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.historylog.tasks.VendorSetupValues;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class VendorGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map) object;
            Vendor newVendor	= (Vendor) incomingRequest.get("newHistoryVendor");

            if (newVendor == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Vendor was not found!");
            }

            VendorSetupValues historyBuild = new VendorSetupValues();
            historyBuild.setOrganizationId((String)incomingRequest.get("organizationId"));
            historyBuild.setUserId((String)incomingRequest.get("userId"));
            //historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));

            history = historyBuild.getVendorHistoryLog(newVendor);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return history;
    }

}
