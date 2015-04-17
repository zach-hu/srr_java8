package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;

import java.util.Map;

public class RequisitionSetStatus extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            String newStatus = (String) incomingRequest.get("newStatus") ;
            if (newStatus == null) {
                newStatus = DocumentStatus.REQ_APPROVED ;
            }

            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
            List reqLineList = (List) incomingRequest.get("requisitionLineList") ;
            
            if(rqh.getRequisitionType().equalsIgnoreCase("M") && newStatus.equals(DocumentStatus.REQ_REJECTED))
            {
            	newStatus = DocumentStatus.REQ_PLANNING_REJECTED;
            }
            rqh.setStatus(newStatus);
            Log.debug(this, "Requisition " + rqh.getRequisitionNumber() + " newStatus is: " + newStatus);

            for (int i=0; i < reqLineList.size(); i++) {
                RequisitionLine rql = (RequisitionLine) reqLineList.get(i);
                if(!rql.getStatus().equals(DocumentStatus.CLOSED) && !rql.getStatus().equals(DocumentStatus.CANCELLED))
                {
                	rql.setStatus(newStatus);
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred approving the current Requisition ", e);
        }
        return null ;
    }
}
