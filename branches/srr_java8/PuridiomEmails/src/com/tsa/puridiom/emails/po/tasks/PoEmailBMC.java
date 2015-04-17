package com.tsa.puridiom.emails.po.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.Dictionary;
import com.tsagate.properties.DictionaryManager;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class PoEmailBMC extends Task
{
	private Dictionary emailMessage;

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Log.debug(this, "starting");

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			List poLineList = (List)incomingRequest.get("poLineList");

			if (poHeader != null && poHeader.getStatus().compareTo(DocumentStatus.PO_AWARDED) == 0 && !HiltonUtility.isEmpty(poHeader.getUdf3Code()))
			{
				String organizationId = (String)incomingRequest.get("organizationId");
				this.emailMessage = DictionaryManager.getInstance("emailmsgbmc", organizationId);

				this.buildEmail(incomingRequest, poHeader.getIcPoHeader().toString(), this.getSubject(poHeader), this.getMsg(poHeader, organizationId, poLineList));
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
        String header = emailMessage.getProperty("po_bmc_subject");

        Object args[] = new Object[1];
        args[0] = poHeader.getUdf3Code();

        String result = MessageFormat.format(header, args);
        Log.debug(this, "subject text: " + result);
        return result;
    }

    private String getMsgHeader(PoHeader poHeader, String organizationId)
    {
        String header = emailMessage.getProperty("po_bmc_header");

        Object args[] = new Object[7];
        args[0] = poHeader.getDisplayPoNumber();
        args[1] = Dates.getDate(Dates.today("", poHeader.getTimeZone())) + " " + Dates.getNow(null, poHeader.getTimeZone());

        args[2] = this.getUserName(poHeader.getBuyerCode(), organizationId);
        args[3] = poHeader.getRequiredDate();
        args[4] = HiltonUtility.getCurrency(poHeader.getTotal(), poHeader.getCurrencyCode(), organizationId);
        args[5] = poHeader.getVendorName();
        args[6] = poHeader.getInternalComments();

        String result = MessageFormat.format(header, args);

        String addText = "Order ";
        if (!HiltonUtility.isEmpty(poHeader.getRequisitionNumber())) {
        	addText = "Requisition " + poHeader.getRequisitionNumber() + " ";
        }

        result = addText + result;

        Log.debug(this, "header text: " + result);
        return result;
    }

    public String getMsg(PoHeader poHeader, String organizationId, List poLineList)
    {
        return this.getMsgHeader(poHeader, organizationId) + this.getMsgLines(poLineList);
    }

    public String getMsgLines(List poLineList)
    {
        String header = emailMessage.getProperty("po_bmc_line_header");
        String itemLine = emailMessage.getProperty("po_bmc_line_item");
        StringBuffer result = new StringBuffer();

        result.append(MessageFormat.format(header, new Object[]{"Items"}));

        for(int i = 0; i < poLineList.size(); i++)
        {
            PoLine poLine = (PoLine)poLineList.get(i);
            Object args[] = new Object[4];

            args[0] = String.valueOf(i + 1);
            args[1] = poLine.getItemNumber();
            args[2] = poLine.getDescription();
            args[3] = poLine.getQuantity();
            result.append(MessageFormat.format(itemLine, args));
        }

        result.append("\n");
        result.append("-------------------------------------------------------------------\n");
        result.append("Powered by Puridiom – \"Procurement Simplified. Results Guaranteed.\"");

        Log.debug(this, "lines text: " + result.toString());
        return result.toString();
    }

    private void buildEmail(Map incomingRequest, String icPoHeader, String subject, String msg)
    {
        Log.debug(this, "buildingEmail starts");
        try
        {
        	Boolean isAutoAwardRequisition = (Boolean) incomingRequest.get("autoAwarded");
        	if(isAutoAwardRequisition == null) isAutoAwardRequisition = Boolean.FALSE;

            incomingRequest.put("SendQueue_doctype", "PO");
            incomingRequest.put("SendQueue_docic", icPoHeader);
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
