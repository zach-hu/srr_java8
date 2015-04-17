package com.tsa.puridiom.po.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoSetStatus extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            String newStatus = (String) incomingRequest.get("newStatus") ;
            if (Utility.isEmpty(newStatus)) {			newStatus = DocumentStatus.PO_AWARDED ;		}

            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader") ;
            List poLineList = (List) incomingRequest.get("poLineList") ;

            poHeader.setStatus(newStatus);
            Log.debug(this, poHeader.getDisplayPoNumber().toString() + " newStatus is: " + newStatus);

            for (int i=0; i < poLineList.size(); i++)
            {
                PoLine poLine = (PoLine) poLineList.get(i);
                if(!poLine.getStatus().equals(DocumentStatus.CLOSED) || !poLine.getStatus().equals(DocumentStatus.CANCELLED))
                {
                	poLine.setStatus(newStatus) ;
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error ocurred closing order ", e);
        }
        return null ;
    }
}
