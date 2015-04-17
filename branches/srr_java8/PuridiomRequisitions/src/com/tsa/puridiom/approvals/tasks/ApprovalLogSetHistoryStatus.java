/*
 * Created on Apr 19, 2005
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class ApprovalLogSetHistoryStatus extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
            Map incomingRequest = (Map)object;
            List routingList = (List)incomingRequest.get("routingList");
            String newStatus = (String)incomingRequest.get("newStatus");
            if(newStatus.equals(DocumentStatus.REQ_APPROVED))
            {
                ret = HistoryStatus.REQ_APPROVED;
            }
            else
            {
                for(int i = 0; i < routingList.size(); i++)
                {
                    ApprovalLog app = (ApprovalLog)routingList.get(i);
                    if(app.getApproved().equals("A"))
                    {
                        if(i == 0)
                        {
                            //first time approving. Requisitioner just sent this to an approver
                            ret = HistoryStatus.REQ_FORWARDED;
                        }
                        else if(i == routingList.size() - 1 && newStatus.equals(DocumentStatus.REQ_APPROVED))
                        {
                            //requisition will be approved.
                            ret = HistoryStatus.REQ_APPROVED;
                        }
                        else
                        {
                            ret = HistoryStatus.REQ_FORWARDING;
                        }
                    }
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}