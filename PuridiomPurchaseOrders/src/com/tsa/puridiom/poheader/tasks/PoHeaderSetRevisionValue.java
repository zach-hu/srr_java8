package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoHeaderSetRevisionValue extends Task
{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

            BigDecimal revisionValue = new BigDecimal(0);
            revisionValue = (BigDecimal)poHeader.getTotal();
            poHeader.setRevisionValue(revisionValue);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, " Error on the setup revision value " + e.toString());
            e.toString();
            throw e;
        }
        return result;
    }
}