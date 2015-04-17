package com.tsa.puridiom.invoiceheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvoiceHeaderRetrieveByOrder extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("invoiceHeader_icPoHeader");

			if (Utility.isEmpty(icPoHeaderString)) {
			    icPoHeaderString = (String) incomingRequest.get("ReceiptHeader_icPoHeader");
			}
			if (Utility.isEmpty(icPoHeaderString)) {
			    icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
			}
			if (Utility.isEmpty(icPoHeaderString)) {
			    icPoHeaderString = (String) incomingRequest.get("PoLine_icPoHeader");
			}
			if (Utility.isEmpty(icPoHeaderString)) {
			    icPoHeaderString = (String) incomingRequest.get("RequisitionHeader_icPoHeader");
			}
			if (Utility.isEmpty(icPoHeaderString)) {
			    icPoHeaderString = (String) incomingRequest.get("RequisitionLine_icPoHeader");
			}

			if (!Utility.isEmpty(icPoHeaderString)) {
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );

				String queryString = "from InvoiceHeader as InvoiceHeader where InvoiceHeader.icPoHeader = ? and InvoiceHeader.invoiceNumber<>'N/A' order by InvoiceHeader.invoiceDate";
				List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;

				result = resultList;
			} else {
			    Log.error(this, "IcPoHeader was empty.  InvoiceHeaderRetrieveByOrder could not be executed.");
			}

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