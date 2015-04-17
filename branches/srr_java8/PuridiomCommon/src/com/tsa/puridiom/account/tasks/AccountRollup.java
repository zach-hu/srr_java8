package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AccountRollup extends Task {
    
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    List accountList = (List) incomingRequest.get("accountList");
		    Map accountMap = new HashMap();
	
		    if (accountList != null) {
		        for (int i=0; i < accountList.size(); i++) {
		            Account account = (Account) accountList.get(i);
		            String	accountString = account.getAcctString("|");
		            
		            if (accountMap.containsKey(accountString)) {
		                Account	accountRolledUp = (Account) accountMap.get(accountString);
		                
		                accountRolledUp.setAllocAmount(accountRolledUp.getAllocAmount().add(account.getAllocAmount()));
		                accountRolledUp.setAllocQty(accountRolledUp.getAllocQty().add(account.getAllocQty()));
		                
		                accountMap.put(accountString, accountRolledUp);
		            } else {
		                accountMap.put(accountString, account);
		            }
		        }
		    }

		    List rolledUpAccountList = new ArrayList();
		    Iterator accounts = accountMap.keySet().iterator();
		    while (accounts.hasNext()) {
		        String	key = (String) accounts.next();
		        Account account = (Account) accountMap.get(key);
		        rolledUpAccountList.add(account);
		    }
		    
			result = rolledUpAccountList;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}