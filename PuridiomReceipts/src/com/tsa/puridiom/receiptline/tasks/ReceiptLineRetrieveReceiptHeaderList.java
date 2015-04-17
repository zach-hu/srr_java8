package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.List;
import java.util.Map;

public class ReceiptLineRetrieveReceiptHeaderList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List receiptHeaderList = (List) incomingRequest.get("receiptHeaderList");
			if (receiptHeaderList!=null && receiptHeaderList.size() > 0) {
				for(int rhl = 0 ; rhl < receiptHeaderList.size() ; rhl ++)
				{
					ReceiptHeader receiptHeader = (ReceiptHeader)receiptHeaderList.get(rhl);
					String queryString = "from ReceiptLine as receiptline where receiptline.icRecHeader = " + receiptHeader.getIcRecHeader() +
							" and receiptline.status > '" + DocumentStatus.RCV_INPROGRESS + "'";
					List listResult = dbs.query(queryString.toString()) ;
					receiptHeader.setReceiptLineList(listResult);
				}
				this.setStatus(dbs.getStatus()) ;
				result = receiptHeaderList;
			}
			this.setStatus(Status.SUCCEEDED) ;
			}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}