package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptLineRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String icRecHeader = "";
			if(incomingRequest.get("ReceiptLine_icRecHeader") instanceof String[])
			    icRecHeader = ((String[]) incomingRequest.get("ReceiptLine_icRecHeader"))[0];
			else
				icRecHeader = (String) incomingRequest.get("ReceiptLine_icRecHeader");

			if (HiltonUtility.isEmpty(icRecHeader)) {
				icRecHeader = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
			}
			
			BigDecimal icReceiptHeader = null;
			try {
				icReceiptHeader = new BigDecimal(icRecHeader);
			} catch (Exception e) {
				Log.error(this, e);
			}
			
			if (icReceiptHeader != null) {
				String queryString = "from ReceiptLine as receiptline where receiptline.icRecHeader = ? order by receiptline.receiptLine ASC ";
				ret = dbs.query(queryString.toString(), new Object[] {icReceiptHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
				this.setStatus(dbs.getStatus());
				
			} else {
				ret = new ArrayList();
				this.setStatus(Status.SUCCEEDED);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			this.setStatus(Status.FAILED);
			throw new TsaException("ReceiptLine was not Retrieved", e);
		}
		return ret ;
	}
}