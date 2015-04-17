package com.tsa.puridiom.apprulesext.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AppRulesExtRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AppRulesExt as apprulesext where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AppRulesExt_ruleNumber"))
		{			
			String ruleNumber = (String) incomingRequest.get("AppRulesExt_ruleNumber");
			args.add(ruleNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.id.ruleNumber = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_ruleType"))
		{			
			String ruleType = (String) incomingRequest.get("AppRulesExt_ruleType");
			args.add(ruleType);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.id.ruleType = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_ruleOrder"))
		{			
			String ruleOrder = (String) incomingRequest.get("AppRulesExt_ruleOrder");
			args.add(ruleOrder);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.ruleOrder = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_ruleFilename"))
		{			
			String ruleFilename = (String) incomingRequest.get("AppRulesExt_ruleFilename");
			args.add(ruleFilename);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.ruleFilename = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_routto"))
		{			
			String routto = (String) incomingRequest.get("AppRulesExt_routto");
			args.add(routto);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.routto = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_ruleAction"))
		{			
			String ruleAction = (String) incomingRequest.get("AppRulesExt_ruleAction");
			args.add(ruleAction);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.ruleAction = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_ruleText"))
		{			
			String ruleText = (String) incomingRequest.get("AppRulesExt_ruleText");
			args.add(ruleText);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.ruleText = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_notes"))
		{			
			String notes = (String) incomingRequest.get("AppRulesExt_notes");
			args.add(notes);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.notes = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_approverAmount"))
		{			
			String approverAmount = (String) incomingRequest.get("AppRulesExt_approverAmount");
			args.add(approverAmount);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.approverAmount = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_requiredAuthority"))
		{			
			String requiredAuthority = (String) incomingRequest.get("AppRulesExt_requiredAuthority");
			args.add(requiredAuthority);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.requiredAuthority = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_reqdAuthObject"))
		{			
			String reqdAuthObject = (String) incomingRequest.get("AppRulesExt_reqdAuthObject");
			args.add(reqdAuthObject);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.reqdAuthObject = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_reqdAuthName"))
		{			
			String reqdAuthName = (String) incomingRequest.get("AppRulesExt_reqdAuthName");
			args.add(reqdAuthName);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.reqdAuthName = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_reqdAuthSource"))
		{			
			String reqdAuthSource = (String) incomingRequest.get("AppRulesExt_reqdAuthSource");
			args.add(reqdAuthSource);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.reqdAuthSource = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_reqdAuthValue"))
		{			
			String reqdAuthValue = (String) incomingRequest.get("AppRulesExt_reqdAuthValue");
			args.add(reqdAuthValue);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.reqdAuthValue = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_fyiApprover"))
		{			
			String fyiApprover = (String) incomingRequest.get("AppRulesExt_fyiApprover");
			args.add(fyiApprover);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.fyiApprover = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_requiredApprover"))
		{			
			String requiredApprover = (String) incomingRequest.get("AppRulesExt_requiredApprover");
			args.add(requiredApprover);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.requiredApprover = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_advisor"))
		{			
			String advisor = (String) incomingRequest.get("AppRulesExt_advisor");
			args.add(advisor);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.advisor = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_superRule"))
		{			
			String superRule = (String) incomingRequest.get("AppRulesExt_superRule");
			args.add(superRule);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.superRule = ?");
		}
		if(incomingRequest.containsKey("AppRulesExt_enabled"))
		{			
			String enabled = (String) incomingRequest.get("AppRulesExt_enabled");
			args.add(enabled);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprulesext.enabled = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
