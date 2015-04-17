package com.tsa.puridiom.budgetaudit.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BudgetAuditRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BudgetAudit as budgetaudit where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BudgetAudit_auditId"))
		{			
			String auditId = (String) incomingRequest.get("BudgetAudit_auditId");
			args.add(auditId);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.id.auditId = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_budgetId"))
		{			
			String budgetId = (String) incomingRequest.get("BudgetAudit_budgetId");
			args.add(budgetId);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.budgetId = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_authority"))
		{			
			String authority = (String) incomingRequest.get("BudgetAudit_authority");
			args.add(authority);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.authority = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("BudgetAudit_icHeader");
			args.add(icHeader);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.icHeader = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_icLine"))
		{			
			String icLine = (String) incomingRequest.get("BudgetAudit_icLine");
			args.add(icLine);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.icLine = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_actionCode"))
		{			
			String actionCode = (String) incomingRequest.get("BudgetAudit_actionCode");
			args.add(actionCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.actionCode = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_actionType"))
		{			
			String actionType = (String) incomingRequest.get("BudgetAudit_actionType");
			args.add(actionType);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.actionType = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_actionMake"))
		{			
			String actionMake = (String) incomingRequest.get("BudgetAudit_actionMake");
			args.add(actionMake);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.actionMake = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_actionDate"))
		{			
			String actionDate = (String) incomingRequest.get("BudgetAudit_actionDate");
			args.add(actionDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.actionDate = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_actionTime"))
		{			
			String actionTime = (String) incomingRequest.get("BudgetAudit_actionTime");
			args.add(actionTime);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.actionTime = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_priorAmt"))
		{			
			String priorAmt = (String) incomingRequest.get("BudgetAudit_priorAmt");
			args.add(priorAmt);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.priorAmt = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_tranAmt"))
		{			
			String tranAmt = (String) incomingRequest.get("BudgetAudit_tranAmt");
			args.add(tranAmt);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.tranAmt = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_parentType"))
		{			
			String parentType = (String) incomingRequest.get("BudgetAudit_parentType");
			args.add(parentType);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.parentType = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_lineType"))
		{			
			String lineType = (String) incomingRequest.get("BudgetAudit_lineType");
			args.add(lineType);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.lineType = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_owner"))
		{			
			String owner = (String) incomingRequest.get("BudgetAudit_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.owner = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_budgetFlag"))
		{			
			String budgetFlag = (String) incomingRequest.get("BudgetAudit_budgetFlag");
			args.add(budgetFlag);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.budgetFlag = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_exportCode"))
		{			
			String exportCode = (String) incomingRequest.get("BudgetAudit_exportCode");
			args.add(exportCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.exportCode = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_exportDate"))
		{			
			String exportDate = (String) incomingRequest.get("BudgetAudit_exportDate");
			args.add(exportDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.exportDate = ?");
		}
		if(incomingRequest.containsKey("BudgetAudit_exportGroup"))
		{			
			String exportGroup = (String) incomingRequest.get("BudgetAudit_exportGroup");
			args.add(exportGroup);
			types.add(Hibernate.STRING);
			queryString.append(" AND budgetaudit.exportGroup = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}