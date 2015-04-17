package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class NotifyBuyerPoApproved extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            incomingRequest.put("SendQueue_doctype", "PO");
            incomingRequest.put("SendQueue_docic", poHeader.getIcPoHeader().toString());

            String order = 	poHeader.getPoNumber();
            String release = poHeader.getReleaseNumber().toString() ;
            if (Utility.isEmpty(release) ) {
                release = "0" ;
            }
            BigDecimal rel = new BigDecimal(release) ;
            String owner = (String) incomingRequest.get("userId") ;

            StringBuffer subject = new StringBuffer("");
            subject.append("Order ");
            subject.append(order) ;

            if (rel.compareTo(new BigDecimal(0)) > 0) {
                subject.append(" Release ");
                subject.append(release) ;
            }
            subject.append(" submitted for purchase") ;

            List poLineList = (List)incomingRequest.get("poLineList");
            StringBuffer lineTxt = new StringBuffer("The following items were placed on the Order: \n");
            for(int i = 0; i < poLineList.size(); i++)
            {
                PoLine poLine = (PoLine)poLineList.get(i);
                lineTxt.append(String.valueOf(i) + ".\t" + poLine.getItemNumber() + "\t" + poLine.getDescription() + "\t" + poLine.getQuantity().toString() +".\n");
            }

            String oid = (String)incomingRequest.get("organizationId");
            incomingRequest.put("SendQueue_subject", "[Buyer Notification] " + subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "U");
            incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(oid, owner).getMailId());
            incomingRequest.put("SendQueue_sendtotype", "U");
            String buyerEmail = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode()).getMailId();
            incomingRequest.put("SendQueue_sendto", buyerEmail);
            incomingRequest.put("SendQueue_messagetext", lineTxt.toString());

            incomingRequest.put("SendQueue_action", "EN");

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}