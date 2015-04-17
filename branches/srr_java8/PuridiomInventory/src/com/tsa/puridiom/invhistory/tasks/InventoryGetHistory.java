/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poheader.history.tasks.PoHeaderGetHistory.java
 *
 */
package com.tsa.puridiom.invhistory.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.historylog.tasks.PoSetupValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InventoryGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            InvItem newInvItem	= (InvItem) incomingRequest.get("newHistoryInvItem");
            String organizationId = (String)incomingRequest.get("organizationId");
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            String userId = (String) incomingRequest.get("userId");
            forwardedTo = UserManager.getInstance().getUser(organizationId, forwardedTo).getDisplayName() ;

            InventorySetupValues historyBuild = new InventorySetupValues();

            historyBuild.setOrganizationId(organizationId);
            historyBuild.setNextUser(forwardedTo);
            historyBuild.setReason((String)incomingRequest.get("historyreason"));
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
            historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));
            historyBuild.setHistoryStatus((String)incomingRequest.get("historyStatus"));
            historyBuild.setForwardedTo(forwardedTo);
            historyBuild.setUserId(userId);

            history = historyBuild.getHeaderHistoryLog(newInvItem);

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
