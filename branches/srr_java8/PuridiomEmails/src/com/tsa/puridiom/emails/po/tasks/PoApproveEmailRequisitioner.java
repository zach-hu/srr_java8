/*
 * Created on Jun 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.po.tasks;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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

/**
 * @author renzo
 */
public class PoApproveEmailRequisitioner extends Task
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
            Log.debug(this, "Notifying " + poHeader.getRequisitionerCode());
            String organizationId = (String)incomingRequest.get("organizationId");
            this.emailMessage = DictionaryManager.getInstance("emailmsg", organizationId);
            StringBuffer requisitioners = (StringBuffer)incomingRequest.get("requisitionersToEmail");
            this.buildEmail(incomingRequest, poHeader.getIcPoHeader().toString(), this.getRequisitionerEmails(requisitioners, organizationId), this.getSubject(poHeader), this.getMsg(poHeader, organizationId, poLineList));
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

    private StringBuffer getRequisitionerEmails(StringBuffer requisitioners, String organizationId)
    {
        Log.debug(this, "getRequisitionerEmails starts");
        String users[] = requisitioners.toString().split(";");
        StringBuffer requisitionerEmails = new StringBuffer();
        for (int i = 0; i < users.length; i++)
        {
            if(!Utility.isEmpty(users[i]))
            {
                Log.debug(this, "user: " + users[i]);
                if(requisitionerEmails.length() > 0 && !(i == users.length))
                {
                	requisitionerEmails.append(";");
                }
                requisitionerEmails.append(this.getUserEmail(users[i], organizationId));
            }
        }
        Log.debug(this, "returns " + requisitionerEmails.toString());
        return requisitionerEmails;
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

    public String getUserEmail(String userId, String organizationId)
    {
        try
        {
            userId = UserManager.getInstance().getUser(organizationId, userId).getMailId();
        }
        catch(Exception e)
        {
            Log.debug(this, "No user: " + userId + " for oid: " + organizationId + " was found!");
        }
        return userId;
    }

    private String getSubject(PoHeader poHeader)
    {
        String header = emailMessage.getProperty("poforward_Subject");

        Object args[] = new Object[2];
        args[0] = poHeader.getRequisitionNumber();
        args[1] = poHeader.getDisplayPoNumber();

        String result = MessageFormat.format(header, args);
        Log.debug(this, "subject text: " + result);
        return result;
    }

    private String getMsgHeader(PoHeader poHeader, String organizationId)
    {
        String header = emailMessage.getProperty("poforward_msg");

        Object args[] = new Object[8];
        args[0] = poHeader.getRequisitionNumber();
        args[1] = poHeader.getDisplayPoNumber();
        args[2] = Dates.getDate(Dates.today("", poHeader.getTimeZone())) + " " + Dates.getNow(null, poHeader.getTimeZone());

        args[3] = this.getUserName(poHeader.getBuyerCode(), organizationId);
        args[4] = poHeader.getRequiredDate();
        args[5] = poHeader.getTotal();
        args[6] = poHeader.getVendorName();
        String method = poHeader.getEdiOrder();
        if(method.equalsIgnoreCase("X"))
        {
            method = "XML";
        }
        else if(method.equalsIgnoreCase("M"))
        {
            method = "Email";
        }
        else if(method.equalsIgnoreCase("F"))
        {
            method = "Fax";
        }
        else if(method.equalsIgnoreCase("E"))
        {
            method = "EDI";
        }
        else
        {
        	method = "Print";
        }
        args[7] = method;

        String result = MessageFormat.format(header, args);
        Log.debug(this, "header text: " + result);
        return result;
    }

    public String getMsg(PoHeader poHeader, String organizationId, List poLineList)
    {
        return this.getMsgHeader(poHeader, organizationId) + this.getMsgLines(poLineList);
    }

    public String getMsgLines(List poLineList)
    {
        String header = emailMessage.getProperty("poforward_headeritemline");
        String itemLine = emailMessage.getProperty("poforward_itemline");
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

        Log.debug(this, "lines text: " + result.toString());
        return result.toString();
    }

    private void buildEmail(Map incomingRequest, String icPoHeader, StringBuffer to, String subject, String msg)
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
            //String sendTo = this.getUserEmail(requisitioner, (String)incomingRequest.get("organizationId"));
            incomingRequest.put("SendQueue_sendto", to.toString());
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