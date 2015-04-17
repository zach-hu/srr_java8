package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class QueueAddSupplierAcknowledged extends Task {

    public Object executeTask(Object object) throws Exception {
        Map incomingRequest = (Map)object;
        Object result = null;

        try {
            String	organizationId = (String)incomingRequest.get("organizationId");
            String  vendorId = HiltonUtility.ckNull((String)incomingRequest.get("vendorId"));
            String	userId = (String)incomingRequest.get("userId");
            String userName = (String) incomingRequest.get("userName");

            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

            if(poHeader == null) {
                this.setStatus(Status.FAILED);
                throw new Exception(" PoHeader was not found!");
            }
            String	buyer = poHeader.getBuyerCode();

            if (Utility.isEmpty(buyer)) {
	            this.setStatus(Status.FAILED);
	            throw new Exception(" Buyer  was not found!");
            }

            String	poNumber = poHeader.getPoNumber();

            userId = userId.toLowerCase();
            userName = Utility.ckNull(userName);

            if (!Utility.isEmpty(poNumber))
            {
                String orderTypeNumber = OrderType.toString(poHeader.getPoType(), organizationId) + " #: " + poHeader.getPoNumber();
                StringBuffer subject = new StringBuffer(OrganizationManager.getInstance().getOrganizationName(organizationId) + " " + orderTypeNumber + " - Acknowledged");
                StringBuffer message = new StringBuffer("");

                message.append("\n" + orderTypeNumber + " acknowledged on " + poHeader.getDateAcknowledged() + " by " + userName + "[" + userId + "]");
                message.append("\nSupplier Name: " + poHeader.getVendorName());
                message.append("\nRequired Date:  " + poHeader.getRequiredDate());
                message.append("\nOrder Total: " + poHeader.getTotal());
                message.append("\n");
                message.append("\nPurpose: " + poHeader.getInternalComments());

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", userId);  //userId is the mail id for bidboard users
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(organizationId, buyer).getMailId() );
                incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
                incomingRequest.put("SendQueue_messagetext", message.toString());
                incomingRequest.put("SendQueue_doctype", "POA");
                incomingRequest.put("SendQueue_docic", poHeader.getIcPoHeader().toString());
                incomingRequest.put("SendQueue_owner", vendorId);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Order Acknowledgement: " + poNumber );
            }
        }
        catch (Exception e) {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}