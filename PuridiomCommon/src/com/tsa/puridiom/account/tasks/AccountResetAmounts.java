/**
 * Created on Apr 12, 2004
 * @author renzo
 * com.tsa.puridiom.account.tasks.AccountResetAmounts.java
 *
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AccountResetAmounts extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			List accounts = (List)incomingRequest.get("accounts");
			for (int i= 0; i < accounts.size(); i++)
			{
				Account account = (Account)accounts.get(i);
				account.setAllocAmount(new BigDecimal(0));
				accounts.set(i, account);
			}
			ret = accounts;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

}
