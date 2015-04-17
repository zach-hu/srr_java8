package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReceiptLineRetreiveByHeaderForAutoInvoice extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String icRecHeader = "";
			if(incomingRequest.get("ReceiptLine_icRecHeader") instanceof String[])
			    icRecHeader = ((String[]) incomingRequest.get("ReceiptLine_icRecHeader"))[0];
			else
				icRecHeader = (String) incomingRequest.get("ReceiptLine_icRecHeader");

			if(HiltonUtility.isEmpty(icRecHeader)){
				icRecHeader = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
			}
			BigDecimal bdRecHeader = new BigDecimal(icRecHeader);

			String queryString = "from ReceiptLine as receiptline where receiptline.icRecHeader = ? AND (receiptline.status = '4100' or receiptline.status = '4150')";
			result = dbs.query(queryString, bdRecHeader, Hibernate.BIG_DECIMAL);
			this.setStatus(dbs.getStatus());
		}
		catch(Exception e)
		{
			Log.debug(this, e.getMessage());
			this.setStatus(Status.FAILED);
		}
		return result;
	}
}
