package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReceiptHeaderRetrieveForAutoCreateInvoice extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from ReceiptHeader as ReceiptHeader where ReceiptHeader.receiptStatus in (?, ?) and ReceiptHeader.icPoHeader in " +
					"(Select PoHeader.icPoHeader from PoHeader as PoHeader where (PoHeader.subType = '00' or PoHeader.subType = '01' or PoHeader.subType = '04') and PoHeader.lastRevision = 'C' and PoHeader.poNumber not like '%N/A%')";
			List resultList = dbs.query(queryString, new Object[] { DocumentStatus.RCV_PARTIALLYRECEIVED, DocumentStatus.RCV_RECEIVED } , new Type[] { Hibernate.STRING, Hibernate.STRING }) ;

			result = resultList;

			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}