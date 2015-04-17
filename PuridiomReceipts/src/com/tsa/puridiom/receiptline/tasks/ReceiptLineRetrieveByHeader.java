package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;
import java.util.Map;

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

			if(HiltonUtility.isEmpty(icRecHeader)){
				icRecHeader = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
			}

			String queryString = "from ReceiptLine as receiptline where receiptline.icRecHeader = " + icRecHeader + " order by receiptline.receiptLine ASC ";

			ret = dbs.query(queryString.toString()) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			e.printStackTrace();
			this.setStatus(Status.FAILED);
			throw new TsaException("ReceiptLine was not Retrieved", e);
		}
		return ret ;
	}
}