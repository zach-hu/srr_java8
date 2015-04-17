package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.encryptor.Encryptor;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.property.PropertiesManager;
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

public class QueueAddRejectedActionRequired extends Task {

    public Object executeTask(Object object) throws Exception {
        Map incomingRequest = (Map)object;
        Object result = null;

        try {
            String	organizationId = (String)incomingRequest.get("organizationId");
            String	userId = (String)incomingRequest.get("userId");
            ReceiptHeader originalReceiptHeader = (ReceiptHeader) incomingRequest.get("originalReceiptHeader");
            ReceiptLine originalReceiptLine = (ReceiptLine) incomingRequest.get("originalReceiptLine");
            PoHeader poHeader = (PoHeader) incomingRequest.get("originalPoHeader");
            PoLine poLine = (PoLine) incomingRequest.get("poLine");
            List originalReceiptLineList = null;

            if(originalReceiptHeader == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException("Original ReceiptHeader was not found!");
            }
            if(originalReceiptLine == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException("Original ReceiptLine was not found!");
            }
            if(poHeader == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException("PoHeader was not found!");
            }
            if(poLine == null) {
                poLine = originalReceiptLine.getPoLine();
            }
            if(poLine == null) {
                this.setStatus(Status.FAILED);
                throw new ReceiptRejectedItemException("PoLine was not found!");
            }
            String	buyer = poHeader.getBuyerCode();

            if (Utility.isEmpty(buyer)) {
	            this.setStatus(Status.FAILED);
	            throw new ReceiptRejectedItemException("Buyer  was not found!");
            }

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

            if (!Utility.isEmpty(originalReceiptNumber))
            {
                StringBuffer subject = new StringBuffer("Action required for rejected Item(s)");
                StringBuffer message = new StringBuffer("");
                String	buyerMailId = UserManager.getInstance().getUser(organizationId, buyer).getMailId();
                Encryptor enc = new Encryptor();
                String	encUid = enc.of_set(buyer);
                String	encOid =enc.of_set(organizationId);
                String	encMid = enc.of_set(buyerMailId);
                String	encIcPoHeader = enc.of_set(poHeader.getIcPoHeader().toString());
                String	urlLocation = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION","URL","http://my.puridiom.com");

                String partialLink = urlLocation + "/receipts/mail_return.jsp?";
                partialLink = partialLink + "ext=" + encUid + "&";
                partialLink = partialLink + "zat=" + encOid + "&";
                partialLink = partialLink + "ail=" + encMid + "&";
                partialLink = partialLink + "ciop=" + encIcPoHeader;

                String	createReturnLink = "<A HREF=\"" + partialLink + "\">Click here to create a Return Receipt now.</A>";

                message.append("<HTML>");
                message.append("<LINK REL=STYLESHEET TYPE=\"text/css\" HREF=\"" + urlLocation+  "/puridiom/system/styles/puridiom.css\">");
                message.append("<FORM>");
                message.append("<BODY>");
                message.append("<P>");
                message.append("<B>" + OrderType.toString(poHeader.getPoType(), organizationId) + " #: " + poHeader.getPoNumber() + "</B>");
                message.append("<BR>Line Item #:" + poLine.getLineNumber());
                message.append("<BR>Item/Part #: " + poLine.getItemNumber());
                message.append("<BR>Description:  " + poLine.getDescription());
                message.append("<BR><BR>Received/Rejected By: " + UserManager.getInstance().getUser(organizationId, userId).getDisplayName());
                message.append("<BR><BR>Original Receipt #: " + originalReceiptNumber);
                message.append("<BR>Received: " + originalReceiptLine.getQtyReceived() + " " + uom);
                message.append("<BR>Rejected: " + originalReceiptLine.getQtyRejected() + " " + uom);
                message.append("<BR>Accepted: " + originalReceiptLine.getQtyAccepted() + " " + uom);
                message.append("<BR>Rejection Code: " + originalReceiptLine.getRejectionCode());
                message.append("<BR><BR>" + createReturnLink);
                message.append("</P>");
                message.append("</BODY>");
                message.append("</FORM>");
                message.append("</HTML>");

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, userId).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", buyerMailId );
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
            throw new ReceiptRejectedItemException(re.getMessage() +   "  The buyer will not be notifiied that the rejected items require action!");
        }
        catch (Exception e) {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}