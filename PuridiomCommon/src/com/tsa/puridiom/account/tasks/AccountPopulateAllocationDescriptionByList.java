/*
 * Created on July 17, 2008
 */
package com.tsa.puridiom.account.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import org.hibernate.type.Type;
import org.hibernate.Hibernate;
import com.tsa.puridiom.common.utility.HiltonUtility;

/**
 * @author Kelli
 */
public class AccountPopulateAllocationDescriptionByList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     *   Retrieves the description of each account segment for all account records in accountList
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
            String accountDescElements = propertiesManager.getProperty("FORM","ACCOUNTDESCELEMENTS","0");
            List accountList = (List) incomingRequest.get("accountList");
            int ielements = Integer.valueOf(accountDescElements).intValue();

            if (ielements > 0) {
                AccountPopulateAllocationDescription populate = new AccountPopulateAllocationDescription();
                Map requestParameters = new HashMap();

                requestParameters.put("organizationId", organizationId);
                requestParameters.put("userId", incomingRequest.get("userId"));
                requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                requestParameters.put("dbsession", incomingRequest.get("dbsession"));

                for (int i = 0; i < accountList.size(); i++) {
                    Account account = (Account) accountList.get(i);

                    requestParameters.put("account", account);
                    account = (Account) populate.executeTask(requestParameters);

                    accountList.set(i, account);
                }
            }

            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String queryString_ac02 = "select description from StdTable as StdTable where StdTable.id.tableType = 'AC02' and StdTable.id.tableKey = ?";
			String queryString_ac04 = "select description from StdTable as StdTable where StdTable.id.tableType = 'AC04' and StdTable.id.tableKey = ?";
			String queryString_ac05 = "select description from StdTable as StdTable where ( StdTable.id.tableType = 'AC05' or StdTable.id.tableType = 'PRIO' ) and StdTable.id.tableKey = ?";
				
			String description="";
            if(accountList != null){
            	for (int i = 0; i < accountList.size(); i++) {
            		Account account = (Account) accountList.get(i);
            		List resultQuery_ac02 = dbs.query(queryString_ac02, new Object[] {account.getFld2()} , new Type[] { Hibernate.STRING}) ;
            		
            		if ((resultQuery_ac02 != null && resultQuery_ac02.size() > 0))
					{
            			description = HiltonUtility.ckNull((String) resultQuery_ac02.get(0).toString());
            			account.setFld2Description(description);
					}     
            		
            		List resultQuery_ac04 = dbs.query(queryString_ac04, new Object[] {account.getFld4()} , new Type[] { Hibernate.STRING}) ;
            		
            		if ((resultQuery_ac04 != null && resultQuery_ac04.size() > 0))
					{
            			description = HiltonUtility.ckNull((String) resultQuery_ac04.get(0).toString());
            			account.setFld4Description(description);
					}
            		
            		List resultQuery_ac05 = dbs.query(queryString_ac05, new Object[] {account.getFld5()} , new Type[] { Hibernate.STRING}) ;
            		
            		if ((resultQuery_ac05 != null && resultQuery_ac05.size() > 0))
					{
            			description = HiltonUtility.ckNull((String) resultQuery_ac05.get(0).toString());
            			account.setFld5Description(description);
					}            		
            	}
            }
            
            result = accountList;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Account Allocation Descriptions could not be retrieved! [" + e.getMessage() + "]", e);
		}

		return result ;
	}
}