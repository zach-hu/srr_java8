package com.tsa.puridiom.capitalclearingaccount.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.CapitalClearingAccount;

public class CapitalClearingAccountSetValuesPK
{
	public void setValues(Map incomingRequest, CapitalClearingAccount capitalClearingAccount ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("CapitalClearingAccount_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			capitalClearingAccount.setIcHeader(icHeader);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}