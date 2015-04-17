/*
 * Created on Mar 17, 2005
 */
package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.vendor.VendorManager;
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
public class RequisitionAutoAwardEmail extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;

            String organizationId = (String) incomingRequest.get("organizationId");
            StringBuffer message = new StringBuffer("");
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");

            message.append("Requisition # " + requisitionHeader.getRequisitionNumber() + " was placed on Order # " + poHeader.getPoNumber() + " and " +
            		"awarded to Supplier " + VendorManager.getInstance().getVendorName(organizationId, poHeader.getVendorId()) + "\r\n");


	        StringBuffer subject = new StringBuffer("");
	        subject.append("Requisition " + requisitionHeader.getRequisitionNumber() + " Autoaward Procurement Action");

	        this.buildEmail(incomingRequest, requisitionHeader.getIcReqHeader().toString(), requisitionHeader.getRequisitionerCode(), subject, message);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error ocurred generating Email for the requisitioner", e);
        }

        return ret;
    }

    /**
     * @param incomingRequest
     */
    private void buildEmail(Map incomingRequest, String icReqHeader, String requisitioner, StringBuffer subject, StringBuffer msg)
    {
        Log.debug(this, "buildingEmail starts");
        try
        {
            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", icReqHeader);
            //[Requisitioner Notification]
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            //String fromEmail = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "");
            String sendFrom = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            if(Utility.isEmpty(sendFrom))
            {
            	sendFrom = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "");
            }
            incomingRequest.put("SendQueue_sendfrom", sendFrom );

            incomingRequest.put("SendQueue_sendtotype", "E");
            //StringBuffer sbtemp = this.buildEmailBody(msg);
            Log.debug(this, "body is: " + msg.toString());
            HiltonUtility.setSendQueueMessageLong(msg, incomingRequest);

            //incomingRequest.put("SendQueue_messagetext", msg.toString());
            StringBuffer sbTo = new StringBuffer();
            sbTo.append(UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), requisitioner).getMailId());
            incomingRequest.put("SendQueue_sendto", sbTo.toString());
            incomingRequest.put("SendQueue_action", "EN");

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
