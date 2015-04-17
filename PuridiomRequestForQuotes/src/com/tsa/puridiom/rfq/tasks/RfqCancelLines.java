/*
 * Created on Oct. 17, 2005
 */
package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqCancelLines extends Task {

    public Object executeTask(Object object) throws Exception {
        Object ret = null;
        try {
            Map incomingRequest = (Map)object;
            List rfqLineList = (List)incomingRequest.get("rfqLineList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("rfqline-cancel.xml");
            
            RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");

            for(int i = 0; i < rfqLineList.size(); i++) {
                RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
 
                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("rfqHeader", rfqHeader);
                newIncomingRequest.put("rfqLine", rfqLine);
                newIncomingRequest.put("RfqLine_icRfqHeader", rfqLine.getIcRfqHeader().toString());
                newIncomingRequest.put("RfqLine_icRfqLine", rfqLine.getIcRfqLine().toString());

                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

                process.executeProcess(newIncomingRequest);
                this.setStatus(process.getStatus());
                if (this.getStatus() != Status.SUCCEEDED)
                {
                    Log.error(this, "Canceling Line item " + rfqLine.getRfqLine() + " failed with status: " + process.getStatus());
                    throw new Exception("Error Canceling RfqLine: " + rfqLine.getRfqLine());
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new Exception("An error occurred cancelling line items", e);
        }
        return super.executeTask(object);
    }
}
