/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.history.tasks.DisbursementLineGetHistory.java
 * 
 */
package com.tsa.puridiom.disbline.history.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.historylog.tasks.DisbLineSetupHistoryValues;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbursementLineGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            DisbLine newLine	= (DisbLine)incomingRequest.get("newHistoryDisbLine");
            DisbHeader header = (DisbHeader)incomingRequest.get("disbHeader");
            
            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Disb Line was not found!");
            }
            
            DisbLineSetupHistoryValues historyBuild = new DisbLineSetupHistoryValues();
            history = historyBuild.getLineHistoryLog(newLine, header);
            
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
