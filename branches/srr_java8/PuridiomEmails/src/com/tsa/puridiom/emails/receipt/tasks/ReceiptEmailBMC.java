package com.tsa.puridiom.emails.receipt.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.Dictionary;
import com.tsagate.properties.DictionaryManager;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class ReceiptEmailBMC extends Task
{
	private Dictionary emailMessage;

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Log.debug(this, "starting");

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			List receiptLineList = (List)incomingRequest.get("receiptLineList");

			if (poHeader != null && receiptHeader != null &&
				(poHeader.getStatus().compareTo(DocumentStatus.RCV_PARTIALLYRECEIVED) == 0 || poHeader.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0) &&
				!HiltonUtility.isEmpty(poHeader.getUdf3Code()))
			{
				String organizationId = (String)incomingRequest.get("organizationId");
				this.emailMessage = DictionaryManager.getInstance("emailmsgbmc", organizationId);

				this.buildEmail(incomingRequest, receiptHeader.getIcRecHeader().toString(), this.getSubject(poHeader), this.getMsg(poHeader, receiptHeader, organizationId, receiptLineList));
			}
			this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Requisitoner cound not be notified!", e);
        }
        Log.debug(this, "ends");
        return null;
    }

    public String getUserName(String userId, String organizationId)
    {
        try
        {
            userId = UserManager.getInstance().getUser(organizationId, userId).getDisplayName();
        }
        catch(Exception e)
        {
            Log.debug(this, "No user: " + userId + " for oid: " + organizationId + " was found!");
        }
        return userId;
    }

    private String getSubject(PoHeader poHeader)
    {
        String header = emailMessage.getProperty("rec_bmc_subject");

        Object args[] = new Object[1];
        args[0] = poHeader.getUdf3Code();

        String result = MessageFormat.format(header, args);
        Log.debug(this, "subject text: " + result);
        return result;
    }

    private String getMsgHeader(PoHeader poHeader, ReceiptHeader receiptHeader, String organizationId)
    {
        String header = emailMessage.getProperty("rec_bmc_header");

        Object args[] = new Object[9];
        args[0] = receiptHeader.getReceiptNumber();
        args[1] = poHeader.getDisplayPoNumber();
        args[2] = this.getUserName(receiptHeader.getReceivedBy(), organizationId);
        args[3] = receiptHeader.getReceiptDate();
        args[4] = receiptHeader.getPackingSlip();
        args[5] = receiptHeader.getPkgsReceived();
        args[6] = receiptHeader.getVendorName();
        args[7] = receiptHeader.getReceiptNotes();
        args[8] = DocumentStatus.toString(poHeader.getStatus(), organizationId);

        String result = MessageFormat.format(header, args);

        Log.debug(this, "header text: " + result);
        return result;
    }

    public String getMsg(PoHeader poHeader, ReceiptHeader receiptHeader, String organizationId, List receiptLineList)
    {
        return this.getMsgHeader(poHeader, receiptHeader, organizationId) + this.getMsgLines(receiptLineList);
    }

    public String getMsgLines(List receiptLineList)
    {
        String header = emailMessage.getProperty("rec_bmc_line_header");
        String itemLine = emailMessage.getProperty("rec_bmc_line_item");
        StringBuffer result = new StringBuffer();

        result.append(MessageFormat.format(header, new Object[]{"Items"}));

        for(int i = 0; i < receiptLineList.size(); i++)
        {
        	ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
            Object args[] = new Object[4];

            String itemNumber = "";
            if (receiptLine.getPoLine() != null)
            {
            	itemNumber = receiptLine.getPoLine().getItemNumber();
            }

            args[0] = String.valueOf(i + 1);
            args[1] = itemNumber;
            args[2] = receiptLine.getReceiptNotes();
            args[3] = receiptLine.getQtyAccepted();
            result.append(MessageFormat.format(itemLine, args));
        }

        result.append("\n");
        result.append("-------------------------------------------------------------------\n");
        result.append("Powered by Puridiom – \"Procurement Simplified. Results Guaranteed.\"");

        Log.debug(this, "lines text: " + result.toString());
        return result.toString();
    }

    private void buildEmail(Map incomingRequest, String icRecHeader, String subject, String msg)
    {
        Log.debug(this, "buildingEmail starts");
        try
        {
        	Boolean isAutoAwardRequisition = (Boolean) incomingRequest.get("autoAwarded");
        	if(isAutoAwardRequisition == null) isAutoAwardRequisition = Boolean.FALSE;

            incomingRequest.put("SendQueue_doctype", "REC");
            incomingRequest.put("SendQueue_docic", icRecHeader);
            //[Requisitioner Notification]
            incomingRequest.put("SendQueue_subject", subject );
            incomingRequest.put("SendQueue_sendfromtype", "E");

            String sendFrom = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            if(Utility.isEmpty(sendFrom) && isAutoAwardRequisition.booleanValue())
            {
            	sendFrom = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "support@puridiom.com");
            }
            incomingRequest.put("SendQueue_sendfrom", sendFrom );
            incomingRequest.put("SendQueue_sendtotype", "E");

            Log.debug(this, "body is: " + msg);
            incomingRequest.put("SendQueue_messagetext", msg.toString());

            String sendTo = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "SENDTOBMC", "magichelpdesk@aaamidatlantic.com;ithelpdesk@aaamidatlantic.com");

            incomingRequest.put("SendQueue_sendto", sendTo);
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
}
