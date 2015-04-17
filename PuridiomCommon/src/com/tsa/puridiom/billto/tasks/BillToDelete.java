package com.tsa.puridiom.billto.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class BillToDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BillTo billTo = (BillTo)incomingRequest.get("billTo");
		if(billTo == null)
		{
			billTo = new BillTo();
		}
		BillToSetValuesPK setValues = new BillToSetValuesPK();
		setValues.setValues(incomingRequest, billTo);
		dbs.delete(billTo);
		this.setStatus(dbs.getStatus()) ;
		return billTo ;
	}

}