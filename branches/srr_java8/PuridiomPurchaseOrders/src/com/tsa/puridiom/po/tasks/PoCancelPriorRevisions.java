/*
 * Created on Apr 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.po.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class PoCancelPriorRevisions extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            List poLineList = (List)incomingRequest.get("PoRevisionList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("po-cancel.xml");

            for(int i = 0; i < poLineList.size(); i++)
            {
                PoHeader poHeader = (PoHeader)poLineList.get(i);
                Log.debug(this, "Canceling Revision: " + poHeader.getPoNumber());
                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("poHeader", poHeader);
                newIncomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());
                newIncomingRequest.put("recalculate", "Y");
                newIncomingRequest.put("priorrev", "Y");
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("poaction", incomingRequest.get("poaction"));

                process.executeProcess(newIncomingRequest);
                this.setStatus(process.getStatus());
                if (this.getStatus() != Status.SUCCEEDED)
                {
                    Log.error(this, "Canceling Line item " + poHeader.getPoNumber() + " failed with status: " + process.getStatus());
                    throw new TsaException("Error Canceling PoLine: " + poHeader.getPoNumber());
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An error occurred canceling line items", e);
        }
        return super.executeTask(object);
    }
}
