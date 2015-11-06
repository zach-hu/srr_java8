package com.tsa.puridiom.receiptline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class ReceiptLineRetrieveByHeaderPoLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeaderString = (String) incomingRequest.get("ReceiptLine_icRecHeader");
			String icPoLineString = (String) incomingRequest.get("ReceiptLine_icPoLine");
			
			BigDecimal icRecHeader = null;
			BigDecimal icPoLine = null;
			try {
				icRecHeader = new BigDecimal(icRecHeaderString); 
				icPoLine = new BigDecimal(icPoLineString);
				
			} catch (Exception e) {
				Log.error(this, e);
			}
			
			if (icRecHeader != null && icPoLine != null) {
				String queryString = "from ReceiptLine as receiptline where receiptline.icRecHeader = ? and receiptline.icPoLine = ?";
				List resultList = dbs.query(queryString, new Object[] {icRecHeader, icPoLine} , new Type[] {Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
				if (resultList.size() == 0) resultList = null ;
				if (resultList != null && resultList.size() > 0)
				{
					ret = resultList.get(0);
				} else {
					ret = resultList;
				}
				this.setStatus(dbs.getStatus());
				
			} else {
				ret = null;
				this.setStatus(Status.SUCCEEDED);
			}
			
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw new TsaException("ReceiptLine was not Retrieved", e);
		}
		return ret ;
	}
}