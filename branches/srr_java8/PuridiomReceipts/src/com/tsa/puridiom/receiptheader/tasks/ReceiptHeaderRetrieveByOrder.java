package com.tsa.puridiom.receiptheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReceiptHeaderRetrieveByOrder extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("ReceiptHeader_icPoHeader");

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

				String queryString = "from ReceiptHeader as ReceiptHeader where ReceiptHeader.icPoHeader = ? AND ReceiptHeader.receiptNumber IS NOT NULL order by ReceiptHeader.receiptDate";
				List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;

				result = resultList;
			} else {
			    Log.error(this, "IcPoHeader was empty.  ReceiptHeaderRetrieveByOrder could not be executed.");
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