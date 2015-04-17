/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poheader.history.tasks.PoHeaderGetHistory.java
 *
 */
package com.tsa.puridiom.poheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.historylog.tasks.PoSetupValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader newHeader	= (PoHeader)incomingRequest.get("newHistoryPoHeader");
            String organizationId = (String)incomingRequest.get("organizationId");
            String sAutoReleased = (String)incomingRequest.get("autoReleased");
            boolean autoReleased = Utility.ckNull(sAutoReleased).equals("Y");
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            String userId = (String) incomingRequest.get("userId");
            String deferTo = (String) incomingRequest.get("deferTo") ;
            forwardedTo = UserManager.getInstance().getUser(organizationId, forwardedTo).getDisplayName() ;

            PoSetupValues historyBuild = new PoSetupValues();

            historyBuild.setOrganizationId(organizationId);
            historyBuild.setAutoReleased(autoReleased);
            historyBuild.setNextUser(forwardedTo);
            historyBuild.setReason((String)incomingRequest.get("historyreason"));
            historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));
            historyBuild.setBuyerRemarks((String)incomingRequest.get("PoHeader_buyerRemarks"));
            historyBuild.setExtraIc(incomingRequest.get("BuyerRemarks_icLine"));
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
            historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));
            historyBuild.setRejectedBy(userId);
            historyBuild.setApproverNotes((String)incomingRequest.get("ApprovalLog_approverNotes"));

            history = historyBuild.getHeaderHistoryLog(newHeader);
            if(history != null)
            {
	            if(!history.getUserid().equalsIgnoreCase("AUTORELEASE"))
	            {
	            	history.setUserid(userId);
	            }
            }

            if(! HiltonUtility.isEmpty(deferTo))
            {
            	String deferredTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), deferTo).getDisplayName();
            	history.setDescription("PO " + newHeader.getPoNumber() + " deferred to " + deferredTo + ".");
            	
            	if (incomingRequest.containsKey("ApprovalLog_approverNotes") && history != null ) {
		            String approvalNotes = (String) incomingRequest.get("ApprovalLog_approverNotes");
		            if (!HiltonUtility.isEmpty(approvalNotes)) {
		                history.setDescription(history.getDescription() + " Approval Notes: " + approvalNotes);
		            }
	            }
            }else{
				RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
				if (requisitionHeader != null)
				{
					String description = "";
					if (requisitionHeader.getRequisitionType().equals("C"))
					{
						description = "Change Request #" + newHeader.getRequisitionNumber() + " has been placed on Order #" + newHeader.getPoNumber();
						if (newHeader.getRevisionNumber().intValue() > 0)
							description = description + " Revision " + newHeader.getRevisionNumber().intValue();
						if (!HiltonUtility.isEmpty(newHeader.getVendorId()))
							description = description + " to Supplier " + VendorManager.getInstance().getVendorName(organizationId, newHeader.getVendorId());
						if (!HiltonUtility.isEmpty(description))
							history.setDescription(description);
					}
				}
	
				ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
				String receiptMethod = (String)incomingRequest.get("receiptMethod");
				if (receiptHeader != null && receiptMethod != null && receiptMethod.equalsIgnoreCase("ReceiveByPackage"))
				{
					String receivedBy = "";
					UserProfile receivedByUser = UserManager.getInstance().getUser(organizationId, receiptHeader.getReceivedBy());
					if (receivedByUser != null) {
						receivedBy = receivedByUser.getDisplayName();
					}
	
					String forwardTo = "";
					UserProfile forwardToUser = UserManager.getInstance().getUser(organizationId, receiptHeader.getForwardTo());
					if (forwardToUser != null) {
						forwardTo = forwardToUser.getDisplayName();
					}
	
					String description = "";
					if (receiptHeader.getReceiptType().equals(ReceiptType.ORIGINAL)) {
						description = " Received " + receiptHeader.getPkgsReceived().toString() + " package(s) on Receipt # " + receiptHeader.getReceiptNumber() + ".";
						if (!HiltonUtility.isEmpty(receivedBy)) {
							description += " Received by " + receivedBy + ".";
						}
						if (!HiltonUtility.isEmpty(forwardTo)) {
							description += " Forward to " + forwardTo + ".";
						}
					}
					history.setDescription(description);
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
