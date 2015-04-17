package com.puridiom.service.budget.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetAudit;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author renzo
 *
 */
public class BudgetAuditCreateFromService extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

		try
		{
			BigDecimal budgetCenterId = (BigDecimal)incomingRequest.get("BudgetCenter_budgetId");
			//String authority = (String)incomingRequest.get("authority");
			BigDecimal icHeader = (BigDecimal)incomingRequest.get("icHeader");
			BigDecimal icLine = (BigDecimal)incomingRequest.get("icLine");
			String formType = (String)incomingRequest.get("formType");
			String budgetAction = (String)incomingRequest.get("budgetAction");
			BigDecimal bdAmt = (BigDecimal)incomingRequest.get("budgetAmount");
			String userId = (String)incomingRequest.get("userId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            BigDecimal auditIc = new BigDecimal(incomingRequest.get("auditIc").toString());

			BudgetAudit bAudit = new BudgetAudit() ;
//			bAudit.setAuditId(new BigDecimal(ukg.getUniqueKey().toString())) ;
			bAudit.setAuditId(auditIc);
			bAudit.setBudgetId(budgetCenterId) ;
			//bAudit.setActionType(ls_action_type) ;
			//bAudit.setAuthority(authority) ;
			bAudit.setIcHeader(icHeader) ;
			bAudit.setIcLine(icLine) ;
			bAudit.setParentType(formType) ;
			//bAudit.setLineType(bType) ;
			//bAudit.setActionCode(bAction) ;
			bAudit.setActionType(budgetAction) ;
			bAudit.setActionMake("1") ;
			bAudit.setActionDate(Dates.today("", userTimeZone)) ;
			bAudit.setActionTime(Dates.getNow("", userTimeZone));
			bAudit.setPriorAmt(new BigDecimal(0)) ;
			bAudit.setTranAmt(bdAmt) ;
			bAudit.setOwner(userId) ;
			bAudit.setExportCode("");
			bAudit.setExportDate("");
			bAudit.setBOperator(new BigDecimal(1)) ;
			bAudit.setNewRecord(true) ;

			result = bAudit;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			return null;
		}
		return result;
	}

}
