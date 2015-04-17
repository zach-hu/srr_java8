/**
 *
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Johnny
 */
public class QueueAddMXPInvoice extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Object result = null;
		Map incomingRequest = (Map) object;

		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			String newStatus = (String) incomingRequest.get("newStatus");
			String oid = (String) incomingRequest.get("organizationId");
			String owner = (String) incomingRequest.get("userId");
			String subject = "";
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process;

			if (newStatus.compareTo(DocumentStatus.IVC_APPROVED) == 0)
            {
				incomingRequest.put("SendQueue_action", "MX");

				incomingRequest.put("SendQueue_sendto", "");
				incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(oid, owner).getMailId());
				incomingRequest.put("SendQueue_sendfromtype", "U");
				incomingRequest.put("SendQueue_sendtotype", "U");

				incomingRequest.put("SendQueue_doctype", "IVC");
				incomingRequest.put("SendQueue_docic", (String) incomingRequest.get("InvoiceHeader_icIvcHeader"));

				subject = PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom") + " " + invoiceHeader.getInvoiceNumber() + "  Status: " + invoiceHeader.getStatus();

				incomingRequest.put("SendQueue_subject", subject);

				process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);

				this.setStatus(process.getStatus());
            }

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "Add queue MXP Order failed: " + e.getMessage());

			throw e;
		}

		return result;
	}

}
