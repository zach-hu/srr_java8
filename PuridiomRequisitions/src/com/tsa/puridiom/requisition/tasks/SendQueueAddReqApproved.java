package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueAddReqApproved extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            incomingRequest.put("SendQueue_action", "HA");
            String	filename = this.getProcessFilename();
            String email = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "support@puridiom.com");
            incomingRequest.put("SendQueue_subject", email + "," + filename );

            incomingRequest.put("SendQueue_sendfrom", rqh.getOwner());
            incomingRequest.put("SendQueue_sendfromtype", "U");
            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));
            incomingRequest.put("SendQueue_messagetext", this.setParameters(incomingRequest));

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw new TsaException("SendQueueAddReqApproved failed! " + e.getMessage(), e );
        }
        return result;
    }

    public String setParameters(Map incomingRequest)
    {
    	String params = "RequisitionHeader_icReqHeader=" + (String)incomingRequest.get("RequisitionHeader_icReqHeader");
        return params;
    }
}