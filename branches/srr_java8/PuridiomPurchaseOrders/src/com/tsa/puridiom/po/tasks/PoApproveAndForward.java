package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import java.util.Date;
import java.util.Map;

public class PoApproveAndForward extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        String	userId = (String) incomingRequest.get("userId") ;

        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        Date today = Dates.getCurrentDate(userTimeZone);

        String	newStatus = (String) incomingRequest.get("newStatus") ;
        PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader") ;
        if (newStatus == null) {
            newStatus = DocumentStatus.PO_AWARDED;
        }

        incomingRequest.put("newStatus",newStatus) ;
        poHeader.setStatus(newStatus);
        if (newStatus.equals(DocumentStatus.PO_AWARDED)) {
        	poHeader.setAuthorizationCode(userId) ;
        	poHeader.setAppDate(today);
        	poHeader.setAppBy(userId) ;
        	poHeader.setApproved("Y") ;
        }
        this.setStatus(Status.SUCCEEDED);
        return null ;
    }
}
