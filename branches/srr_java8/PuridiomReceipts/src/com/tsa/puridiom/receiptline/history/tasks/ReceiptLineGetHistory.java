/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.history.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.receiptline.history.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.common.documents.*;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class ReceiptLineGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            ReceiptLine newLine	= (ReceiptLine)incomingRequest.get("newHistoryReceiptLine");
            ReceiptHeader header = (ReceiptHeader)incomingRequest.get("receiptHeader");

            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Receipt Line was not found!");
            }

            ReceiptLineSetupHistoryValues historyBuild = new ReceiptLineSetupHistoryValues();
            historyBuild.setOrganizationId((String)incomingRequest.get("organizationId"));
            historyBuild.setUserId((String)incomingRequest.get("userId"));
            historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));
            historyBuild.setPoLine((PoLine)incomingRequest.get("PoLine"));
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
            historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));

            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            if (forwardedTo == null)
            {
                forwardedTo = "";
            }
            else
            {
                forwardedTo = UserManager.getInstance().getUser(
                        (String)incomingRequest.get("organizationId"), forwardedTo).getDisplayName();
            }
            historyBuild.setForwardedTo(forwardedTo);

            String rejectedBy = (String)incomingRequest.get("rejectedBy");
            if (rejectedBy == null)
            {
                rejectedBy = "";
            }
            else
            {
                rejectedBy = UserManager.getInstance().getUser(
                        (String)incomingRequest.get("organizationId"), rejectedBy).getDisplayName();
            }
            historyBuild.setRejectedBy(rejectedBy);

            String writehistory = (String)incomingRequest.get("writehistory");
            String originalQty = (String) incomingRequest.get("originalQuantity");
            String originalPrice = (String) incomingRequest.get("originalPrice");
            if (writehistory == null) {	writehistory = "N";	}
            if (HiltonUtility.isEmpty(originalQty)) {	originalQty = "-1";	}
            if (HiltonUtility.isEmpty(originalPrice)) {	originalPrice = "-1";	}
            BigDecimal bdOriginalQty = new BigDecimal(originalQty);
            BigDecimal bdOriginalPrice = new BigDecimal(originalPrice);
            historyBuild.setWriteHistory(writehistory);
            historyBuild.setOriginalQty(bdOriginalQty);
            historyBuild.setOriginalPrice(bdOriginalPrice);

            history = historyBuild.getLineHistoryLog(newLine, header);
            
            /** SRR - Task #495 - SRR - MSR Kitting Functionality
            if(historyBuild.getHistoryStatus().equals("MSRLINE")){
            	InvBinLocation invBinLocation = (InvBinLocation) incomingRequest.get("invBinLocation");
	            String description="Inventory Item #: "+incomingRequest.get("InvItem_itemNumber")+" Qty: "+invBinLocation.getQtyOnHand()+" stored in location: "+
	            invBinLocation.getLot()+"."+invBinLocation.getAisle()+"."+invBinLocation.getLocrow()+"."+invBinLocation.getTier()+"."+invBinLocation.getBin();
	    		history.setDescription(description);
            } */
            
            if (incomingRequest.containsKey("ReceiptLine_noteCancel") && ( DocumentStatus.CANCELLED.compareTo(history.getStatus()) == 0  || DocumentStatus.CLOSED.compareTo(history.getStatus()) == 0 ) ) {
            	history.setDescription(history.getDescription() + " Reason:" + incomingRequest.get("ReceiptLine_noteCancel"));
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
