/*
 * Created on Oct. 17, 2005
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class PoMultipleCreateFromRfq extends Task {

    public Object executeTask(Object object) throws Exception {
        List ret = new ArrayList();
        try {
            Map incomingRequest = (Map)object;
            List vendorList = (List)incomingRequest.get("vendorList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("po-create-from-rfq-by-vendor.xml");

            for(int i = 0; i < vendorList.size(); i++) {
                String vendorId = (String) vendorList.get(i);

                Map newIncomingRequest = new HashMap();

                newIncomingRequest.put("RfqHeader_icRfqHeader", (String) incomingRequest.get("RfqHeader_icRfqHeader"));
                newIncomingRequest.put("RfqVendor_vendorId", vendorId);
                newIncomingRequest.put("vendorId", vendorId);
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

                process.executeProcess(newIncomingRequest);
                ret.add((PoHeader) newIncomingRequest.get("poHeader"));

                this.setStatus(process.getStatus());
                if (this.getStatus() != Status.SUCCEEDED)
                {
                    Log.error(this, "Canceling Line items " + (String) incomingRequest.get("RfqHeader_icRfqHeader") + " failed with status: " + process.getStatus());
                    //throw new Exception("Error Canceling RfqLine: " + rfqLine.getRfqLine());
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new Exception("An error occurred cancelling line items", e);
        }
        return ret;
    }
}
