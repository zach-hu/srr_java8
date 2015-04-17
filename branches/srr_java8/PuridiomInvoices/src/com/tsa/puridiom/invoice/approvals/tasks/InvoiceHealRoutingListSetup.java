package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvoiceHealRoutingListSetup extends Task {
    public Object executeTask(Object object) throws Exception {

        Map incomingRequest = (Map) object;
        List routingList = (List) incomingRequest.get("routingList");
        incomingRequest.put("healRoutingList", routingList) ;
        this.setStatus(Status.SUCCEEDED);
        return null ;
    }
}

