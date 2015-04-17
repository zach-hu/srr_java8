/*
 * Created onOctober 11, 2007
 *
 * project: HiltonVendors
 * package com.tsa.puridiom.vendor.history.tasks.VendorHistory.java
 *
 */
package com.tsa.puridiom.vendor.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class VendorHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            Vendor newVendor = (Vendor)incomingRequest.get("vendor");
            if (!Utility.isEmpty(newVendor.getVendorId()))
            {
	            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
	            PuridiomProcess process = processLoader.loadProcess("vendor-history.xml");

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("newHistoryVendor", newVendor);
				process.executeProcess(newIncomingRequest);
				this.setStatus(process.getStatus());
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}