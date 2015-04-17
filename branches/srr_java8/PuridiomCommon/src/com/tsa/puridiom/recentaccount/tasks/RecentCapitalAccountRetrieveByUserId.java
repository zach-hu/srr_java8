package com.tsa.puridiom.recentaccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.XrefCombo;

/**  Tasks RecentNonCapitalAccountRetrieveByUserId - Retrieve recent non capital account by user id */

public class RecentCapitalAccountRetrieveByUserId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;


		XrefCombo xrefComboUniqueCPTL = (XrefCombo) incomingRequest.get("xrefComboUniqueCPTL");
		String	accfld1 = HiltonUtility.ckNull(xrefComboUniqueCPTL.getCode2());
		String	accfld2 = HiltonUtility.ckNull(xrefComboUniqueCPTL.getCode3());
		String	accfld3 = HiltonUtility.ckNull(xrefComboUniqueCPTL.getCode4());

		String accountStringCPTL = accfld1 + "-" + accfld2 + "-" + accfld3;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String userId = (String) incomingRequest.get("userId");

			String queryString = "from RecentAccount as RecentAccount " +
								 "where RecentAccount.id.userId = '" + userId + "' " +
								 "and RecentAccount.id.accountString like '" + accountStringCPTL + "-%' " +
								 "order by RecentAccount.accountCount DESC";

			 result = dbs.query(queryString.toString()) ;

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}