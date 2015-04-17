package com.tsa.puridiom.recentreceipt.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecentReceiptRetrieveHeaderBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentReceipt as recentreceipt, ReceiptHeader as receiptheader where 1=1 ");
		if(incomingRequest.containsKey("RecentReceipt_receivedBy"))
		{
			String requisitionerCode = (String) incomingRequest.get("RecentReceipt_receivedBy");
			queryString.append(" AND recentreceipt.id.receivedBy = '" + requisitionerCode + "'");
		}
		if(incomingRequest.containsKey("RecentReceipt_icRecHeader"))
		{
			String icReqHeader = (String) incomingRequest.get("RecentReceipt_icRecHeader");
			queryString.append(" AND recentreceipt.id.icRecHeader = '" + icReqHeader + "'");
		}
		if(incomingRequest.containsKey("RecentReceipt_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("RecentReceipt_dateEntered");
			queryString.append(" AND recentreceipt.dateEntered = '" + dateEntered + "'");
		}
		
		queryString.append(" AND recentreceipt.id.icRecHeader = receiptheader.icRecHeader");
		queryString.append(" ORDER BY recentreceipt.id.icRecHeader DESC");

		List list = dbs.query(queryString.toString(), new Object[] {}, 11) ;
		List	result = new ArrayList();

		if (list != null && list.size() > 0) {
		    for (int i=0; i < list.size(); i++ ) {
		        Object obj[] = (Object[])list.get(i);
		        ReceiptHeader receiptHeader = (ReceiptHeader) obj[1];
		        result.add(receiptHeader);
		    }
		}

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}