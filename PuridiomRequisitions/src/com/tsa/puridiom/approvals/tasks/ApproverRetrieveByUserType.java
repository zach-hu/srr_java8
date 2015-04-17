package com.tsa.puridiom.approvals.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class ApproverRetrieveByUserType extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			String udf1Code = (String) incomingRequest.get("AppRule_udf1Code");
			BigDecimal bdApprovalAmount = (BigDecimal) incomingRequest.get("AppRule_amount");

			String queryString = "SELECT appRule.id.userId FROM UserProfile as userProfile, AppRule as appRule WHERE userProfile.userId = appRule.id.userId " +
											"and appRule.id.udf1Code = ? and appRule.amount >= ? " +
											"and (userProfile.userType = 'VP' or userProfile.userType = 'SVP') order by appRule.amount ASC";
			List resultList = dbs.query(queryString, new Object[] {udf1Code, bdApprovalAmount} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}