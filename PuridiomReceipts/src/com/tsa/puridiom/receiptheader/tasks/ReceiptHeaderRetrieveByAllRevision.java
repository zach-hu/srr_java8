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

public class ReceiptHeaderRetrieveByAllRevision extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		List resultList = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("ReceiptHeader_icPoHeader");

			if (Utility.isEmpty(icPoHeaderString)) {
			    icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
			}

			String refNumber = (String) incomingRequest.get("poNumber");
			String refRelease = (String) incomingRequest.get("poRelease");

			if( !Utility.isEmpty(refNumber) ){
				String queryString = "from ReceiptHeader as ReceiptHeader where ReceiptHeader.refNumber = ? AND ReceiptHeader.refRelease = ? order by ReceiptHeader.receiptDate";
				resultList = dbs.query(queryString, new Object[] { refNumber, refRelease } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
			}
			else {

					Log.error(this, "IcHeaderKey was empty.  ReceiptHeaderRetrieveByAllRevisions could not be executed.");
			}

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