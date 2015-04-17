/**
 * Created on August 22, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invformpart.tasks.InvoiceLineDeleteByHeader.java
 *
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import org.hibernate.*;
import org.hibernate.type.Type;

public class InvoiceLineDeleteByHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest =(Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String queryString = "from InvoiceLine as InvoiceLine where InvoiceLine.icIvcHeader = ?";
			String icHeader = (String)incomingRequest.get("InvoiceLine_icIvcHeader");

			this.setStatus(dbs.delete(queryString, new Object[] {icHeader}, new Type[] {Hibernate.STRING}));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
