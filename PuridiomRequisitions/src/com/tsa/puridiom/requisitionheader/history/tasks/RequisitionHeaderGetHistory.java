/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.history.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.requisitionheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.requisitionheader.history.RequisitionHistorySetupValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionHeaderGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader newHeader	= (RequisitionHeader)incomingRequest.get("newHistoryRequisitionHeader");
            String errorStr = HiltonUtility.ckNull((String) incomingRequest.get("errorStr"));
            String employeeNo = HiltonUtility.ckNull((String) incomingRequest.get("employeeNo"));
            String createFromSaveAs = HiltonUtility.ckNull((String) incomingRequest.get("createFromSaveAs"));
            String deferTo = (String) incomingRequest.get("deferTo") ;
            
            if(newHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Requisition Header was not found!");
            }

            RequisitionHistorySetupValues historyBuild = new RequisitionHistorySetupValues();
            historyBuild.setOrganizationId((String)incomingRequest.get("organizationId"));
            historyBuild.setUserId((String)incomingRequest.get("userId"));
            historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));
            historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));
            historyBuild.setExtraIc(incomingRequest.get("BuyerRemarks_icLine"));
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
                       
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
                rejectedBy = UserManager.getInstance().getUser(
                        (String)incomingRequest.get("organizationId"), rejectedBy).getDisplayName();
            }
            historyBuild.setRejectedBy(rejectedBy);

            history = historyBuild.getHeaderHistoryLog(newHeader);
            
            if(! HiltonUtility.isEmpty(deferTo)){
            	String deferredTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), deferTo).getDisplayName();
            	history.setDescription("Requisition " + newHeader.getRequisitionNumber() + " deferred to " + deferredTo + ".");
            	if (incomingRequest.containsKey("ApprovalLog_approverNotes") && history != null ) {
			            String approvalNotes = (String) incomingRequest.get("ApprovalLog_approverNotes");
			            if (!HiltonUtility.isEmpty(approvalNotes)) {
			                history.setDescription(history.getDescription() + " Approval Notes: " + approvalNotes);
			            }
	            }
            } else {
	            if(errorStr.equalsIgnoreCase("Y"))
	            {
	            	history.setDescription("Requisition " + newHeader.getRequisitionNumber() + " rejected by FDCS. Employee# " + employeeNo + " is Invalid.");
	            }
	            else if(!HiltonUtility.isEmpty(createFromSaveAs))
	            {
	            	history.setDescription(createFromSaveAs);
	            }
	            else
	            {
		            if (incomingRequest.containsKey("RequisitionHeader_noteCancel") && ( DocumentStatus.CANCELLED.compareTo(history.getStatus()) == 0 || DocumentStatus.CLOSED.compareTo(history.getStatus()) == 0 )) {
		            	history.setDescription(history.getDescription() + " Reason:" + incomingRequest.get("RequisitionHeader_noteCancel"));
		            }
		
		            if (incomingRequest.containsKey("ApprovalLog_approverNotes") && history != null ) {
			            String approvalNotes = (String) incomingRequest.get("ApprovalLog_approverNotes");
			            if (!HiltonUtility.isEmpty(approvalNotes)) {
			                history.setDescription(history.getDescription() + " Approval Notes: " + approvalNotes);
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
        return history;
    }

}
