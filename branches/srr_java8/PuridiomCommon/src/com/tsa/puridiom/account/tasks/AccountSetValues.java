package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.common.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AccountSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AccountPK comp_id = new AccountPK();
			Account account = (Account) incomingRequest.get("account");
			if (account == null)
			{
				account = new Account();
			}

			if (incomingRequest.containsKey("Account_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("Account_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("Account_icLine"))
			{
				String icLineString = (String) incomingRequest.get("Account_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("Account_sequence"))
			{
				String sequenceString = (String) incomingRequest.get("Account_sequence");
				if (Utility.isEmpty(sequenceString))
				{
					sequenceString = "0";
				}
				BigDecimal sequence = new BigDecimal ( sequenceString );
				comp_id.setSequence(sequence);
			}
			if (incomingRequest.containsKey("Account_accountType"))
			{
				String accountType = (String ) incomingRequest.get("Account_accountType");
				account.setAccountType(accountType);
			}
			if (incomingRequest.containsKey("Account_fld1"))
			{
				String fld1 = (String ) incomingRequest.get("Account_fld1");
				String udf1 = (String ) incomingRequest.get("userNameUdf1");
				if(HiltonUtility.isEmpty(fld1) && !HiltonUtility.isEmpty(udf1))
					fld1 = udf1;
				account.setFld1(fld1);
			}
			if (incomingRequest.containsKey("Account_fld2"))
			{
				String fld2 = (String ) incomingRequest.get("Account_fld2");
				String udf2 = (String ) incomingRequest.get("userNameUdf2");
				if(HiltonUtility.isEmpty(fld2) && !HiltonUtility.isEmpty(udf2))
					fld2 = udf2;
				account.setFld2(fld2);
			}
			if (incomingRequest.containsKey("Account_fld3"))
			{
				String fld3 = (String ) incomingRequest.get("Account_fld3");
				String udf3 = (String ) incomingRequest.get("userNameUdf3");
				if(HiltonUtility.isEmpty(fld3) && !HiltonUtility.isEmpty(udf3))
					fld3 = udf3;
				account.setFld3(fld3);
			}
			if (incomingRequest.containsKey("Account_fld4"))
			{
				String fld4 = (String ) incomingRequest.get("Account_fld4");
				String udf4 = (String ) incomingRequest.get("userNameUdf4");
				if(HiltonUtility.isEmpty(fld4) && !HiltonUtility.isEmpty(udf4))
					fld4 = udf4;
				account.setFld4(fld4);
			}
			if (incomingRequest.containsKey("Account_fld5"))
			{
				String fld5 = (String ) incomingRequest.get("Account_fld5");
				String udf5 = (String ) incomingRequest.get("userNameUdf5");
				if(HiltonUtility.isEmpty(fld5) && !HiltonUtility.isEmpty(udf5))
					fld5 = udf5;
				account.setFld5(fld5);
			}
			if (incomingRequest.containsKey("Account_fld6"))
			{
				String fld6 = (String ) incomingRequest.get("Account_fld6");
				String udf6 = (String ) incomingRequest.get("userNameUdf6");
				if(HiltonUtility.isEmpty(fld6) && !HiltonUtility.isEmpty(udf6))
					fld6 = udf6;
				account.setFld6(fld6);
			}
			if (incomingRequest.containsKey("Account_fld7"))
			{
				String fld7 = (String ) incomingRequest.get("Account_fld7");
				String udf7 = (String ) incomingRequest.get("userNameUdf7");
				if(HiltonUtility.isEmpty(fld7) && !HiltonUtility.isEmpty(udf7))
					fld7 = udf7;
				account.setFld7(fld7);
			}
			if (incomingRequest.containsKey("Account_fld8"))
			{
				String fld8 = (String ) incomingRequest.get("Account_fld8");
				account.setFld8(fld8);
			}
			if (incomingRequest.containsKey("Account_fld9"))
			{
				String fld9 = (String ) incomingRequest.get("Account_fld9");
				account.setFld9(fld9);
			}
			if (incomingRequest.containsKey("Account_fld10"))
			{
				String fld10 = (String ) incomingRequest.get("Account_fld10");
				account.setFld10(fld10);
			}
			if (incomingRequest.containsKey("Account_fld11"))
			{
				String fld11 = (String ) incomingRequest.get("Account_fld11");
				account.setFld11(fld11);
			}
			if (incomingRequest.containsKey("Account_fld12"))
			{
				String fld12 = (String ) incomingRequest.get("Account_fld12");
				account.setFld12(fld12);
			}
			if (incomingRequest.containsKey("Account_fld13"))
			{
				String fld13 = (String ) incomingRequest.get("Account_fld13");
				account.setFld13(fld13);
			}
			if (incomingRequest.containsKey("Account_fld14"))
			{
				String fld14 = (String ) incomingRequest.get("Account_fld14");
				account.setFld14(fld14);
			}
			if (incomingRequest.containsKey("Account_fld15"))
			{
				String fld15 = (String ) incomingRequest.get("Account_fld15");
				account.setFld15(fld15);
			}
			if (incomingRequest.containsKey("Account_allocPercent"))
			{
				String allocPercentString = (String) incomingRequest.get("Account_allocPercent");
				if (Utility.isEmpty(allocPercentString))
				{
					allocPercentString = "0";
				}
				BigDecimal allocPercent = new BigDecimal ( allocPercentString );
				account.setAllocPercent(allocPercent);
			}
			if (incomingRequest.containsKey("Account_allocAmount"))
			{
				String allocAmountString = (String) incomingRequest.get("Account_allocAmount");
				if (Utility.isEmpty(allocAmountString))
				{
					allocAmountString = "0";
				}
				BigDecimal allocAmount = new BigDecimal ( allocAmountString );
				account.setAllocAmount(allocAmount);
			}
			if (incomingRequest.containsKey("Account_accountTitle"))
			{
				String accountTitle = (String ) incomingRequest.get("Account_accountTitle");
				account.setAccountTitle(accountTitle);
			}
			if (incomingRequest.containsKey("Account_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Account_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				account.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Account_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Account_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				account.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Account_status"))
			{
				String status = (String ) incomingRequest.get("Account_status");
				account.setStatus(status);
			}
			if (incomingRequest.containsKey("Account_owner"))
			{
				String owner = (String ) incomingRequest.get("Account_owner");
				account.setOwner(owner);
			}
			if (incomingRequest.containsKey("Account_allocMethod"))
			{
				String allocMethod = (String ) incomingRequest.get("Account_allocMethod");
				account.setAllocMethod(allocMethod);
			}
			if (incomingRequest.containsKey("Account_allocQty"))
			{
				String allocQtyString = (String) incomingRequest.get("Account_allocQty");
				if (Utility.isEmpty(allocQtyString))
				{
					allocQtyString = "0";
				}
				BigDecimal allocQty = new BigDecimal ( allocQtyString );
				account.setAllocQty(allocQty);
			}
			if (incomingRequest.containsKey("Account_recQty"))
			{
				String recQtyString = (String) incomingRequest.get("Account_recQty");
				if (Utility.isEmpty(recQtyString))
				{
					recQtyString = "0";
				}
				BigDecimal recQty = new BigDecimal ( recQtyString );
				account.setRecQty(recQty);
			}
			if (incomingRequest.containsKey("Account_icLastRec"))
			{
				String icLastRecString = (String) incomingRequest.get("Account_icLastRec");
				if (Utility.isEmpty(icLastRecString))
				{
					icLastRecString = "0";
				}
				BigDecimal icLastRec = new BigDecimal ( icLastRecString );
				account.setIcLastRec(icLastRec);
			}
			
			if (incomingRequest.containsKey("Account_source"))
			{
				String source = (String) incomingRequest.get("Account_source");
				account.setSource(source);
			}
			
			if (incomingRequest.containsKey("Account_amountLine"))
			{
				String amountLineString = (String) incomingRequest.get("Account_amountLine");
				if (Utility.isEmpty(amountLineString))
				{
					amountLineString = "0";
				}
				BigDecimal amountLine = new BigDecimal ( amountLineString );
				account.setAmountLine(amountLine);
			}
			if (incomingRequest.containsKey("Account_budgetFlag"))
			{
				String budgetFlag = (String) incomingRequest.get("Account_budgetFlag");
				account.setBudgetFlag(budgetFlag);
			}

			account.setComp_id(comp_id);

			result = account;
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