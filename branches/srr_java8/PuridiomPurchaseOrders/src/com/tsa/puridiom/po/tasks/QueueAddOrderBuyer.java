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

public class QueueAddOrderBuyer extends Task
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

			StringBuffer emails = new StringBuffer();
			String buyerEmail = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode()).getMailId();
            emails.append(buyerEmail);

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

}
