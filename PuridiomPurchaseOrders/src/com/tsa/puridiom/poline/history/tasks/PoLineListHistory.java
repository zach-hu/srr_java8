/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.poline.history.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineListHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            List poLineList = (List)incomingRequest.get("poLineList");
            if(poLineList == null)
            {
                poLineList = (List)incomingRequest.get("poLineList");
            }

            String icPoLineString = "";
            String fromPoItemPage = HiltonUtility.ckNull((String) incomingRequest.get("fromPoItemPage"));
            if (fromPoItemPage.equals("Y"))
            {
            	icPoLineString = (String) incomingRequest.get("PoLine_icPoLine");
            }

            if (poLineList != null)
            {
                for (Iterator iter = poLineList.iterator(); iter.hasNext();)
                {
                    PoLine poLine = (PoLine) iter.next();
                    PoLineHistory historyLine = new PoLineHistory();

                    if ( icPoLineString.equals(poLine.getIcPoLine().toString()) || !fromPoItemPage.equals("Y") )
                    {
	                    incomingRequest.put("newHistoryPoLine", poLine);
	                    incomingRequest.put("poHeader", incomingRequest.get("poHeader"));
	                    incomingRequest.put("autoReleased", incomingRequest.get("poFromRelease"));

	                    historyLine.executeTask(incomingRequest);
	                    this.setStatus(historyLine.getStatus());
	                    if(this.getStatus() != Status.SUCCEEDED)
	                    {
	                        throw new TsaException(this.getName() + "Error ocurred writing history for line: " + poLine.getLineNumber() + ", item: " + poLine.getItemNumber());
	                    }
                    }
                }
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            throw e;
           //throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}