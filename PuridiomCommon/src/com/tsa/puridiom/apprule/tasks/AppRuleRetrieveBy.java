package com.tsa.puridiom.apprule.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AppRuleRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AppRule as apprule where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AppRule_userId"))
		{
			String userId = (String) incomingRequest.get("AppRule_userId");
			args.add(userId);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.userId = ?");
		}
		if(incomingRequest.containsKey("AppRule_amount"))
		{
			String amount = (String) incomingRequest.get("AppRule_amount");
			args.add(amount);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.amount = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("AppRule_udf1Code");
			args.add(udf1Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.udf1Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("AppRule_udf2Code");
			args.add(udf2Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.udf2Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("AppRule_udf3Code");
			args.add(udf3Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.udf3Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("AppRule_udf4Code");
			args.add(udf4Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.udf4Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("AppRule_udf5Code");
			args.add(udf5Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.udf5Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf6Code"))
		{
			String udf6Code = (String) incomingRequest.get("AppRule_udf6Code");
			args.add(udf6Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.udf6Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf7Code"))
		{
			String udf7Code = (String) incomingRequest.get("AppRule_udf7Code");
			args.add(udf7Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.id.udf7Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf8Code"))
		{
			String udf8Code = (String) incomingRequest.get("AppRule_udf8Code");
			args.add(udf8Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.udf8Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf9Code"))
		{
			String udf9Code = (String) incomingRequest.get("AppRule_udf9Code");
			args.add(udf9Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.udf9Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_udf10Code"))
		{
			String udf10Code = (String) incomingRequest.get("AppRule_udf10Code");
			args.add(udf10Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.udf10Code = ?");
		}
		if(incomingRequest.containsKey("AppRule_approverLevel"))
		{
			String approverLevel = (String) incomingRequest.get("AppRule_approverLevel");
			args.add(approverLevel);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.approverLevel = ?");
		}
		if(incomingRequest.containsKey("AppRule_excludeLess"))
		{
			String excludeLess = (String) incomingRequest.get("AppRule_excludeLess");
			args.add(excludeLess);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.excludeLess = ?");
		}
		if(incomingRequest.containsKey("AppRule_fyiApprover"))
		{
			String fyiApprover = (String) incomingRequest.get("AppRule_fyiApprover");
			args.add(fyiApprover);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.fyiApprover = ?");
		}
		if(incomingRequest.containsKey("AppRule_requiredApprover"))
		{
			String requiredApprover = (String) incomingRequest.get("AppRule_requiredApprover");
			args.add(requiredApprover);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.requiredApprover = ?");
		}
		if(incomingRequest.containsKey("AppRule_notes"))
		{
			String notes = (String) incomingRequest.get("AppRule_notes");
			args.add(notes);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.notes = ?");
		}
		if(incomingRequest.containsKey("AppRule_advisor"))
		{
			String advisor = (String) incomingRequest.get("AppRule_advisor");
			args.add(advisor);
			types.add(Hibernate.STRING);
			queryString.append(" AND apprule.advisor = ?");
		}

		/*  please do not remove this order by!!!
		 * 	 it sorts the list of approver rules, which is especially helpful when an approver has a lot of rules
		 */
		queryString.append( "ORDER BY apprule.id.udf1Code, apprule.amount, apprule.id.userId ASC");

		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
