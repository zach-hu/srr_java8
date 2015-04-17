package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;

import java.util.Map;

public class RequisitionSetRqSubType extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            String newRqSubType = HiltonUtility.ckNull((String) incomingRequest.get("RequisitionHeader_rqSubType"));
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
            if(HiltonUtility.isEmpty(newRqSubType))
            {
            	newRqSubType = HiltonUtility.ckNull((String) rqh.getRqSubType());
            }
            
            rqh.setRqSubType(newRqSubType);

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
