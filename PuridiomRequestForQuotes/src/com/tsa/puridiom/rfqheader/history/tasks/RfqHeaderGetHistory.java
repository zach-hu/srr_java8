/*
 * Created on August 27, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqheader.history.tasks.RfqHeaderGetHistory.java
 *
 */
package com.tsa.puridiom.rfqheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.historylog.tasks.RfqSetupValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RfqHeaderGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader newHeader	= (RfqHeader)incomingRequest.get("newHistoryRfqHeader");
            String organizationId = (String) incomingRequest.get("organizationId");
            String sAutoReleased = (String)incomingRequest.get("autoReleased");
            boolean autoReleased = Utility.ckNull(sAutoReleased).equals("Y");
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            String deferTo = (String) incomingRequest.get("deferTo") ;
            forwardedTo = UserManager.getInstance().getUser(organizationId, forwardedTo).getDisplayName() ;

            RfqSetupValues historyBuild = new RfqSetupValues();

            historyBuild.setOrganizationId(organizationId);
            historyBuild.setAutoReleased(autoReleased);
            historyBuild.setNextUser(forwardedTo);
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
            historyBuild.setApproverNotes((String)incomingRequest.get("ApprovalLog_approverNotes"));
            String userId = (String) incomingRequest.get("userId");
            historyBuild.setRejectedBy(userId);
            history = historyBuild.getHeaderHistoryLog(newHeader);

            if(! HiltonUtility.isEmpty(deferTo))
            {
            	String deferredTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), deferTo).getDisplayName();
            	history.setDescription("Solicitation " + newHeader.getRfqNumber() + " deferred to " + deferredTo + ".");

            	if (incomingRequest.containsKey("ApprovalLog_approverNotes") && history != null ) {
		            String approvalNotes = (String) incomingRequest.get("ApprovalLog_approverNotes");
		            if (!HiltonUtility.isEmpty(approvalNotes)) {
		                history.setDescription(history.getDescription() + " Approval Notes: " + approvalNotes);
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
        return history;
    }

}
