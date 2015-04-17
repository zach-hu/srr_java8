/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.disbheader.history.tasks.DisbHeaderGetHistory.java
 * 
 */
package com.tsa.puridiom.disbheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.historylog.tasks.DisbSetupValues;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbHeaderGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            DisbHeader newHeader	= (DisbHeader)incomingRequest.get("newHistoryDisbHeader");
            if(newHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Disbursement Header was not found!");
            }
            
            DisbSetupValues historyBuild = new DisbSetupValues();
            history = historyBuild.getHeaderHistoryLog(newHeader);
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return history;
    }

}
