package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.common.documents.AccountRollup;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountRollupRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			List accountRollupList = new ArrayList();
			String icHeaderString = (String) incomingRequest.get("Account_icHeader");
			if (Utility.isEmpty(icHeaderString))
			{
				icHeaderString = (String) incomingRequest.get("icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					throw new Exception("Account_icHeader cannot be empty.");
				}
			}
			BigDecimal icHeader = new BigDecimal ( icHeaderString );

			//This query adds allocAmount Account String.
			String queryString = "select sum(a.allocAmount), a.fld1,a.fld2,a.fld3,a.fld4,a.fld5,a.fld6,a.fld7,a.fld8,a.fld9,a.fld10,a.fld11,a.fld12,a.fld13,a.fld14,a.fld15 "
				+ "from Account as a where a.id.icHeader = ? "
				+ "group by a.fld1,a.fld2,a.fld3,a.fld4,a.fld5,a.fld6,a.fld7,a.fld8,a.fld9,a.fld10,a.fld11,a.fld12,a.fld13,a.fld14,a.fld15";

			List accountList = dbs.query(queryString, new Object[] {icHeader, } , new Type[] { Hibernate.BIG_DECIMAL});

			if (accountList != null)
			{
			    for (int i=0; i < accountList.size(); i++)
			    {
			        Object acctObj[] = (Object[]) accountList.get(i);
			        BigDecimal bdAllocAmount = (BigDecimal) acctObj[0];
			        String fld1 = (String) acctObj[1];
			        String fld2 = (String) acctObj[2];
			        String fld3 = (String) acctObj[3];
			        String fld4 = (String) acctObj[4];
			        String fld5 = (String) acctObj[5];
			        String fld6 = (String) acctObj[6];
			        String fld7 = (String) acctObj[7];
			        String fld8 = (String) acctObj[8];
			        String fld9 = (String) acctObj[9];
			        String fld10 = (String) acctObj[10];
			        String fld11 = (String) acctObj[11];
			        String fld12 = (String) acctObj[12];
			        String fld13 = (String) acctObj[13];
			        String fld14 = (String) acctObj[14];
			        String fld15 = (String) acctObj[15];

			        AccountRollup acctRollup = new AccountRollup();
			        acctRollup.setAllocAmount(bdAllocAmount);
			        acctRollup.setFld1(fld1);
			        acctRollup.setFld2(fld2);
			        acctRollup.setFld3(fld3);
			        acctRollup.setFld4(fld4);
			        acctRollup.setFld5(fld5);
			        acctRollup.setFld6(fld6);
			        acctRollup.setFld7(fld7);
			        acctRollup.setFld8(fld8);
			        acctRollup.setFld9(fld9);
			        acctRollup.setFld10(fld10);
			        acctRollup.setFld11(fld11);
			        acctRollup.setFld12(fld12);
			        acctRollup.setFld13(fld13);
			        acctRollup.setFld14(fld14);
			        acctRollup.setFld15(fld15);
			        accountRollupList.add(acctRollup);
			    }
			}

			result = accountRollupList;

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