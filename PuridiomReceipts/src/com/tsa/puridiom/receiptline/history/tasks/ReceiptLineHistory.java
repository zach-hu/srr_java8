/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.receiptline.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ReceiptLineHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            ReceiptLine newLine = (ReceiptLine)incomingRequest.get("newHistoryReceiptLine");
            if(!Utility.isEmpty(newLine.getReceiptNumber()) && !newLine.getReceiptNumber().equals("N/A"))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("receiptline-history.xml");

                Map newIncomingRequest = new HashMap();

                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("newHistoryRequisitionLine", newLine);
                newIncomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
                newIncomingRequest.put("rejectedBy", incomingRequest.get("rejectedBy"));
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                newIncomingRequest.put("PoLine", incomingRequest.get("PoLine"));
                newIncomingRequest.put("writehistory", incomingRequest.get("writehistory"));
                newIncomingRequest.put("originalQuantity", incomingRequest.get("originalQuantity"));
                newIncomingRequest.put("originalPrice", incomingRequest.get("originalPrice"));
                newIncomingRequest.put("newHistoryReceiptLine", newLine);
                if (incomingRequest.containsKey("ReceiptLine_noteCancel")) {
					newIncomingRequest.put("ReceiptLine_noteCancel", incomingRequest.get("ReceiptLine_noteCancel"));
				}
                newIncomingRequest.put("receiptHeader", incomingRequest.get("receiptHeader"));
                newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));
                newIncomingRequest.put("invBinLocation", incomingRequest.get("invBinLocation"));
                newIncomingRequest.put("InvItem_itemNumber", incomingRequest.get("InvItem_itemNumber"));
                
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