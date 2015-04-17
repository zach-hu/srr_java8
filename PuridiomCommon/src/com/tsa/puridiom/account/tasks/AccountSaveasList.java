/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff / Kelli
 */
public class AccountSaveasList extends Task
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
		String saveAccountTotals = HiltonUtility.ckNull((String) incomingRequest.get("saveAccountTotals"));
		List poLines = (List)incomingRequest.get("poLines");
		String	userId = (String)incomingRequest.get("userId");

		for (int accountRow = 0; accountRow < accounts.size(); accountRow++)
		{
			Account account = (Account)accounts.get(accountRow);
			if (("PS-ACI").equalsIgnoreCase(userId) && (account.getAllocMethod().equalsIgnoreCase("AH") || account.getAllocMethod().equalsIgnoreCase("AL")))
			{
				BigDecimal  totalAlloc = new BigDecimal(0);
				for (int ix = 0; ix < accounts.size(); ix++)
				{
					Account tempAccount = (Account)accounts.get(ix);
					BigDecimal tempAlloc = tempAccount.getAllocAmount();
					totalAlloc = totalAlloc.add(tempAlloc);
				}
				if (accounts.size() == 1)
				{
					incomingRequest.put("newAllocationPercent", new BigDecimal(100));
				}
				else
				{
					BigDecimal tempPercent = new BigDecimal(0);
					tempPercent = account.getAllocAmount().multiply(new BigDecimal(100)).divide(totalAlloc, 2);
					incomingRequest.put("newAllocationPercent", tempPercent);
				}


				incomingRequest.put("amountAllocation", "Y");
			}
			incomingRequest.put("account", account);
			incomingRequest.put("saveAccountTotals", saveAccountTotals);

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
