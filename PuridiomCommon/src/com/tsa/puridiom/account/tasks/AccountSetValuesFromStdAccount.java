package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AccountPK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class AccountSetValuesFromStdAccount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Account stdAccount = (Account) incomingRequest.get("stdAccount");
			AccountPK stdAccountPK = stdAccount.getComp_id();
			String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			String allocMethod = stdAccount.getAllocMethod();
			if (Utility.isEmpty(allocMethod))
			{
				allocMethod = Utility.ckNull((String) incomingRequest.get("Account_allocMethod"));
			}
			if (Utility.isEmpty(allocMethod))
			{
			    allocMethod = "P";
			}

			BigDecimal bdAllocPercent = stdAccount.getAllocPercent();
			BigDecimal bdAllocQty = stdAccount.getAllocQty();
			if (bdAllocPercent == null)
			{
				bdAllocPercent = new BigDecimal(0);
			}
			if (bdAllocQty == null)
			{
				bdAllocQty = new BigDecimal(0);
			}

			PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
			String dollarDecimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");

		    BigDecimal allocAmount =  new BigDecimal(0.00);
		    BigDecimal perc = new BigDecimal(0.00);
			BigDecimal bdAmountToAllocate = new BigDecimal(0.00);

			String amountToAllocate = (String) incomingRequest.get("amountToAllocate");
			if (!Utility.isEmpty(amountToAllocate))
			{
				bdAmountToAllocate = new BigDecimal(amountToAllocate).setScale(Integer.valueOf(dollarDecimals).intValue(), BigDecimal.ROUND_HALF_UP);
			}

			if (allocMethod.substring(0,1).equalsIgnoreCase("P"))
            {
                perc = stdAccount.getAllocPercent();
                perc = perc.multiply(new BigDecimal(.01));

                allocAmount = bdAmountToAllocate.multiply(perc).setScale(Integer.valueOf(dollarDecimals).intValue(), BigDecimal.ROUND_HALF_UP);
            }
            else
            {
            	allocAmount = stdAccount.getAllocAmount().setScale(Integer.valueOf(dollarDecimals).intValue(), BigDecimal.ROUND_HALF_UP);;
            }

			incomingRequest.put("Account_icHeader", incomingRequest.get("Account_icHeader"));
			incomingRequest.put("Account_icLine", incomingRequest.get("Account_icLine"));
			incomingRequest.put("Account_sequence", String.valueOf(stdAccountPK.getSequence()));
			incomingRequest.put("Account_accountType", incomingRequest.get("Account_accountType"));

			String Account_fld1 = HiltonUtility.ckNull((String)incomingRequest.get("Account_fld1"));
			String Account_fld2 = HiltonUtility.ckNull((String)incomingRequest.get("Account_fld2"));
			String Account_fld3 = HiltonUtility.ckNull((String)incomingRequest.get("Account_fld3"));
			String Account_fld4 = HiltonUtility.ckNull((String)incomingRequest.get("Account_fld4"));
			String Account_fld5 = HiltonUtility.ckNull((String)incomingRequest.get("Account_fld5"));
			String Account_fld6 = HiltonUtility.ckNull((String)incomingRequest.get("Account_fld6"));
			String Account_fld7 = HiltonUtility.ckNull((String)incomingRequest.get("Account_fld7"));

			if (HiltonUtility.isEmpty(Account_fld1)) {
				incomingRequest.put("Account_fld1", stdAccount.getFld1());
			}
			if (HiltonUtility.isEmpty(Account_fld2)) {
				incomingRequest.put("Account_fld2", stdAccount.getFld2());
			}
			if (HiltonUtility.isEmpty(Account_fld3)) {
				incomingRequest.put("Account_fld3", stdAccount.getFld3());
			}
			if (HiltonUtility.isEmpty(Account_fld4)) {
				incomingRequest.put("Account_fld4", stdAccount.getFld4());
			}
			if (HiltonUtility.isEmpty(Account_fld5)) {
				incomingRequest.put("Account_fld5", stdAccount.getFld5());
			}
			if (HiltonUtility.isEmpty(Account_fld6)) {
				incomingRequest.put("Account_fld6", stdAccount.getFld6());
			}
			if (HiltonUtility.isEmpty(Account_fld7)) {
				incomingRequest.put("Account_fld7", stdAccount.getFld7());
			}
			incomingRequest.put("Account_fld8", stdAccount.getFld8());
			incomingRequest.put("Account_fld9", stdAccount.getFld9());
			incomingRequest.put("Account_fld10", stdAccount.getFld10());
			incomingRequest.put("Account_fld11", stdAccount.getFld11());
			incomingRequest.put("Account_fld12", stdAccount.getFld12());
			incomingRequest.put("Account_fld13", stdAccount.getFld13());
			incomingRequest.put("Account_fld14", stdAccount.getFld14());
			incomingRequest.put("Account_fld15", stdAccount.getFld15());
			incomingRequest.put("Account_allocPercent", bdAllocPercent.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			incomingRequest.put("Account_allocAmount", allocAmount.toString());
			incomingRequest.put("Account_accountTitle", stdAccount.getAccountTitle());
			incomingRequest.put("Account_dateEntered", Dates.today("", userTimeZone));
			incomingRequest.put("Account_dateExpires", Dates.today("", userTimeZone));
			incomingRequest.put("Account_status", "");
			incomingRequest.put("Account_owner", userId);
			incomingRequest.put("Account_allocMethod", allocMethod);
			incomingRequest.put("Account_allocQty", bdAllocQty.toString());
			incomingRequest.put("Account_recQty", "0");
			incomingRequest.put("Account_icLastRec", "0");

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