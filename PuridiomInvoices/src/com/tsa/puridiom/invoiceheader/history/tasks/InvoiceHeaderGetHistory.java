/*
 * Created on October 17, 2006
 *
 * project: HiltonInvoices
 * package com.tsa.puridiom.invoiceheader.history.tasks.InvoiceHeaderGetHistory.java
 *
 */
package com.tsa.puridiom.invoiceheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.historylog.tasks.InvoiceSetupValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceHeaderGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map) object;
            InvoiceHeader newHeader	= (InvoiceHeader) incomingRequest.get("newHistoryInvoiceHeader");
            String deferTo = (String) incomingRequest.get("deferTo") ;
            if (newHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Invoice Header was not found!");
            }

            /*
            String organizationId = (String) incomingRequest.get("organizationId");
            String sAutoReleased = (String)incomingRequest.get("autoReleased");
            boolean autoReleased = Utility.ckNull(sAutoReleased).equals("Y");
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            forwardedTo = UserManager.getInstance().getUser(organizationId, forwardedTo).getDisplayName() ;

            historyBuild.setOrganizationId(organizationId);
            historyBuild.setAutoReleased(autoReleased);
            historyBuild.setNextUser(forwardedTo);
			*/

            InvoiceSetupValues historyBuild = new InvoiceSetupValues();
            historyBuild.setOrganizationId((String)incomingRequest.get("organizationId"));
            historyBuild.setUserId((String)incomingRequest.get("userId"));
            historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));
            historyBuild.setApproverNotes((String)incomingRequest.get("ApprovalLog_approverNotes"));

            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            if (forwardedTo == null)
            {
                forwardedTo = "";
            }
            else
            {
                forwardedTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), forwardedTo).getDisplayName();
            }
            historyBuild.setForwardedTo(forwardedTo);

            String rejectedBy = (String)incomingRequest.get("rejectedBy");
            if (rejectedBy == null)
            {
                rejectedBy = "";
            }
            else
            {
                rejectedBy = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), rejectedBy).getDisplayName();
            }
            historyBuild.setRejectedBy(rejectedBy);

            history = historyBuild.getHeaderHistoryLog(newHeader);
            
            if(! HiltonUtility.isEmpty(deferTo))
            {
            	String deferredTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), deferTo).getDisplayName();
            	history.setDescription("Invoice " + newHeader.getInvoiceNumber() + " deferred to " + deferredTo + ".");
            	
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
