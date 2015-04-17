package com.tsa.puridiom.approvals.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class ApprovalLogRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ApprovalLog as approvallog where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("ApprovalLog_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("ApprovalLog_icHeader");
			args.add(icHeader);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.id.icHeader = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_icLine"))
		{			
			String icLine = (String) incomingRequest.get("ApprovalLog_icLine");
			args.add(icLine);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.id.icLine = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_sequence"))
		{			
			String sequence = (String) incomingRequest.get("ApprovalLog_sequence");
			args.add(sequence);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.id.sequence = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_userId"))
		{			
			String userId = (String) incomingRequest.get("ApprovalLog_userId");
			args.add(userId);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.id.userId = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_amount"))
		{			
			String amount = (String) incomingRequest.get("ApprovalLog_amount");
			args.add(amount);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.amount = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_approverAmount"))
		{			
			String approverAmount = (String) incomingRequest.get("ApprovalLog_approverAmount");
			args.add(approverAmount);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.approverAmount = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_approved"))
		{			
			String approved = (String) incomingRequest.get("ApprovalLog_approved");
			args.add(approved);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.approved = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_udfValues"))
		{			
			String udfValues = (String) incomingRequest.get("ApprovalLog_udfValues");
			args.add(udfValues);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.udfValues = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_authorized"))
		{			
			String authorized = (String) incomingRequest.get("ApprovalLog_authorized");
			args.add(authorized);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.authorized = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_alternateUserid"))
		{			
			String alternateUserid = (String) incomingRequest.get("ApprovalLog_alternateUserid");
			args.add(alternateUserid);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.alternateUserid = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_dateAssigned"))
		{			
			String dateAssigned = (String) incomingRequest.get("ApprovalLog_dateAssigned");
			args.add(dateAssigned);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.dateAssigned = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_dateApproved"))
		{			
			String dateApproved = (String) incomingRequest.get("ApprovalLog_dateApproved");
			args.add(dateApproved);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.dateApproved = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_approverType"))
		{			
			String approverType = (String) incomingRequest.get("ApprovalLog_approverType");
			args.add(approverType);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.approverType = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_ruleType"))
		{			
			String ruleType = (String) incomingRequest.get("ApprovalLog_ruleType");
			args.add(ruleType);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.ruleType = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_poolid"))
		{			
			String poolid = (String) incomingRequest.get("ApprovalLog_poolid");
			args.add(poolid);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.poolid = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_ruleAction"))
		{			
			String ruleAction = (String) incomingRequest.get("ApprovalLog_ruleAction");
			args.add(ruleAction);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.ruleAction = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_approverSig"))
		{			
			String approverSig = (String) incomingRequest.get("ApprovalLog_approverSig");
			args.add(approverSig);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.approverSig = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_callForward"))
		{			
			String callForward = (String) incomingRequest.get("ApprovalLog_callForward");
			args.add(callForward);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.callForward = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_ruleSource"))
		{			
			String ruleSource = (String) incomingRequest.get("ApprovalLog_ruleSource");
			args.add(ruleSource);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.ruleSource = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_approverNotes"))
		{			
			String approverNotes = (String) incomingRequest.get("ApprovalLog_approverNotes");
			args.add(approverNotes);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.approverNotes = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_fyiApprover"))
		{			
			String fyiApprover = (String) incomingRequest.get("ApprovalLog_fyiApprover");
			args.add(fyiApprover);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.fyiApprover = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_requiredApprover"))
		{			
			String requiredApprover = (String) incomingRequest.get("ApprovalLog_requiredApprover");
			args.add(requiredApprover);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.requiredApprover = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_excludeLess"))
		{			
			String excludeLess = (String) incomingRequest.get("ApprovalLog_excludeLess");
			args.add(excludeLess);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.excludeLess = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_pooldesc"))
		{			
			String pooldesc = (String) incomingRequest.get("ApprovalLog_pooldesc");
			args.add(pooldesc);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.pooldesc = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_backupApprover"))
		{			
			String backupApprover = (String) incomingRequest.get("ApprovalLog_backupApprover");
			args.add(backupApprover);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.backupApprover = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_advisor"))
		{			
			String advisor = (String) incomingRequest.get("ApprovalLog_advisor");
			args.add(advisor);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.advisor = ?");
		}
		if(incomingRequest.containsKey("ApprovalLog_recommendation"))
		{			
			String recommendation = (String) incomingRequest.get("ApprovalLog_recommendation");
			args.add(recommendation);
			types.add(Hibernate.STRING);
			queryString.append(" AND approvallog.recommendation = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
