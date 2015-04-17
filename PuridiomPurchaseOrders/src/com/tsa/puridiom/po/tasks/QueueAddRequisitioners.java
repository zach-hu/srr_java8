package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.SendPoFlag;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class QueueAddRequisitioners extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String oid = (String) incomingRequest.get("organizationId");
            String actionFlag = poHeader.getEdiOrder();
            if(Utility.isEmpty(actionFlag)) {       actionFlag = SendPoFlag.PRINT_PO;    }

            if(actionFlag.equalsIgnoreCase(SendPoFlag.XML_PO))
            {
            	this.setStatus(Status.SUCCEEDED);
            	return result;
            }

			StringBuffer reqEmails = (StringBuffer) incomingRequest.get("requisitionersToEmail");
			StringBuffer emails = new StringBuffer();
			String buyerEmail = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode()).getMailId();
            emails.append(buyerEmail);

			if (!Utility.isObjectEmpty(reqEmails))
			{
				if (emails.length() > 0) {	emails.append(";");		}
				emails.append(this.getRequisitionerEmails(reqEmails, oid));
			}

			String temp = UserManager.getInstance().getUser(oid, poHeader.getRequisitionerCode()).getMailId();
			if (temp != null && temp.length() > 0)
			{
				if (emails.length() > 0) {		emails.append(";");		}
				emails.append(temp);
			}
			incomingRequest.put("SendQueue_action", "XP");
			incomingRequest.put("SendQueue_sendto", emails.toString());
			incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(oid, poHeader.getBuyerCode()).getMailId());

			incomingRequest.put("SendQueue_sendfromtype", "U");
			incomingRequest.put("SendQueue_sendtotype", "U");

			incomingRequest.put("SendQueue_doctype", "PO");
			incomingRequest.put("SendQueue_docic", (String) incomingRequest.get("PoHeader_icPoHeader"));

			StringBuffer subject = new StringBuffer("FYI: ");
			subject.append(PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom"));
			subject.append(" ");
			subject.append(poHeader.getDisplayPoNumber());
			incomingRequest.put("SendQueue_subject", subject.toString());

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
			process.executeProcess(incomingRequest);
			this.status = process.getStatus();
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}

	private StringBuffer getRequisitionerEmails(StringBuffer requisitioners, String organizationId)
	{
		Log.debug(this, "getRequisitionerEmails starts");

		String users[] = requisitioners.toString().split(";");
		StringBuffer requisitionerEmails = new StringBuffer();
		for (int i = 0; i < users.length; i++)
		{
			if (!Utility.isEmpty(users[i]))
			{
				Log.debug(this, "user: " + users[i]);
				if (requisitionerEmails.length() > 0 && !(i == users.length)) {	requisitionerEmails.append(";");		}
				requisitionerEmails.append(this.getUserEmail(users[i], organizationId));
			}
		}
		Log.debug(this, "returns " + requisitionerEmails.toString());
		return requisitionerEmails;
	}

	public String getUserEmail(String userId, String organizationId)
	{
		try
		{
			userId = UserManager.getInstance().getUser(organizationId, userId).getMailId();
		}
		catch (Exception e)
		{
			Log.debug(this, "No user: " + userId + " for oid: " + organizationId + " was found!");
		}
		return userId;
	}

}
