package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

public class InvoiceLineRetrieveExport extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String queryString = "from InvoiceLine as InvoiceLine where InvoiceLine.status = ? and " +
				"( InvoiceLine.assetExported is null or InvoiceLine.assetExported = 'N' )";
			List resultList = dbs.query(queryString, new Object[] {DocumentStatus.IVC_APPROVED}, new Type[] {Hibernate.STRING});

			result = resultList;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}