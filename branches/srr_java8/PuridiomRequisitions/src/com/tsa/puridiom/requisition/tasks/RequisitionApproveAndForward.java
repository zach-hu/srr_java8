package com.tsa.puridiom.requisition.tasks;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import java.util.Date;
import java.util.Map;

public class RequisitionApproveAndForward extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        String	userId = (String) incomingRequest.get("userId") ;

        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        Date today = Dates.getCurrentDate(userTimeZone);

        String	newStatus = (String) incomingRequest.get("newStatus") ;
        RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
        if (newStatus == null) {
            newStatus = DocumentStatus.REQ_APPROVED ;
        }
        //No need to approve here.. genral_approval-rules do it now. (Approval Rule of type Z)
        /*else if (rqh.getRequisitionType().equals(RequisitionType.PURCHASE_REQUEST) && rqh.getRqSubType().equals("X") && rqh.getTotal().compareTo(new BigDecimal(200.00)) <= 0) {
            newStatus = DocumentStatus.REQ_APPROVED ;
        }*/

        if(rqh.getRequisitionType().equals(RequisitionType.INVENTORY_RETURN))
        {
            newStatus = DocumentStatus.INV_RETURNING;
        }
        else if(rqh.getRequisitionType().equals(RequisitionType.PRICING_REQUEST))
        {
            newStatus = DocumentStatus.REQ_FORWARDED;
        }

        incomingRequest.put("newStatus",newStatus) ;
        rqh.setStatus(newStatus);
        if (newStatus.equals(DocumentStatus.REQ_APPROVED)) {
            rqh.setAuthorizationCode(userId) ;
            rqh.setAppDate(today);
            rqh.setAppBy(userId) ;
            rqh.setApproved("Y") ;
        }

        /*rqh.setAssignedDate(today);
        if (requisitionType.equals("S") || requisitionType.equals("I") ||requisitionType.equals("T") || requisitionType.equals("Z")) {
            rqh.setAssignedBuyer("SUPPLYROOM") ;
        } else {
            rqh.setAssignedBuyer("PURCHASING") ;
        }
        */
        return null ;
    }
}
