package com.tsa.puridiom.capitalclearingaccount.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class CapitalClearingAccountDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		CapitalClearingAccount capitalClearingaccount = (CapitalClearingAccount)incomingRequest.get("capitalClearingAccount");
		if(capitalClearingaccount == null)
		{
			capitalClearingaccount = new CapitalClearingAccount();
		}
		CapitalClearingAccountSetValuesPK setValues = new CapitalClearingAccountSetValuesPK();
		setValues.setValues(incomingRequest, capitalClearingaccount);
		dbs.delete(capitalClearingaccount);
		this.setStatus(dbs.getStatus()) ;
		return capitalClearingaccount ;
	}

}