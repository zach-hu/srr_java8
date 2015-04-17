/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.PoHeaderHistory.java
 *
 */
package com.tsa.puridiom.invhistory.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InventoryHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            InvItem newInvItem = (InvItem)incomingRequest.get("invItem");
            if(!Utility.isEmpty(newInvItem.getItemNumber()))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("inventory-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
                newIncomingRequest.put("historyreason", incomingRequest.get("order_historyreason"));
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                
                newIncomingRequest.put("newHistoryInvItem", newInvItem);
                newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));

                process.executeProcess(newIncomingRequest);
                this.setStatus(process.getStatus());
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}