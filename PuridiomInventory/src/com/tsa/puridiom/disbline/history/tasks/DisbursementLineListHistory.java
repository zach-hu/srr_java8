/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.DisbursementLineGetHistory.java
 * 
 */
package com.tsa.puridiom.disbline.history.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbursementLineListHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            List reqList = (List)incomingRequest.get("disbLines");
            if(reqList.size()>0)
            {
            for (Iterator iter = reqList.iterator(); iter.hasNext();)
            {
                DisbLine disbLine = (DisbLine) iter.next();
                DisbursementLineHistory historyLine = new DisbursementLineHistory();
                
                incomingRequest.put("newHistoryDisbLine", disbLine);
                incomingRequest.put("disbHeader", incomingRequest.get("disbHeader"));
                
                historyLine.executeTask(incomingRequest);
                this.setStatus(historyLine.getStatus());
                if(this.getStatus() != Status.SUCCEEDED)
                {
                    throw new TsaException(this.getName() + "Error ocurred writing history for line: " + disbLine.getLineNumber() + ", item: " + disbLine.getItemNumber());
                }
            }
            }else{
            	this.setStatus(Status.SUCCEEDED);
            }
            
        }
        catch (Exception e) 
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}