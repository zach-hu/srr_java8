/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.disbheader.tasks;

import com.tsa.puridiom.account.tasks.AccountSaveas;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

/**
 * @author Jeff / Kelli
 */
public class DisbHeaderAccountSaveas extends Task
{
	
	/**
	 * executeTask
	 * <p>This method takes a list of accounts coming from incoming request(accountList)</p>
	 * <p> and calls AccountSaveas for each one of them.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List accounts = (List)incomingRequest.get("accountList");
		DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");

		for (int accountRow = 0; accountRow < accounts.size(); accountRow++)
		{
			Account account = (Account)accounts.get(accountRow);

			incomingRequest.put("account", account);
			incomingRequest.put("newAccount_icHeader", disbHeader.getIcDsbHeader().toString());
			incomingRequest.put("newAccount_icLine", "0");
			incomingRequest.put("newAccount_accountType", "DBH");
			
			AccountSaveas saveas = new AccountSaveas();
			saveas.executeTask(incomingRequest);
			
			if (saveas.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("Account save as failed.");
			}
			
			accounts.set(accountRow, account);
			this.setStatus(Status.SUCCEEDED);
		}
		if(accounts.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return accounts;
	}

}
