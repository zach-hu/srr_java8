/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.requisitionheader.tasks.PoLineHistory.java
 *
 */
package com.tsa.puridiom.poline.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoLineHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            PoLine newLine = (PoLine)incomingRequest.get("newHistoryPoLine");
            if(!Utility.isEmpty(newLine.getPoNumber()) && !newLine.getPoNumber().equals("N/A"))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("poline-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                String oid = (String)incomingRequest.get("organizationId");
                newIncomingRequest.put("organizationId", oid);
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("poHeader", incomingRequest.get("poHeader"));
                newIncomingRequest.put("newHistoryPoLine", newLine);
                newIncomingRequest.put("autoReleased", incomingRequest.get("autoReleased"));
                newIncomingRequest.put("historyreason", incomingRequest.get("historyreason"));
                newIncomingRequest.put("writehistory", incomingRequest.get("writehistory"));
                newIncomingRequest.put("originalQuantity", incomingRequest.get("originalQuantity"));
                newIncomingRequest.put("originalPrice", incomingRequest.get("originalPrice"));

                if (incomingRequest.containsKey("PoLine_icReqLineOld") && incomingRequest.containsKey("PoLine_icReqLine"))
                {
                	if ( !((String) incomingRequest.get("PoLine_icReqLineOld")).equals( (String)incomingRequest.get("PoLine_icReqLine") ) )
                	{
                		newIncomingRequest.put("PoLine_requisitionNumber", incomingRequest.get("PoLine_requisitionNumber"));
                	}
                }

                String forwadedTo = (String)incomingRequest.get("forwardedTo");
                forwadedTo = UserManager.getInstance().getUser(oid, forwadedTo).getDisplayName();
                newIncomingRequest.put("forwardedTo", forwadedTo);
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