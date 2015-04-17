package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.receipt.exception.ReceiptRejectedItemException;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class QueueAddRejectedActionTaken extends Task {

    public Object executeTask(Object object) throws Exception {
        Map incomingRequest = (Map)object;
        Object result = null;

        try {
            String	organizationId = (String)incomingRequest.get("organizationId");
            String	userId = (String)incomingRequest.get("userId");
            ReceiptHeader originalReceiptHeader = (ReceiptHeader) incomingRequest.get("originalReceiptHeader");
            ReceiptLine originalReceiptLine = (ReceiptLine) incomingRequest.get("originalReceiptLine");
            ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
            ReceiptLine receiptLine = null;
            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
            PoLine poLine = (PoLine) incomingRequest.get("poLine");
            List receiptLineList = (List) incomingRequest.get("receiptLineList");

            if(originalReceiptHeader == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException( "Original ReceiptHeader was not found!");
            }
            if(originalReceiptLine == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException("Original ReceiptLine was not found!");
            }
            if(receiptHeader == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException(" ReceiptHeader was not found!");
            }
            if(receiptLineList == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException(" ReceiptLineList was not found!");
            }
            if (receiptLineList.size() > 0) {
                receiptLine = (ReceiptLine) receiptLineList.get(0);
            }
            if(receiptLine == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException(" ReceiptLine was not found!");
            }
            if(poHeader == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException(" PoHeader was not found!");
            }
            if(poLine == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException(" PoLine was not found!");
            }
            String	buyer = poHeader.getBuyerCode();

            if (Utility.isEmpty(buyer)) {
	            this.setStatus(Status.FAILED);
	            throw new ReceiptRejectedItemException(" Buyer  was not found!");
            }

            String	receiptNumber = receiptHeader.getReceiptNumber();
            String	originalReceiptNumber = originalReceiptHeader.getReceiptNumber();
            BigDecimal qtyRejected = originalReceiptLine.getQtyRejected();
            String	uom = poLine.getUmCode();

            //if (qtyRejected != null) {
//                qtyRejected = qtyRejected.multiply(new BigDecimal(-1));
//            }
            if (!Utility.isEmpty("uom")) {
                uom = "(" + uom + ")";
            }

            incomingRequest.put("SendQueue_doctype", "REC");
            incomingRequest.put("SendQueue_docic", originalReceiptHeader.getIcRecHeader().toString());

            if (!Utility.isEmpty(receiptNumber))
            {
                StringBuffer subject = new StringBuffer("Return Receipt created for rejected item(s)");
                StringBuffer message = new StringBuffer("");

                message.append(OrderType.toString(poHeader.getPoType(), organizationId) + " #: " + poHeader.getPoNumber());
                message.append("\nLine Item #:" + poLine.getLineNumber());
                message.append("\nItem/Part #: " + poLine.getItemNumber());
                message.append("\nDescription:  " + poLine.getDescription());
                message.append("\n\nReceived/Rejected/Returned By: " + UserManager.getInstance().getUser(organizationId, userId).getDisplayName());
                message.append("\n\nOriginal Receipt #: " + originalReceiptNumber);
                message.append("\nReceived: " + originalReceiptLine.getQtyReceived() + " " + uom);
                message.append("\nRejected: " + originalReceiptLine.getQtyRejected() + " " + uom);
                message.append("\nAccepted: " + originalReceiptLine.getQtyAccepted() + " " + uom);
                message.append("\nRejection Code: " + originalReceiptLine.getRejectionCode());
                message.append("\n\nReturn Receipt #: " + receiptNumber);
                message.append("\nRMA #: " + receiptHeader.getPackingSlip());
                message.append("\nDisposition Code: " + receiptLine.getDispositionCode());
                message.append("\n\nNo action is required from you at this time.");

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, userId).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(organizationId, buyer).getMailId() );
                incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
                incomingRequest.put("SendQueue_messagetext", message.toString());

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Receipt: " + originalReceiptNumber );
            }
        }
        catch(ReceiptRejectedItemException re) {
            throw new ReceiptRejectedItemException(re.getMessage() +   "  The buyer will not be notified that the rejected items have been returned!");
        }
        catch (Exception e) {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}