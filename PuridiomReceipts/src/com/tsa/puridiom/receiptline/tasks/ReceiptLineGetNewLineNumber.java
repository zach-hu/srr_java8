package com.tsa.puridiom.receiptline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReceiptLineGetNewLineNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeaderString = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
			if (Utility.isEmpty(icRecHeaderString)) {
			    icRecHeaderString = (String) incomingRequest.get("ReceiptLine_icRecHeader");
			}
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			BigDecimal lineNumber = new BigDecimal("0");

			String queryString = "select max(r.receiptLine) from ReceiptLine as r where r.icRecHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRecHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				lineNumber = (BigDecimal) resultList.get(0);
				if (lineNumber == null) {
					lineNumber = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(lineNumber.add(new BigDecimal(1)));
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