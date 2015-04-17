package com.tsa.puridiom.invoiceline.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvoiceLineRetrieveExportGLTran extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			//case2: If the status of the invoice is 6010 and first flag is N
			String queryStringGL1 = "from InvoiceLine as InvoiceLine where InvoiceLine.status = ? and " +
				"( InvoiceLine.exportFlag1 is null or InvoiceLine.exportFlag1 = 'N' )";
			List resultListGL1 = dbs.query(queryStringGL1, new Object[] {DocumentStatus.IVC_APPROVED}, new Type[] {Hibernate.STRING});
			incomingRequest.put("invoiceLineListGL1", resultListGL1);

			//case3: If the status of the invoice is 6030 AND the first flag is Y , BUT the second flag is N,
			String queryStringGL2A = "from InvoiceLine as InvoiceLine where InvoiceLine.status = ? and " +
			"( InvoiceLine.exportFlag1 is not null or InvoiceLine.exportFlag1 = 'Y' ) and " +
			"( InvoiceLine.exportFlag2 is null or InvoiceLine.exportFlag2 = 'N' ) ";
			List resultListGL2A = dbs.query(queryStringGL2A, new Object[] {DocumentStatus.IVC_PAID}, new Type[] {Hibernate.STRING});
			incomingRequest.put("invoiceLineListGL2A", resultListGL2A);

			//case 4:  If the status of the invoice is 6030 AND the first flag is N AND the second flag is N
			String queryStringGL2B = "from InvoiceLine as InvoiceLine where InvoiceLine.status = ? and " +
			"( InvoiceLine.exportFlag1 is null or InvoiceLine.exportFlag1 = 'N' ) and " +
			"( InvoiceLine.exportFlag2 is null or InvoiceLine.exportFlag2 = 'N' ) ";
			List resultListGL2B = dbs.query(queryStringGL2B, new Object[] {DocumentStatus.IVC_PAID}, new Type[] {Hibernate.STRING});
			incomingRequest.put("invoiceLineListGL2B", resultListGL2B);

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			Log.error(this, e.getMessage());
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}