/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.disbheader.history.tasks.DisbHeaderRetrieveOldSetup.java
 * 
 */
package com.tsa.puridiom.disbheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbHeaderRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            DisbHeader newHeader	= (DisbHeader)incomingRequest.get("newHistoryDisbHeader");
            if(newHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Disb Header was not found!");
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
