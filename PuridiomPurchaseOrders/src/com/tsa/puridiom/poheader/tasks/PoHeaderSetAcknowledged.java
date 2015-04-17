/**
 * Created on August 1, 2007
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderSetAcknowledged.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.utility.Dates;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.util.Map;

public class PoHeaderSetAcknowledged extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;
        try
        {
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");

            poHeader.setDateAcknowledged(Dates.getDate(Dates.today("", poHeader.getTimeZone())));
            poHeader.setAcknowledgedBy((String) incomingRequest.get("userId"));

            result = poHeader;
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
