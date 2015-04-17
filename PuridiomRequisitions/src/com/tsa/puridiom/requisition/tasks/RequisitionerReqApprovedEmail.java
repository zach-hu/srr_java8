/*
 * Created on Mar 17, 2005
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class RequisitionerReqApprovedEmail extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            List releaseMessages = (List)incomingRequest.get("releaseMessages");
            List nonReleasedMessages = (List)incomingRequest.get("nonReleasedMessages");
            StringBuffer message = new StringBuffer("");

            if(releaseMessages == null)
            {
                Log.debug(this, "No items were autoreleased");
            }
            else
            {

                //String owner = (String) incomingRequest.get("userId") ;

                for(int i = 0; i < releaseMessages.size(); i++)
                {
                    AutoReleasedItemsSummary items = (AutoReleasedItemsSummary)releaseMessages.get(i);
                    AutoReleasedEmailText text = new AutoReleasedEmailText(items, (String)incomingRequest.get("organizationId"));
                    message.append(text.buildEmail());
                }
            }
            if(message.length() > 0)
            {
                message.append("\r\n");
            }
            if(nonReleasedMessages != null)
            {
                for(int i = 0; i < nonReleasedMessages.size(); i++)
                {
                    NonReleasedItemsSummary items = (NonReleasedItemsSummary)nonReleasedMessages.get(i);
                    message.append(items.getEmailText((String)incomingRequest.get("organizationId")));
                    message.append("\r\n");
                }
            }

            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            StringBuffer subject = new StringBuffer("");
            subject.append("Requisition " + rqh.getRequisitionNumber() + " Procurement Action");
            StringBuffer buyersAssigned = (StringBuffer)incomingRequest.get("buyersAssigned");
            if(buyersAssigned == null) {    buyersAssigned = new StringBuffer();    }

            if(!HiltonUtility.isEmpty(message.toString()))
            {
            	message.append("\r\n");
            	this.buildEmail(incomingRequest, rqh.getIcReqHeader().toString(), rqh.getRequisitionerCode(), subject, message, buyersAssigned);
            }
            this.setStatus(Status.SUCCEEDED);

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred generating Email for the requisitioner", e);
        }

        return ret;
    }

    /**
     * @param incomingRequest
     */
    private void buildEmail(Map incomingRequest, String icReqHeader, String requisitioner, StringBuffer subject, StringBuffer msg, StringBuffer buyersAssigned)
    {
        Log.debug(this, "buildingEmail starts");
        try
        {
        	PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", icReqHeader);
            //[Requisitioner Notification]
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            //String fromEmail = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "");
            String sendFrom = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            if(Utility.isEmpty(sendFrom))
            {
            	sendFrom = propertiesManager.getProperty("MAILEVENTS", "AdminEmailAddr", "");
            }
            incomingRequest.put("SendQueue_sendfrom", sendFrom );

            incomingRequest.put("SendQueue_sendtotype", "E");
            //StringBuffer sbtemp = this.buildEmailBody(msg);
            Log.debug(this, "body is: " + msg.toString());
            HiltonUtility.setSendQueueMessageLong(msg, incomingRequest);

            //incomingRequest.put("SendQueue_messagetext", msg.toString());
            StringBuffer sbTo = new StringBuffer();
            sbTo.append(buyersAssigned + ";");
            
            if (!propertiesManager.getProperty("ASSIGNMENT", "ASSIGNBYLINEFORHEADER", "N").equalsIgnoreCase("Y"))
			{
				sbTo.append(UserManager.getInstance().getUser((String) incomingRequest.get("organizationId"), requisitioner).getMailId());
			}
            
            incomingRequest.put("SendQueue_sendto", sbTo.toString());
            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("isRequisitionForwardApprovalProcess", "Y");

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
        }
        catch (Exception e)
        {
            Log.error(this, "Error occured sending email:" + subject.toString());
        }
    }

    public StringBuffer buildEmailBody(StringBuffer msg)
    {
        Log.debug(this, "Building email body: " + msg.toString());
        StringBuffer punchLine = new StringBuffer("\r\nPowered by Puridiom - \"Procurement Simplified.  Results Guaranteed.\"");
        msg.append(punchLine);

        return msg;
    }
}
