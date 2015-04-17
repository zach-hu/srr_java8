/*
 * Created on Jul 21, 2009
 *
 * @author  * johann
 * project: HiltonRequisitions
 * package com.puridiom.hilton.requisitionheader.tasks.PoHeaderHistory.java
 *
 */
package com.tsa.puridiom.poheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.historylog.tasks.PoSetupValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderHistoryChangeRequsitioner extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader") ;
            String organizationId = (String)incomingRequest.get("organizationId");
            String sAutoReleased = (String)incomingRequest.get("autoReleased");
            boolean autoReleased = Utility.ckNull(sAutoReleased).equals("Y");
            String NewReq = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_requisitionerCode"));
            String historyReason = HiltonUtility.ckNull((String) incomingRequest.get("historyreason"));
            String resetStatus = HiltonUtility.ckNull((String) incomingRequest.get("Reset_status"));
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            String userId = (String) incomingRequest.get("userId");
            String userFirstName = "";
            String userLastName = "";
            if(!HiltonUtility.isEmpty(poHeader.getRequisitionerCode()))
            {
            	userFirstName = UserManager.getInstance().getUser(organizationId, poHeader.getRequisitionerCode()).getFirstName();
            	userLastName = UserManager.getInstance().getUser(organizationId, poHeader.getRequisitionerCode()).getLastName();
            }
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

            history = historyBuild.getHeaderHistoryLog(poHeader);
            if(history != null)
            {
	            if(!history.getUserid().equalsIgnoreCase("AUTORELEASE"))
	            {
	            	history.setUserid(userId);
	            }
            }
            String description = "";

            if(resetStatus.equalsIgnoreCase("Y"))
            {
            	description = "Reason for Status Change: " + historyReason;
            }
            else
            {
            	description = "User " + userId + " changed requisitioner to: " + userFirstName + " " + userLastName;
            	description = description + ", Reason: " + historyReason;
            }
			history.setDescription(description);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, e.getMessage());
            e.printStackTrace();
            throw new TsaException(this.getName(), e);
        }
        return history;
	}
}