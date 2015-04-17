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

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class PoCancelLines extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            List poLineList = (List)incomingRequest.get("poLineList");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("poLine-cancel.xml");

            for(int i = 0; i < poLineList.size(); i++)
            {
                PoLine poLine = (PoLine)poLineList.get(i);
                Log.debug(this, "Canceling line: " + poLine.getLineNumber().toString());
                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("poLine", poLine);
                newIncomingRequest.put("PoLine_icPoHeader", poLine.getIcPoHeader().toString());
                newIncomingRequest.put("PoLine_icPoLine", poLine.getIcPoLine().toString());
                newIncomingRequest.put("recalculate", "N");
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

                process.executeProcess(newIncomingRequest);
                this.setStatus(process.getStatus());
                if (this.getStatus() != Status.SUCCEEDED)
                {
                    Log.error(this, "Canceling Line item " + poLine.getLineNumber() + " failed with status: " + process.getStatus());
                    throw new TsaException("Error Canceling PoLine: " + poLine.getLineNumber());
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
