package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AccountPK;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class ApprovalAMSBuildApproverList extends Task
{
	Map incomingRequest;
	List appLogList = new ArrayList();
	public Object executeTask(Object object) throws Exception
	{
		incomingRequest = (Map)object;
		String oid = (String)incomingRequest.get("organizationId");
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");

		List accountList = (List)incomingRequest.get("accountList");
		List rqlList = (List)incomingRequest.get("requisitionLineList");
		String icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
		String forwardTo = "";

		try
		{
			for (int i = 0; i < accountList.size(); i++)
			{

				Account account = (Account)accountList.get(i);
				AccountPK accountPK = (AccountPK)account.getComp_id();
				BigDecimal icLine = accountPK.getIcLine();
				if (!Utility.isEmpty(account.getFld5()))
				{
					if (account.getFld5().length() >= 5)
					{
						String fld5 = account.getFld5();
						String userId = fld5.substring(0, 5);
						UserProfile pUser = UserManager.getInstance().getUser(oid,userId);
						ApprovalLog appLog = new ApprovalLog();
						ApprovalLogPK pk = new ApprovalLogPK();
						pk.setIcHeader(new BigDecimal(icHeader));
						pk.setIcLine(icLine);
						pk.setSequence(new BigDecimal(i));
						pk.setUserId(userId);
						appLog.setComp_id(pk);
						appLog.setCallForward(userId);
						appLog.setApproverName(pUser.getDisplayName());
						appLog.setRuleType("AMS");
						appLog.setRuleOrder(new BigDecimal(30));
						appLog.setAmount(new BigDecimal(0));
						appLog.setApproverAmount(new BigDecimal(0)) ;
						appLog.setApproverType("U") ;
						appLog.setApproved("A") ;
						appLog.setAuthorized("N") ;
						appLog.setRuleAction("A") ;
						appLog.setApproverSig("") ;
						appLog.setApproverNotes("") ;
						appLog.setFyiApprover("Y");
						appLog.setPoolid(" ") ;
						appLog.setPooldesc("") ;
						appLog.setRuleSource(" ") ;
						appLog.setUdfValues("[AMS Rule.]") ;
						appLog.setExcludeLess(new BigDecimal(0)) ;
						appLogList.add(appLog) ;
					}
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return appLogList;
	}
}
