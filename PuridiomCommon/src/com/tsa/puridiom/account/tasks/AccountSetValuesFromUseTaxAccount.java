package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.List;
import java.util.Map;

public class AccountSetValuesFromUseTaxAccount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List useTaxAccountList = (List) incomingRequest.get("useTaxAccountList");
			if (useTaxAccountList != null && (!useTaxAccountList.isEmpty()))
			{
				Account useTaxAccount = (Account) useTaxAccountList.get(0);
				String userId = (String) incomingRequest.get("userId");
                String userTimeZone = (String) incomingRequest.get("userTimeZone");

				incomingRequest.put("Account_icHeader", incomingRequest.get("InvoiceHeader_icIvcHeader"));
				incomingRequest.put("Account_icLine", "4");
				incomingRequest.put("Account_sequence", "1");
				incomingRequest.put("Account_accountType", "IVU");

				incomingRequest.put("Account_fld1", useTaxAccount.getFld1());
				incomingRequest.put("Account_fld2", useTaxAccount.getFld2());
				incomingRequest.put("Account_fld3", useTaxAccount.getFld3());
				incomingRequest.put("Account_fld4", useTaxAccount.getFld4());
				incomingRequest.put("Account_fld5", useTaxAccount.getFld5());
				incomingRequest.put("Account_fld6", useTaxAccount.getFld6());
				incomingRequest.put("Account_fld7", useTaxAccount.getFld7());
				incomingRequest.put("Account_fld8", useTaxAccount.getFld8());
				incomingRequest.put("Account_fld9", useTaxAccount.getFld9());
				incomingRequest.put("Account_fld10", useTaxAccount.getFld10());
				incomingRequest.put("Account_fld11", useTaxAccount.getFld11());
				incomingRequest.put("Account_fld12", useTaxAccount.getFld12());
				incomingRequest.put("Account_fld13", useTaxAccount.getFld13());
				incomingRequest.put("Account_fld14", useTaxAccount.getFld14());
				incomingRequest.put("Account_fld15", useTaxAccount.getFld15());
				incomingRequest.put("Account_allocPercent", "100");
				incomingRequest.put("Account_allocAmount", "0");
				incomingRequest.put("Account_accountTitle", useTaxAccount.getAccountTitle());
				incomingRequest.put("Account_dateEntered", Dates.today("", userTimeZone));
				incomingRequest.put("Account_dateExpires", Dates.today("", userTimeZone));
				incomingRequest.put("Account_status", "");
				incomingRequest.put("Account_owner", userId);
				incomingRequest.put("Account_allocMethod", "P");
				incomingRequest.put("Account_allocQty", "0");
				incomingRequest.put("Account_recQty", "0");
				incomingRequest.put("Account_icLastRec", "0");
			}

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