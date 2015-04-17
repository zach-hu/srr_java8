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

public class RequisitionLineNotOnOrderAutoawardByLine extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        List reqLines = (List)incomingRequest.get("rqlListAutoAwardedByLine");
        if(reqLines == null)
        {
            this.setStatus(Status.FAILED);
            throw new Exception("Requisition Lines not found!");
        }
        int lineStatus = 0;
        lineStatus = reqLines.size();

        if (lineStatus > 0)
        {
        	  incomingRequest.put("requisitionLines", reqLines);
        }

        this.setStatus(Status.SUCCEEDED);
        return new BigDecimal(lineStatus);
    }

}
