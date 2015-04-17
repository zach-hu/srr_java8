/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionline.tasks.RequisitionLineNotOnOrder.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class RequisitionLineNotOnOrder extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        List reqLines = (List)incomingRequest.get("requisitionLines");
        if(reqLines == null)
        {
            this.setStatus(Status.FAILED);
            throw new Exception("Requisition Lines not found!");
        }
        int lineStatus = 0;
        List tempItemsList = new ArrayList();
        for (Iterator iter = reqLines.iterator(); iter.hasNext();)
        {
            RequisitionLine reqLine = (RequisitionLine) iter.next();
            if (!Utility.isEmpty(reqLine.getStatus()))
            {
                //if(!(reqLine.getStatus().compareTo(DocumentStatus.REQ_APPROVED) >= 0 && reqLine.getStatus().compareTo(DocumentStatus.PO_INPROGRESS) == -1))
                if(reqLine.getStatus().equals(DocumentStatus.REQ_APPROVED))
                {
                    tempItemsList.add(reqLine);
                    lineStatus++;
                }
            }
        }
        if(lineStatus > 0)
        {
            incomingRequest.put("requisitionLines", tempItemsList);
        }
        this.setStatus(Status.SUCCEEDED);
        return new BigDecimal(lineStatus);
    }

}
