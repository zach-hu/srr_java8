package com.tsa.puridiom.rules;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AccountOrderVseRules extends Task
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
	    	String isValidFld2 = "valid"; 
	    	String isValidFld6 = "valid";
	    	String isValidFld6ByFld2 = "valid";
	    	
	        for (Iterator it = poLineList.iterator(); it.hasNext(); )
	        {
	        	PoLine poLine = (PoLine) it.next();
	        	List accountList = poLine.getAccountList();
	        	for (Iterator it2 = accountList.iterator(); it2.hasNext(); )
			    {
	        		Account account = (Account) it2.next();
	        		String fld2 = account.getFld2();
					String fld6 = account.getFld6();

					String queryString = "";
					List resultList = null;

					if ( !isValidFld2.equalsIgnoreCase("failed") && !HiltonUtility.isEmpty(fld2))
					{
						queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC02' " +
						"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
						resultList = dbs.query(queryString, new Object[] {fld2} , new Type[] { Hibernate.STRING}) ;
						
						if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
						{
							isValidFld2 = "failed";					
						}						
					}
					incomingRequest.put("isAccountLineFld2", isValidFld2);
					
					if ( !isValidFld6.equalsIgnoreCase("failed") && !HiltonUtility.isEmpty(fld6))
					{
						queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC06' " +
						"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
						resultList = dbs.query(queryString, new Object[] {fld6} , new Type[] { Hibernate.STRING}) ;
						if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
						{
							isValidFld6 = "failed";							
						}
					}	
					incomingRequest.put("isAccountLineFld6", isValidFld6);
					
					if ( !isValidFld6ByFld2.equalsIgnoreCase("failed") && !HiltonUtility.isEmpty(fld2))
					{
						if( !fld6.startsWith("6"))
						{
							isValidFld6ByFld2 = "failed";							
						}	
					}
					incomingRequest.put("isAccountLineFld6ByFld6", isValidFld6ByFld2);
					
			    }
	        }

	        // header accounts
	        List accountList = (List) incomingRequest.get("accounts");
	        for (Iterator it = accountList.iterator(); it.hasNext(); )
	        {
        		Account account = (Account) it.next();
        		String fld2 = account.getFld2();
				String fld6 = account.getFld6();

				String queryString = "";
				List resultList = null;

				if ( !isValidFld2.equalsIgnoreCase("failed") && !HiltonUtility.isEmpty(fld2))
				{
					queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC02' " +
					"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
					resultList = dbs.query(queryString, new Object[] {fld2} , new Type[] { Hibernate.STRING}) ;
					
					if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
					{
						isValidFld2 = "failed";
					}						
				}
				
				if ( !isValidFld6.equalsIgnoreCase("failed") && !HiltonUtility.isEmpty(fld6))
				{
					queryString = "Select count(*) from StdTable as StdTable where StdTable.id.tableType = 'AC06' " +
					"AND StdTable.status = '02' AND StdTable.id.tableKey = ? ";
					resultList = dbs.query(queryString, new Object[] {fld6} , new Type[] { Hibernate.STRING}) ;
					if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
					{
						isValidFld6 = "failed";
					}
				}
				
				if ( !isValidFld6ByFld2.equalsIgnoreCase("failed") && !HiltonUtility.isEmpty(fld2))
				{
					if( !fld6.startsWith("6"))
					{
						isValidFld6ByFld2 = "failed";						
					}	
				}
	        }
	        incomingRequest.put("isAccountHeaderFld2", isValidFld2);
	        incomingRequest.put("isAccountHeaderFld6", isValidFld6);
	        incomingRequest.put("isAccountHeaderFld6ByFld2", isValidFld6ByFld2);
	        
	        this.setStatus(Status.SUCCEEDED);	        
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "n Error occurred at AccountOrderVseRules" + e.getMessage());
			e.printStackTrace();
            throw e;
		}
		return result;
    }
}