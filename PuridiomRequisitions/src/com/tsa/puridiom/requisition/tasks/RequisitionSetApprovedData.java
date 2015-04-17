
package com.tsa.puridiom.requisition.tasks;

import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.processengine.Status;

public class RequisitionSetApprovedData extends Task
{
    public Object executeTask(Object object) throws Exception
    {

        Map incomingRequest = (Map) object;

       try
       {
    	   RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
           String newStatus = rqh.getStatus();

           //rqh.setStatus(newStatus);
           if(newStatus.equalsIgnoreCase(DocumentStatus.REQ_APPROVED))
           {
               String userTimeZone = (String) incomingRequest.get("userTimeZone");
               Date d_today = Dates.getCurrentDate(userTimeZone);

           		rqh.setApproved("Y");
           		rqh.setAppBy((String)incomingRequest.get("userId"));
           		rqh.setAppDate(d_today);
           }
           Log.debug(this, "Requisition " + rqh.getRequisitionNumber() + " newStatus is: " + newStatus);
           this.setStatus(Status.SUCCEEDED);

		}
       catch (Exception e)
       {
    	   this.setStatus(Status.SUCCEEDED);
    	   throw new TsaException("Approved Information could not be set. ", e);
		}

        return null ;
    }
}
