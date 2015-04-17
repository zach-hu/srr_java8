/**
 * Created on Apr 1, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoRevision.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoRevision extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result;
        try
        {
            //PoHeader original = (PoHeader)incomingRequest.get("originalPoHeader");
            BigDecimal revision = null;
            //String lastRevision = original.getLastRevision();
            String lastRevision = (String)incomingRequest.get("lastRevision");

            if(lastRevision.equals("C"))
            {
                revision = new BigDecimal(0);
            }
            else
            {
                revision = new BigDecimal(lastRevision.trim());
            }
            revision = revision.add(new BigDecimal(1));
            lastRevision = Utility.formatString("000", revision.toString());
            
            incomingRequest.put("newPoHeader_revisionNumber", lastRevision);
            
            result = lastRevision;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return result;
    }

}
