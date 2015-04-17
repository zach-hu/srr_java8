package com.tsa.puridiom.capitalclearingaccount.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class CapitalClearingAccountSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CapitalClearingAccount capitalClearingAccount = (CapitalClearingAccount) incomingRequest.get("capitalClearingAccount");
			if (capitalClearingAccount == null)
			{
				capitalClearingAccount = new CapitalClearingAccount();
			}

			if (incomingRequest.containsKey("CapitalClearingAccount_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("CapitalClearingAccount_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				capitalClearingAccount.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("CapitalClearingAccount_entity"))
			{
				String entity = (String) incomingRequest.get("CapitalClearingAccount_entity");
				capitalClearingAccount.setEntity(entity);
			}
			if (incomingRequest.containsKey("CapitalClearingAccount_department"))
			{
				String department = (String) incomingRequest.get("CapitalClearingAccount_department");
				capitalClearingAccount.setEntity(department);
			}
			if (incomingRequest.containsKey("CapitalClearingAccount_account"))
			{
				String account = (String) incomingRequest.get("CapitalClearingAccount_account");
				capitalClearingAccount.setEntity(account);
			}

			result = capitalClearingAccount;
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