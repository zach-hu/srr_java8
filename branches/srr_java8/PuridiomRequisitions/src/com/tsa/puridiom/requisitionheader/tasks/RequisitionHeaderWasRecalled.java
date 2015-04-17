package com.tsa.puridiom.requisitionheader.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;

/**
 *@author eBS Group mcvz
 */
public class RequisitionHeaderWasRecalled extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;

            String organizationId = (String) incomingRequest.get("organizationId");
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            String icHeader = requisitionHeader.getIcReqHeader().toString();
			String reqNumber = requisitionHeader.getRequisitionNumber();
			String userId = requisitionHeader.getRequisitionerCode();
            String userDisplayName = UserManager.getInstance().getUser(organizationId,userId).getDisplayName();

            incomingRequest.put("Message", " Approval request has been withdrawn. Requisition " + reqNumber + " was recalled by "+ userDisplayName + ".");
            incomingRequest.put("approvalAbleVerification", "N");
            ret = "true";
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return ret;
    }
}
