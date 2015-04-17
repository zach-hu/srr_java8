/*
 * Created on June 01, 2006
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
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
 * @author kathleen
 */
public class RequisitionerReqPricedEmail extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map) object;

        try
        {
        	String oid = (String) incomingRequest.get("organizationId");
            RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
            List reqLineList = (List) incomingRequest.get("requisitionLineList");

            StringBuffer subject = new StringBuffer("");
            subject.append("Requisition " + rqh.getRequisitionNumber() + " has been Priced");

            StringBuffer message = new StringBuffer("");
            message.append("This email has been sent to notify you that Requisition #" + rqh.getRequisitionNumber() + " has been Priced!\r\n");
            message.append("\r\n");
            message.append(this.buildItemsText(reqLineList, oid));
            message.append("\r\n");
            message.append(this.buildSummary(rqh, oid));

            System.out.println(message);

            this.buildEmail(incomingRequest, rqh.getIcReqHeader().toString(), rqh.getRequisitionerCode(), subject, message);

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
    private void buildEmail(Map incomingRequest, String icReqHeader, String requisitioner, StringBuffer subject, StringBuffer msg)
    {
        Log.debug(this, "buildingEmail starts");
        try
        {
            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", icReqHeader);
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "E");

            String sendFrom = UserManager.getInstance().getUser((String) incomingRequest.get("organizationId"), (String) incomingRequest.get("userId")).getMailId();
            if (Utility.isEmpty(sendFrom))
            {
            	sendFrom = PropertiesManager.getInstance((String) incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "");
            }
            incomingRequest.put("SendQueue_sendfrom", sendFrom);
            incomingRequest.put("SendQueue_sendtotype", "E");

            Log.debug(this, "body is: " + msg.toString());
            HiltonUtility.setSendQueueMessageLong(msg, incomingRequest);

            incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), requisitioner).getMailId());
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

    public String buildItemsText(List itemsList, String oid)
    {
        StringBuffer linesText = new StringBuffer();

        for (int i = 0; i < itemsList.size(); i++)
        {
            RequisitionLine item = (RequisitionLine) itemsList.get(i);
            String description = item.getDescription();
            if ( !HiltonUtility.isEmpty(description) && description.length() > 40 )
            {
            	description = description.substring(0, 40);
            	description = description + "...";
    		}

            Object args[] = new Object[7];
            args[0] = String.valueOf(i + 1);
            args[1] = item.getItemNumber();
            args[2] = description;
            args[3] = HiltonUtility.getFormattedQuantity(item.getQuantity(), oid);
            args[4] = HiltonUtility.getFormattedDollar(item.getUnitPrice(), oid);
            args[5] = item.getUmCode();
            args[6] = HiltonUtility.getFormattedDollar(item.getLineTotal(), oid);

            //linesText.append(args[0] + ".  " + args[1] + " - " + args[2] + "  Qty: " + args[3] + "  Total: " + args[4] + "\r\n");
            linesText.append(args[0] + ".  " + args[1] + " - " + args[2] + "  " + args[3] + " @ $" + args[4] + " (" + args[5] + ") = $" + args[6] + "\r\n");
        }

        Log.debug(this, "lines text: " + linesText.toString());
        return linesText.toString();
    }

    public String buildSummary(RequisitionHeader rqh, String oid)
    {
    	StringBuffer summary = new StringBuffer();
    	summary.append("Request Total: $" + HiltonUtility.getFormattedDollar(rqh.getTotal(), oid).toString());
    	summary.append("\r\n");
    	summary.append("Supplier Name: " + rqh.getVendorName());

    	Log.debug(this, "requisition summary: " + summary.toString());
        return summary.toString();
    }

    public StringBuffer buildEmailBody(StringBuffer msg)
    {
        Log.debug(this, "Building email body: " + msg.toString());
        StringBuffer punchLine = new StringBuffer("\r\nPowered by Puridiom - \"Procurement Simplified.  Results Guaranteed.\"");
        msg.append(punchLine);

        return msg;
    }
}
