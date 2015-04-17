/*
 * Created on Mar 15, 2005
 */
package com.tsa.puridiom.po.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class AutoReleaseRecalculate extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            Map newIncomingRequest = new HashMap();
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            newIncomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());

            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
            newIncomingRequest.put("userId", incomingRequest.get("userId"));
	           newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("po-recalculate.xml");
            process.executeProcess(newIncomingRequest);
            if(process.getStatus() != Status.SUCCEEDED)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("An error ocurred calculating Totals for Order #" + poHeader.getPoNumber() + "-" + poHeader.getReleaseNumber().toString());
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An error ocurred Calculating totals");
        }
        return ret;
    }
}
