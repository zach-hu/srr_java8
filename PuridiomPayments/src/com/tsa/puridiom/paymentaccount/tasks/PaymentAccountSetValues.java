package com.tsa.puridiom.paymentaccount.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PaymentAccount;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class PaymentAccountSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PaymentAccount paymentAccount = (PaymentAccount) incomingRequest.get("paymentAccount");
			if (paymentAccount == null)
			{
				paymentAccount = new PaymentAccount();
			}

			if (incomingRequest.containsKey("PaymentAccount_icPayment"))
			{
				String icPaymentString = (String) incomingRequest.get("PaymentAccount_icPayment");
				if (Utility.isEmpty(icPaymentString))
				{
					icPaymentString = "0";
				}
				BigDecimal icPayment = new BigDecimal ( icPaymentString );
				paymentAccount.setIcPayment(icPayment);
			}
			if (incomingRequest.containsKey("PaymentAccount_invoiceNumber"))
			{
				String invoiceNumber = (String ) incomingRequest.get("PaymentAccount_invoiceNumber");
				paymentAccount.setInvoiceNumber(invoiceNumber);
			}
			if (incomingRequest.containsKey("PaymentAccount_poNumber"))
			{
				String poNumber = (String ) incomingRequest.get("PaymentAccount_poNumber");
				paymentAccount.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("PaymentAccount_releaseNumber"))
			{
				String releaseNumberString = (String) incomingRequest.get("PaymentAccount_releaseNumber");
				if (Utility.isEmpty(releaseNumberString))
				{
					releaseNumberString = "0";
				}
				BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
				paymentAccount.setReleaseNumber(releaseNumber);
			}
			if (incomingRequest.containsKey("PaymentAccount_invoiceDate"))
			{
				String invoiceDateString = (String) incomingRequest.get("PaymentAccount_invoiceDate");
				Date invoiceDate = Dates.getDate(invoiceDateString);
				paymentAccount.setInvoiceDate(invoiceDate);
			}
			//TSR YEAR
			//TSR MONTH
			if (incomingRequest.containsKey("PaymentAccount_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("PaymentAccount_vendorId");
				paymentAccount.setVendorId(vendorId);
			}
			//CAC CODE
			//ACCOUNT CODE
			//COST CENTER
			//UTIL CODE
			//ALLOC AMOUNT
			if (incomingRequest.containsKey("PaymentAccount_invoiceAmount"))
			{
				String invoiceAmountString = (String) incomingRequest.get("PaymentAccount_invoiceAmount");
				if (Utility.isEmpty(invoiceAmountString))
				{
					invoiceAmountString = "0";
				}
				BigDecimal invoiceAmount = new BigDecimal ( invoiceAmountString );
				paymentAccount.setInvoiceAmount(invoiceAmount);
			}
			if (incomingRequest.containsKey("PaymentAccount_checkNumber"))
			{
				String checkNumber = (String ) incomingRequest.get("PaymentAccount_checkNumber");
				paymentAccount.setCheckNumber(checkNumber);
			}
			if (incomingRequest.containsKey("PaymentAccount_checkAmount"))
			{
				String checkAmountString = (String) incomingRequest.get("PaymentAccount_checkAmount");
				if (Utility.isEmpty(checkAmountString))
				{
					checkAmountString = "0";
				}
				BigDecimal checkAmount = new BigDecimal ( checkAmountString );
				paymentAccount.setCheckAmount(checkAmount);
			}
			if (incomingRequest.containsKey("PaymentAccount_checkDate"))
			{
				String checkDateString = (String) incomingRequest.get("PaymentAccount_checkDate");
				Date checkDate = Dates.getDate(checkDateString);
				paymentAccount.setCheckDate(checkDate);
			}
			if (incomingRequest.containsKey("PaymentAccount_cancelDate"))
			{
				String cancelDateString = (String) incomingRequest.get("PaymentAccount_cancelDate");
				if (!HiltonUtility.isEmpty(cancelDateString))
				{
					Date cancelDate = Dates.getDate(cancelDateString);
					paymentAccount.setCancelDate(cancelDate);
				}
			}
			if (incomingRequest.containsKey("PaymentAccount_voucherNumber"))
			{
				String voucherNumber = (String ) incomingRequest.get("PaymentAccount_voucherNumber");
				paymentAccount.setVoucherNumber(voucherNumber);
			}
			if (incomingRequest.containsKey("PaymentAccount_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("PaymentAccount_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				paymentAccount.setIcPoHeader(icPoHeader);
			}
			//TSR FLAG
			//TRANS DATE
			//VOUCHER DESC
			if (incomingRequest.containsKey("PaymentAccount_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("PaymentAccount_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				paymentAccount.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("PaymentAccount_owner"))
			{
				String owner = (String ) incomingRequest.get("PaymentAccount_owner");
				paymentAccount.setOwner(owner);
			}


			result = paymentAccount;
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