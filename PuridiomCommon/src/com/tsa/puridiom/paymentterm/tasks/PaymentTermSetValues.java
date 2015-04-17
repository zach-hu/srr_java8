package com.tsa.puridiom.paymentterm.tasks;

import com.tsa.puridiom.entity.PaymentTerm;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class PaymentTermSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PaymentTerm paymentTerm = (PaymentTerm) incomingRequest.get("paymentTerm");
			if (paymentTerm == null)
			{
				paymentTerm = new PaymentTerm();
			}

			if (incomingRequest.containsKey("PaymentTerm_termsCode"))
			{
				String termsCode = (String ) incomingRequest.get("PaymentTerm_termsCode");
				paymentTerm.setTermsCode(termsCode);
			}
			if (incomingRequest.containsKey("PaymentTerm_termDescription"))
			{
				String termDescription = (String ) incomingRequest.get("PaymentTerm_termDescription");
				paymentTerm.setTermDescription(termDescription);
			}
			if (incomingRequest.containsKey("PaymentTerm_termTypeFlag"))
			{
				String termTypeFlagString = (String) incomingRequest.get("PaymentTerm_termTypeFlag");
				if (Utility.isEmpty(termTypeFlagString))
				{
					termTypeFlagString = "0";
				}
				BigDecimal termTypeFlag = new BigDecimal ( termTypeFlagString );
				paymentTerm.setTermTypeFlag(termTypeFlag);
			}
			if (incomingRequest.containsKey("PaymentTerm_termDays"))
			{
				String termDaysString = (String) incomingRequest.get("PaymentTerm_termDays");
				if (Utility.isEmpty(termDaysString))
				{
					termDaysString = "0";
				}
				BigDecimal termDays = new BigDecimal ( termDaysString );
				paymentTerm.setTermDays(termDays);
			}
			if (incomingRequest.containsKey("PaymentTerm_discount"))
			{
				String discountString = (String) incomingRequest.get("PaymentTerm_discount");
				if (Utility.isEmpty(discountString))
				{
					discountString = "0";
				}
				BigDecimal discount = new BigDecimal ( discountString );
				paymentTerm.setDiscount(discount);
			}
			if (incomingRequest.containsKey("PaymentTerm_discountDays"))
			{
				String discountDaysString = (String) incomingRequest.get("PaymentTerm_discountDays");
				if (Utility.isEmpty(discountDaysString))
				{
					discountDaysString = "0";
				}
				BigDecimal discountDays = new BigDecimal ( discountDaysString );
				paymentTerm.setDiscountDays(discountDays);
			}
			if (incomingRequest.containsKey("PaymentTerm_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("PaymentTerm_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				paymentTerm.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("PaymentTerm_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("PaymentTerm_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				paymentTerm.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("PaymentTerm_owner"))
			{
				String owner = (String ) incomingRequest.get("PaymentTerm_owner");
				paymentTerm.setOwner(owner);
			}
			if (incomingRequest.containsKey("PaymentTerm_status"))
			{
				String status = (String ) incomingRequest.get("PaymentTerm_status");
				paymentTerm.setStatus(status);
			}
			if (incomingRequest.containsKey("PaymentTerm_calcMethod"))
			{
				String calcMethod = (String ) incomingRequest.get("PaymentTerm_calcMethod");
				paymentTerm.setCalcMethod(calcMethod);
			}

			result = paymentTerm;
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