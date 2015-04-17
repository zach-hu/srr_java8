package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvoiceCommentIdSetup extends Task {
    public Object executeTask(Object object) throws Exception {

        Map incomingRequest = (Map) object;
        String icIvcHeaderString = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
        incomingRequest.put("DocComment_icHeader", icIvcHeaderString) ;

        this.setStatus(Status.SUCCEEDED);
        return null ;
    }
}
