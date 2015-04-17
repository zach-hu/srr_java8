package com.tsa.puridiom.capitalclearingaccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class CapitalClearingAccountRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CapitalCleaningAccount as account where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("CapitalClearingAccount_icHeader"))
		{
			String icHeader = (String) incomingRequest.get("CapitalClearingAccount_icHeader");
			args.add(icHeader);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.icHeader = ?");
		}
		if(incomingRequest.containsKey("CapitalClearingAccount_entity"))
		{
			String entity = (String) incomingRequest.get("CapitalClearingAccount_entity");
			args.add(entity);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.entity = ?");
		}
		if(incomingRequest.containsKey("CapitalClearingAccount_department"))
		{
			String department = (String) incomingRequest.get("CapitalClearingAccount_department");
			args.add(department);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.department = ?");
		}
		if(incomingRequest.containsKey("CapitalClearingAccount_account"))
		{
			String account = (String) incomingRequest.get("CapitalClearingAccount_account");
			args.add(account);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.account = ?");
		}

		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}