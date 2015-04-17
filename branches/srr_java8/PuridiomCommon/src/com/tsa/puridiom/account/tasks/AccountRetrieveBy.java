package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AccountRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Account as account where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList();
		if(incomingRequest.containsKey("Account_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("Account_icHeader");
			args.add(icHeader);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.id.icHeader = ?");
		}
		if(incomingRequest.containsKey("Account_icLine"))
		{			
			String icLine = (String) incomingRequest.get("Account_icLine");
			args.add(icLine);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.id.icLine = ?");
		}
		if(incomingRequest.containsKey("Account_sequence"))
		{			
			String sequence = (String) incomingRequest.get("Account_sequence");
			args.add(sequence);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.id.sequence = ?");
		}
		if(incomingRequest.containsKey("Account_accountType"))
		{			
			String accountType = (String) incomingRequest.get("Account_accountType");
			args.add(accountType);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.accountType = ?");
		}
		if(incomingRequest.containsKey("Account_fld1"))
		{			
			String fld1 = (String) incomingRequest.get("Account_fld1");
			args.add(fld1);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld1 = ?");
		}
		if(incomingRequest.containsKey("Account_fld2"))
		{			
			String fld2 = (String) incomingRequest.get("Account_fld2");
			args.add(fld2);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld2 = ?");
		}
		if(incomingRequest.containsKey("Account_fld3"))
		{			
			String fld3 = (String) incomingRequest.get("Account_fld3");
			args.add(fld3);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld3 = ?");
		}
		if(incomingRequest.containsKey("Account_fld4"))
		{			
			String fld4 = (String) incomingRequest.get("Account_fld4");
			args.add(fld4);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld4 = ?");
		}
		if(incomingRequest.containsKey("Account_fld5"))
		{			
			String fld5 = (String) incomingRequest.get("Account_fld5");
			args.add(fld5);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld5 = ?");
		}
		if(incomingRequest.containsKey("Account_fld6"))
		{			
			String fld6 = (String) incomingRequest.get("Account_fld6");
			args.add(fld6);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld6 = ?");
		}
		if(incomingRequest.containsKey("Account_fld7"))
		{			
			String fld7 = (String) incomingRequest.get("Account_fld7");
			args.add(fld7);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld7 = ?");
		}
		if(incomingRequest.containsKey("Account_fld8"))
		{			
			String fld8 = (String) incomingRequest.get("Account_fld8");
			args.add(fld8);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld8 = ?");
		}
		if(incomingRequest.containsKey("Account_fld9"))
		{			
			String fld9 = (String) incomingRequest.get("Account_fld9");
			args.add(fld9);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld9 = ?");
		}
		if(incomingRequest.containsKey("Account_fld10"))
		{			
			String fld10 = (String) incomingRequest.get("Account_fld10");
			args.add(fld10);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld10 = ?");
		}
		if(incomingRequest.containsKey("Account_fld11"))
		{			
			String fld11 = (String) incomingRequest.get("Account_fld11");
			args.add(fld11);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld11 = ?");
		}
		if(incomingRequest.containsKey("Account_fld12"))
		{			
			String fld12 = (String) incomingRequest.get("Account_fld12");
			args.add(fld12);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld12 = ?");
		}
		if(incomingRequest.containsKey("Account_fld13"))
		{			
			String fld13 = (String) incomingRequest.get("Account_fld13");
			args.add(fld13);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld13 = ?");
		}
		if(incomingRequest.containsKey("Account_fld14"))
		{			
			String fld14 = (String) incomingRequest.get("Account_fld14");
			args.add(fld14);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld14 = ?");
		}
		if(incomingRequest.containsKey("Account_fld15"))
		{			
			String fld15 = (String) incomingRequest.get("Account_fld15");
			args.add(fld15);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.fld15 = ?");
		}
		if(incomingRequest.containsKey("Account_allocPercent"))
		{			
			String allocPercent = (String) incomingRequest.get("Account_allocPercent");
			args.add(allocPercent);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.allocPercent = ?");
		}
		if(incomingRequest.containsKey("Account_allocAmount"))
		{			
			String allocAmount = (String) incomingRequest.get("Account_allocAmount");
			args.add(allocAmount);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.allocAmount = ?");
		}
		if(incomingRequest.containsKey("Account_accountTitle"))
		{			
			String accountTitle = (String) incomingRequest.get("Account_accountTitle");
			args.add(accountTitle);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.accountTitle = ?");
		}
		if(incomingRequest.containsKey("Account_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("Account_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.dateEntered = ?");
		}
		if(incomingRequest.containsKey("Account_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("Account_dateExpires");
			args.add(dateExpires);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.dateExpires = ?");
		}
		if(incomingRequest.containsKey("Account_status"))
		{			
			String status = (String) incomingRequest.get("Account_status");
			args.add(status);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.status = ?");
		}
		if(incomingRequest.containsKey("Account_owner"))
		{			
			String owner = (String) incomingRequest.get("Account_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.owner = ?");
		}
		if(incomingRequest.containsKey("Account_allocMethod"))
		{			
			String allocMethod = (String) incomingRequest.get("Account_allocMethod");
			args.add(allocMethod);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.allocMethod = ?");
		}
		if(incomingRequest.containsKey("Account_allocQty"))
		{			
			String allocQty = (String) incomingRequest.get("Account_allocQty");
			args.add(allocQty);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.allocQty = ?");
		}
		if(incomingRequest.containsKey("Account_recQty"))
		{			
			String recQty = (String) incomingRequest.get("Account_recQty");
			args.add(recQty);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.recQty = ?");
		}
		if(incomingRequest.containsKey("Account_icLastRec"))
		{			
			String icLastRec = (String) incomingRequest.get("Account_icLastRec");
			args.add(icLastRec);
			types.add(Hibernate.STRING);
			queryString.append(" AND account.icLastRec = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(),
				(Type[])types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}