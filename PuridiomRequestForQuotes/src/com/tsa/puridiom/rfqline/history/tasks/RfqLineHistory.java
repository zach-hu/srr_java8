/*
 * Created on August 27, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqline.tasks.RfqLineHistory.java
 *
 */
package com.tsa.puridiom.rfqline.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RfqLineHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RfqLine newLine = (RfqLine)incomingRequest.get("newHistoryRfqLine");

            if(!Utility.isEmpty(newLine.getRfqNumber()))
            {
	            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqline-history.xml");

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				String oid = (String)incomingRequest.get("organizationId");
				newIncomingRequest.put("organizationId", oid);
				newIncomingRequest.put("rfqHeader", incomingRequest.get("rfqHeader"));
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("newHistoryRfqLine", newLine);
				newIncomingRequest.put("autoReleased", incomingRequest.get("autoReleased"));
				newIncomingRequest.put("historyreason", incomingRequest.get("historyreason"));

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