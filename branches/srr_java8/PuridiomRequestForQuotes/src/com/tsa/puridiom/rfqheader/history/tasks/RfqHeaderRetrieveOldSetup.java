/*
 * Created on August 27, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqheader.history.tasks.RfqHeaderRetrieveOldSetup.java
 * 
 */
package com.tsa.puridiom.rfqheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqHeaderRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader newHeader	= (RfqHeader)incomingRequest.get("newHistoryRfqHeader");
            if(newHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Rfq Header was not found!");
            }
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
