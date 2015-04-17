package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.List;

import java.util.Map;

public class RfqSetStatus extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            String newStatus = (String) incomingRequest.get("newStatus") ;
            if (newStatus == null) {
                newStatus = DocumentStatus.RFQ_PURCHASING ;
            }

            RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader") ;
            List rfqLineList = (List) incomingRequest.get("rfqLineList") ;

            rfqHeader.setStatus(newStatus);

            if (newStatus.equals(DocumentStatus.CANCELLED)) {
            	rfqHeader.setIcReqHeader(new BigDecimal(0));
            	rfqHeader.setRequisitionNumber("");
            }

            Log.debug(this, "Solicitation " + rfqHeader.getRfqNumber() + " newStatus is: " + newStatus);

            for (int i=0; i < rfqLineList.size(); i++) {
                RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
                if(!rfqLine.getStatus().equals(DocumentStatus.CLOSED) && !rfqLine.getStatus().equals(DocumentStatus.CANCELLED))
                {
                	rfqLine.setStatus(newStatus) ;
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An error occurred setting the new status on the current Solicitation ", e);
        }
        return null ;
    }
}
