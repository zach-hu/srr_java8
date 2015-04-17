/*
 * Created on Aug 20, 2007
 *
 * @author  Kelli
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poheader.history.tasks.PoHeaderGetAcknowledgedHistory.java
 */
package com.tsa.puridiom.poheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.historylog.tasks.PoSetupValues;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderGetAcknowledgedHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map) object;
            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

            if(poHeader == null) {
                this.setStatus(Status.FAILED);
                throw new Exception(" PoHeader was not found!");
            }
            String  poNumber = poHeader.getPoNumber();
            String  organizationId = (String)incomingRequest.get("organizationId");
            String  vendorId = (String)incomingRequest.get("vendorId");
            String  userId = (String)incomingRequest.get("userId");
            String  userName = (String) incomingRequest.get("userName");

            userId = userId.toLowerCase();
            userName = Utility.ckNull(userName);

            if (!Utility.isEmpty(poNumber))
            {
                String orderTypeNumber = OrderType.toString(poHeader.getPoType(), organizationId) + " #" + poHeader.getDisplayPoNumber(false);

                PoSetupValues historyBuild = new PoSetupValues();
                historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));
                history = historyBuild.poHeaderSetup(history, poHeader);

                history.setUserid(vendorId);
                history.setDescription(orderTypeNumber + " acknowledged by " + userName + "[" + userId + "]");
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
