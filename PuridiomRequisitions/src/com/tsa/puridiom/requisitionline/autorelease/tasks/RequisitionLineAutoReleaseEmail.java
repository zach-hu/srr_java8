package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.requisition.tasks.AutoReleasedItemsEmail;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionLineAutoReleaseEmail extends Task
{
	public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
        	Map linesMap = (Map)incomingRequest.get("lineListByNumber");
        	if(linesMap != null)
        	{
	        	Set numberSet = linesMap.keySet();
	        	for (Iterator iter = numberSet.iterator(); iter.hasNext();)
	        	{
					String requisitionNumber = (String) iter.next();
					List lines = (List)linesMap.get(requisitionNumber);
					//create email for req
					RequisitionHeader header = ((RequisitionLineAutoReleaseObject)lines.get(0)).getRequisitionHeader();
		            StringBuffer message = new StringBuffer();
					AutoReleasedItemsEmail items = new AutoReleasedItemsEmail(lines);
		            message.append(items.getEmailText((String)incomingRequest.get("organizationId")));
		            message.append("\r\n");

			        StringBuffer subject = new StringBuffer("");
			        subject.append("Requisition " + header.getRequisitionNumber() + " Procurement Action");

			        this.buildEmail(incomingRequest, header.getIcReqHeader().toString(), header.getRequisitionerCode(), subject, message);
			        lines = null;
				}
        	}
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition Line List could not be grouped by Requisition Number.", e);
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

            incomingRequest.remove("SendQueue_messagetext2");
            incomingRequest.remove("sendQueue");
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
