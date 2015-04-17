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
public class PoMultipleCreateFromRfqPartial extends Task {

    public Object executeTask(Object object) throws Exception {
        List ret = new ArrayList();
        try {
            Map incomingRequest = (Map)object;
            List poDataList = (List)incomingRequest.get("poDataList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("po-create-from-rfq-by-vendor-potype.xml");
            List poLineList = new ArrayList();

            String rfqHeader_icReqHeader = (String) incomingRequest.get("RfqHeader_icReqHeader");

            for(int i = 0; i < poDataList.size(); i++) {
            	List values = (List) poDataList.get(i);
            	List lines  = (List) values.get(2);

                Map newIncomingRequest = new HashMap();

                newIncomingRequest.put("RfqHeader_icRfqHeader", (String) incomingRequest.get("RfqHeader_icRfqHeader"));
                newIncomingRequest.put("RfqHeader_rfqNumber", (String) incomingRequest.get("RfqHeader_rfqNumber"));
                newIncomingRequest.put("RfqVendor_vendorId", (String) values.get(0));
                newIncomingRequest.put("RfqVendor_icRfqHeader", (String) incomingRequest.get("RfqHeader_icRfqHeader"));
                newIncomingRequest.put("vendorId", (String) values.get(0));
                newIncomingRequest.put("PoHeader_poType", (String) values.get(1));
                newIncomingRequest.put("rfqLinePartialList", lines);
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

                if(values.size() > 3 && Long.parseLong(rfqHeader_icReqHeader) <= 0)
                {
                	newIncomingRequest.put("PoHeader_prefix", (String) values.get(3));
                }

                process.executeProcess(newIncomingRequest);

                ret.add((PoHeader) newIncomingRequest.get("poHeader"));

                List poLineListPartial = (List) newIncomingRequest.get("poLineList");
                for (int j=0; j < poLineListPartial.size(); j++) {
                	poLineList.add(poLineListPartial.get(j));
                }

                this.setStatus(process.getStatus());
                if (this.getStatus() != Status.SUCCEEDED)
                {
                    Log.error(this, "Canceling Line items " + (String) incomingRequest.get("RfqHeader_icRfqHeader") + " failed with status: " + process.getStatus());
                    //throw new Exception("Error Canceling RfqLine: " + rfqLine.getRfqLine());
                }
            }

            incomingRequest.put("poLineList", poLineList);

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
