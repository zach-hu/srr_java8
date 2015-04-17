package com.tsa.puridiom.rules;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AccountOrderHoyaRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
	    	DBSession dbs = (DBSession) incomingRequest.get("dbsession");

	    	// line accounts
	    	List poLineList = (List) incomingRequest.get("poLineList");
	        for (Iterator it = poLineList.iterator(); it.hasNext(); )
	        {
	        	PoLine poLine = (PoLine) it.next();
	        	List accountList = poLine.getAccountList();

	        	for (Iterator it2 = accountList.iterator(); it2.hasNext(); )
			    {
	        		Account account = (Account) it2.next();
	        		String fld5 = account.getFld5();
					String fld6 = account.getFld6();

					String queryString = "";
					List resultList = null;

					if ( !HiltonUtility.isEmpty(fld5) || poLine.getAsset().equals("Y") )
					{
						queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC05' " +
						"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
						resultList = dbs.query(queryString, new Object[] {fld5} , new Type[] { Hibernate.STRING}) ;
						if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
						{
							incomingRequest.put("account-line-udf5", "failed");
						}
					}
					if ( !HiltonUtility.isEmpty(fld6))
					{
						queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC06' " +
						"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
						resultList = dbs.query(queryString, new Object[] {fld6} , new Type[] { Hibernate.STRING}) ;
						if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
						{
							incomingRequest.put("account-line-udf6", "failed");
						}
					}
			    }
	        }

	        // header accounts
	        List accountList = (List) incomingRequest.get("accounts");
	        for (Iterator it = accountList.iterator(); it.hasNext(); )
	        {
        		Account account = (Account) it.next();
        		String fld5 = account.getFld5();
				String fld6 = account.getFld6();

				String queryString = "";
				List resultList = null;

				if ( !HiltonUtility.isEmpty(fld5))
				{
					queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC05' " +
					"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
					resultList = dbs.query(queryString, new Object[] {fld5} , new Type[] { Hibernate.STRING}) ;
					if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
					{
						incomingRequest.put("account-header-udf5", "failed");
					}
				}
				if ( !HiltonUtility.isEmpty(fld6))
				{
					queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC06' " +
					"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
					resultList = dbs.query(queryString, new Object[] {fld6} , new Type[] { Hibernate.STRING}) ;
					if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
					{
						incomingRequest.put("account-header-udf6", "failed");
					}
				}
	        }

        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at AccountOrderHoyaRules", e);
		}
		return result;
    }
}