package com.tsa.puridiom.budgetcenter.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BudgetCenterRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BudgetCenter as budgetcenter where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BudgetCenter_budgetId"))
		{			
			String budgetId = (String) incomingRequest.get("BudgetCenter_budgetId");
			args.add(budgetId);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.id.budgetId = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budgetPeriod"))
		{			
			String budgetPeriod = (String) incomingRequest.get("BudgetCenter_budgetPeriod");
			args.add(budgetPeriod);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budgetPeriod = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget1"))
		{			
			String budget1 = (String) incomingRequest.get("BudgetCenter_budget1");
			args.add(budget1);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget1 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget2"))
		{			
			String budget2 = (String) incomingRequest.get("BudgetCenter_budget2");
			args.add(budget2);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget2 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget3"))
		{			
			String budget3 = (String) incomingRequest.get("BudgetCenter_budget3");
			args.add(budget3);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget3 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget4"))
		{			
			String budget4 = (String) incomingRequest.get("BudgetCenter_budget4");
			args.add(budget4);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget4 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget5"))
		{			
			String budget5 = (String) incomingRequest.get("BudgetCenter_budget5");
			args.add(budget5);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget5 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget6"))
		{			
			String budget6 = (String) incomingRequest.get("BudgetCenter_budget6");
			args.add(budget6);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget6 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget7"))
		{			
			String budget7 = (String) incomingRequest.get("BudgetCenter_budget7");
			args.add(budget7);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget7 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget8"))
		{			
			String budget8 = (String) incomingRequest.get("BudgetCenter_budget8");
			args.add(budget8);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget8 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget9"))
		{			
			String budget9 = (String) incomingRequest.get("BudgetCenter_budget9");
			args.add(budget9);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget9 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget10"))
		{			
			String budget10 = (String) incomingRequest.get("BudgetCenter_budget10");
			args.add(budget10);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget10 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget11"))
		{			
			String budget11 = (String) incomingRequest.get("BudgetCenter_budget11");
			args.add(budget11);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget11 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget12"))
		{			
			String budget12 = (String) incomingRequest.get("BudgetCenter_budget12");
			args.add(budget12);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget12 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget13"))
		{			
			String budget13 = (String) incomingRequest.get("BudgetCenter_budget13");
			args.add(budget13);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget13 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget14"))
		{			
			String budget14 = (String) incomingRequest.get("BudgetCenter_budget14");
			args.add(budget14);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget14 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budget15"))
		{			
			String budget15 = (String) incomingRequest.get("BudgetCenter_budget15");
			args.add(budget15);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budget15 = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_budgeted"))
		{			
			String budgeted = (String) incomingRequest.get("BudgetCenter_budgeted");
			args.add(budgeted);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.budgeted = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_preEncumbered"))
		{			
			String preEncumbered = (String) incomingRequest.get("BudgetCenter_preEncumbered");
			args.add(preEncumbered);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.preEncumbered = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_encumbered"))
		{			
			String encumbered = (String) incomingRequest.get("BudgetCenter_encumbered");
			args.add(encumbered);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.encumbered = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_expensed"))
		{			
			String expensed = (String) incomingRequest.get("BudgetCenter_expensed");
			args.add(expensed);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.expensed = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_owner"))
		{			
			String owner = (String) incomingRequest.get("BudgetCenter_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.owner = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_ownerPassword"))
		{			
			String ownerPassword = (String) incomingRequest.get("BudgetCenter_ownerPassword");
			args.add(ownerPassword);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.ownerPassword = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_status"))
		{			
			String status = (String) incomingRequest.get("BudgetCenter_status");
			args.add(status);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.status = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_approved"))
		{			
			String approved = (String) incomingRequest.get("BudgetCenter_approved");
			args.add(approved);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.approved = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_projectId"))
		{			
			String projectId = (String) incomingRequest.get("BudgetCenter_projectId");
			args.add(projectId);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.projectId = ?");
		}
		if(incomingRequest.containsKey("BudgetCenter_comments"))
		{			
			String comments = (String) incomingRequest.get("BudgetCenter_comments");
			args.add(comments);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetcenter.comments = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}