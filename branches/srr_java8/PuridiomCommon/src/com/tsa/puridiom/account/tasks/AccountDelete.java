package com.tsa.puridiom.account.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class AccountDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Account account = (Account)incomingRequest.get("account");
		if(account == null)
		{
			account = new Account();
		}
		AccountSetValuesPK setValues = new AccountSetValuesPK();
		setValues.setValues(incomingRequest, account);
		dbs.delete(account);
		this.setStatus(dbs.getStatus()) ;
		return account ;
	}

}