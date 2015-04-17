package com.tsa.puridiom.receiptheader.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class ReceiptHeaderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
		if(receiptHeader == null)
		{
			receiptHeader = new ReceiptHeader();
		}
		ReceiptHeaderSetValuesPK setValues = new ReceiptHeaderSetValuesPK();
		setValues.setValues(incomingRequest, receiptHeader);
		dbs.delete(receiptHeader);
		this.setStatus(dbs.getStatus()) ;
		return receiptHeader ;
	}

}