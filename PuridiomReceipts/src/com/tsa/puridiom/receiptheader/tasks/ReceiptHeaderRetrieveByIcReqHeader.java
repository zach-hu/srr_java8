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

public class ReceiptHeaderRetrieveByIcReqHeader extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqHeaderString = (String) incomingRequest.get("ReceiptHeader_icReqHeader");

			if (!Utility.isEmpty(icReqHeaderString)) {
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );

				String queryString = "from ReceiptHeader as ReceiptHeader where ReceiptHeader.icReqHeader = ? AND ReceiptHeader.receiptNumber IS NOT NULL order by ReceiptHeader.receiptDate";
				List resultList = dbs.query(queryString, new Object[] {icReqHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
				result = resultList;
			} else {
			    Log.error(this, "IcReqHeader was empty.  ReceiptHeaderRetrieveByIcReqHeader could not be executed.");
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