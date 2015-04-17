package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionCommentIdSetup extends Task {
    public Object executeTask(Object object) throws Exception {

        Map incomingRequest = (Map) object;
        String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
        incomingRequest.put("DocComment_icHeader", icReqHeaderString) ;

        this.setStatus(Status.SUCCEEDED);
        return null ;
    }
}
