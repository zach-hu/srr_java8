package com.tsa.puridiom.accountqxref.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AccountQxrefDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AccountQxref accountQxref = (AccountQxref)incomingRequest.get("accountQxref");
		if(accountQxref == null)
		{
			accountQxref = new AccountQxref();
		}
		AccountQxrefSetValuesPK setValues = new AccountQxrefSetValuesPK();
		setValues.setValues(incomingRequest, accountQxref);
		dbs.delete(accountQxref);
		this.setStatus(dbs.getStatus()) ;
		return accountQxref ;
	}

}