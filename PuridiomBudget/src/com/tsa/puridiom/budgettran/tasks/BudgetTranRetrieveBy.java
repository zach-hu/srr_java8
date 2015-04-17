package com.tsa.puridiom.budgettran.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BudgetTranRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BudgetTran as budgettran where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		
		if(incomingRequest.containsKey("BudgetTran_tranId"))
		{			
			String tranId = (String) incomingRequest.get("BudgetTran_tranId");
			args.add(tranId);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.id.tranId = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_auditId"))
		{			
			String auditId = (String) incomingRequest.get("BudgetTran_auditId");
			args.add(auditId);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.auditId = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_budgetId"))
		{			
			String budgetId = (String) incomingRequest.get("BudgetTran_budgetId");
			args.add(budgetId);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.budgetId = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_tranType"))
		{			
			String tranType = (String) incomingRequest.get("BudgetTran_tranType");
			args.add(tranType);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.tranType = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_tranDate"))
		{			
			String tranDate = (String) incomingRequest.get("BudgetTran_tranDate");
			args.add(tranDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.tranDate = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_tranTime"))
		{			
			String tranTime = (String) incomingRequest.get("BudgetTran_tranTime");
			args.add(tranTime);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.tranTime = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_preEncumbered"))
		{			
			String preEncumbered = (String) incomingRequest.get("BudgetTran_preEncumbered");
			args.add(preEncumbered);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.preEncumbered = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_encumbered"))
		{			
			String encumbered = (String) incomingRequest.get("BudgetTran_encumbered");
			args.add(encumbered);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.encumbered = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_expensed"))
		{			
			String expensed = (String) incomingRequest.get("BudgetTran_expensed");
			args.add(expensed);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.expensed = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_balance"))
		{			
			String balance = (String) incomingRequest.get("BudgetTran_balance");
			args.add(balance);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.balance = ?");
		}
		if(incomingRequest.containsKey("BudgetTran_owner"))
		{			
			String owner = (String) incomingRequest.get("BudgetTran_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgettran.owner = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}