package com.tsa.puridiom.po.approvals.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoCommentIdSetup extends Task {
    public Object executeTask(Object object) throws Exception {

        Map incomingRequest = (Map) object;
        String icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
        incomingRequest.put("DocComment_icHeader", icPoHeaderString) ;

        this.setStatus(Status.SUCCEEDED);
        return null ;
    }
}
