/*
 * Created on Sep 30, 2004
 *
 * @author  * renzo / Kelli
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks.PoLineUpdateReqLineStatus.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PoLineCancelUpdateRfqLineStatus extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;

            if (incomingRequest.containsKey("poLine"))
            {
                PoLine poLine = (PoLine) incomingRequest.get("poLine");

                if (poLine != null)
                {
                    String organizationId = (String) incomingRequest.get("organizationId");
                    String userId = (String) incomingRequest.get("userId");
                    DBSession dbsession = (DBSession) incomingRequest.get("dbsession");

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("rfqline-update-status.xml");
                    Map requestParameters = new HashMap();

                    String status = (String)incomingRequest.get("RfqLine_status");
                    BigDecimal	icRfqLine = poLine.getIcRfqLine();

                    requestParameters.put("organizationId",organizationId);
                    requestParameters.put("userId",userId);
                    requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                    requestParameters.put("dbsession", dbsession);
                    requestParameters.put("RfqLine_icRfqLine", icRfqLine.toString());
                    requestParameters.put("RfqLine_status", status);
                    Log.debug(this, "Updating Requisition Status from Po " + poLine.getPoNumber() + " line status " + status);

                    process.executeProcess(requestParameters);

                    if (process.getStatus() != Status.SUCCEEDED) {
                        throw new Exception("An error ocurred updating the requisition line status for po line #" + poLine.getLineNumber());
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
        return super.executeTask(object);
    }
}
