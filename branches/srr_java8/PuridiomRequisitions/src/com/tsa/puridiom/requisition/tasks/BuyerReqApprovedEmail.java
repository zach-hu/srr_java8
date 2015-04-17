/*
 * Created on Mar 17, 2005
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class BuyerReqApprovedEmail extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
            String oid = (String)incomingRequest.get("organizationId");

            //find email for buyers of items autoreleased.
            StringBuffer buyersAssigned = (StringBuffer)incomingRequest.get("buyersAssigned");
            StringBuffer buyerEmails = new StringBuffer();
            if(buyersAssigned == null) {        buyersAssigned = new StringBuffer();     }
            if(buyersAssigned.indexOf(";") > 0)
            {
                String parts[] = buyersAssigned.toString().split(";");
                for(int i = 0; i < parts.length; i++)
                {
                    if(buyerEmails.length() > 0)
                    {
                        buyerEmails.append(";");
                    }
                    buyerEmails.append(UserManager.getInstance().getUser(oid, parts[i]).getMailId());
                }
            }
            buyersAssigned = buyerEmails;

            //now for the rest.
            List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
            for(int i = 0; i < requisitionLineList.size(); i++)
            {
                RequisitionLine rql = (RequisitionLine)requisitionLineList.get(i);
                Log.debug(this, "line " + rql.getLineNumber() + ", item: " + rql.getItemNumber());
                String assignTo = rql.getAssignedBuyer();
                if(assignTo.equalsIgnoreCase("AUTORELEASE"))
                {
                    //We  know already the vendor's email from the buyersAssigned String
                    //buyersAssigned.append(UserManager.getInstance().getUser(oid, assignTo).getMailId());
                }
                else if(assignTo.equalsIgnoreCase("PURCHASING"))
                {
                    //Item has not been assigned! somebody has to know what to with the item.
                    String queue = PropertiesManager.getInstance(oid).getProperty("MAILQUEUE", "PURCHASING", "");
                    String queues[] = queue.split(";");
                    for(int qIndex = 0; qIndex < queues.length; qIndex++)
                    {
                        if(buyersAssigned.length() > 0)
                        {
                            buyersAssigned.append(";");
                        }
                        buyersAssigned.append(queues[qIndex]);
                    }
                    //buyersAssigned.append(UserManager.getInstance().getUser(oid, "SYSADM").getMailId());
                }
                else
                {
                    if(buyersAssigned.length() > 0)
                    {
                        buyersAssigned.append(";");
                    }
                    buyersAssigned.append(UserManager.getInstance().getUser(oid, assignTo).getMailId());
                }
            }
            incomingRequest.put("buyersAssigned", buyersAssigned);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error ocurred generating Email for the requisitioner", e);
        }

        return super.executeTask(object);
    }
}
