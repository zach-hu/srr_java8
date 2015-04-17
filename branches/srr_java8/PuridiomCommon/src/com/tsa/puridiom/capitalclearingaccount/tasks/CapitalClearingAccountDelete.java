package com.tsa.puridiom.capitalclearingaccount.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class CapitalClearingAccountDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		CapitalClearingAccount capitalClearingAccount = (CapitalClearingAccount)incomingRequest.get("capitalClearingAccount");
		if(capitalClearingAccount == null)
		{
			capitalClearingAccount = new CapitalClearingAccount();
		}
		CapitalClearingAccountSetValuesPK setValues = new CapitalClearingAccountSetValuesPK();
		setValues.setValues(incomingRequest, capitalClearingAccount);
		dbs.delete(capitalClearingAccount);
		this.setStatus(dbs.getStatus()) ;
		return capitalClearingAccount ;
	}

}