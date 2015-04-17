/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.PoHeaderHistory.java
 *
 */
package com.tsa.puridiom.poheader.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader newHeader = (PoHeader)incomingRequest.get("poHeader");
            if(!Utility.isEmpty(newHeader.getPoNumber()))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("po-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("autoReleased", incomingRequest.get("poFromRelease"));
                newIncomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
                newIncomingRequest.put("historyreason", incomingRequest.get("order_historyreason"));
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                newIncomingRequest.put("PoHeader_buyerRemarks", incomingRequest.get("PoHeader_buyerRemarks"));
                newIncomingRequest.put("BuyerRemarks_icLine", incomingRequest.get("PoHeader_icPoHeader"));
                newIncomingRequest.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
                newIncomingRequest.put("receiptHeader", incomingRequest.get("receiptHeader"));
                newIncomingRequest.put("receiptMethod", incomingRequest.get("receiptMethod"));

                String deferTo = (String)incomingRequest.get("deferTo") ;
                
                if(!HiltonUtility.isEmpty(deferTo)){
                	newIncomingRequest.put("deferTo", deferTo);
                }
                
                if (incomingRequest.containsKey("ApprovalLog_approverNotes")) {
                    newIncomingRequest.put("ApprovalLog_approverNotes", incomingRequest.get("ApprovalLog_approverNotes"));
                }
                
                newIncomingRequest.put("newHistoryPoHeader", newHeader);
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