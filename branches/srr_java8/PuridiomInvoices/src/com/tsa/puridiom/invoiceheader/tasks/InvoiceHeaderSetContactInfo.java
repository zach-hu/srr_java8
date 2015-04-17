/**
 * Created on August 26, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoiceheader.tasks.InvoiceHeaderSetContactInfo.java
 *
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvoiceHeaderSetContactInfo extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		Contact contact = (Contact) incomingRequest.get("contact");

		if (contact != null)
		{
			incomingRequest.put("InvoiceHeader_contactName", contact.getDisplayName());
			incomingRequest.put("InvoiceHeader_contactEmail", contact.getEmailAddr());
			incomingRequest.put("InvoiceHeader_contactPhone", contact.getPhoneNumber());
			incomingRequest.put("InvoiceHeader_contactFax", contact.getFaxNumber());
		}

		this.setStatus(Status.SUCCEEDED);
		return null;
	}

}
