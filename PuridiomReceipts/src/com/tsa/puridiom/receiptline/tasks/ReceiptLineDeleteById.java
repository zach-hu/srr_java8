package com.tsa.puridiom.receiptline.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ReceiptLineDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		ReceiptLine receiptLine = null ;
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
			if(receiptLine == null)
			{
				receiptLine = new ReceiptLine();
			}
			ReceiptLineSetValuesPK setValues = new ReceiptLineSetValuesPK();
			setValues.setValues(incomingRequest, receiptLine);
			dbs.delete(receiptLine);
			this.setStatus(dbs.getStatus()) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return receiptLine ;
	}

}