/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.poline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class PoLineSetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	Map incomingRequest = (Map)object;
        try
        {
            PoLine poLine = (PoLine) incomingRequest.get("poLine");
            if(poLine == null)
            {
            	Log.error(this, "History needs a line item!");
            	this.setStatus(Status.SUCCEEDED);
            }
            else
            {
	            PoLineHistory historyLine = new PoLineHistory();

	            incomingRequest.put("newHistoryPoLine", poLine);
	            incomingRequest.put("poHeader", incomingRequest.get("poHeader"));
	            incomingRequest.put("autoReleased", "N");
	            incomingRequest.put("historyreason", incomingRequest.get("order_historyreason"));

	            historyLine.executeTask(incomingRequest);
	            this.setStatus(historyLine.getStatus());
            }

            if(this.getStatus() != Status.SUCCEEDED)
            {
                throw new TsaException(this.getName() + "Error ocurred writing history for line: " + poLine.getLineNumber() + ", item: " + poLine.getItemNumber());
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