package com.tsa.puridiom.payment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class PaymentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PaymentPK comp_id = new PaymentPK();
			Payment payment = (Payment) incomingRequest.get("payment");
			if (payment == null)
			{
				payment = new Payment();
			}

			if (incomingRequest.containsKey("Payment_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("Payment_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				comp_id.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("Payment_sequence"))
			{
				String sequenceString = (String) incomingRequest.get("Payment_sequence");
				if (Utility.isEmpty(sequenceString))
				{
					sequenceString = "0";
				}
				BigDecimal sequence = new BigDecimal ( sequenceString );
				comp_id.setSequence(sequence);
			}
			if (incomingRequest.containsKey("Payment_icPoLine"))
			{
				String icPoLineString = (String) incomingRequest.get("Payment_icPoLine");
				if (Utility.isEmpty(icPoLineString))
				{
					icPoLineString = "0";
				}
				BigDecimal icPoLine = new BigDecimal ( icPoLineString );
				payment.setIcPoLine(icPoLine);
			}
			if (incomingRequest.containsKey("Payment_poNumber"))
			{
				String poNumber = (String ) incomingRequest.get("Payment_poNumber");
				payment.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("Payment_revisionNumber"))
			{
				String revisionNumberString = (String) incomingRequest.get("Payment_revisionNumber");
				if (Utility.isEmpty(revisionNumberString))
				{
					revisionNumberString = "0";
				}
				BigDecimal revisionNumber = new BigDecimal ( revisionNumberString );
				payment.setRevisionNumber(revisionNumber);
			}
			if (incomingRequest.containsKey("Payment_releaseNumber"))
			{
				String releaseNumberString = (String) incomingRequest.get("Payment_releaseNumber");
				if (Utility.isEmpty(releaseNumberString))
				{
					releaseNumberString = "0";
				}
				BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
				payment.setReleaseNumber(releaseNumber);
			}
			if (incomingRequest.containsKey("Payment_paymentDate"))
			{
				String paymentDateString = (String) incomingRequest.get("Payment_paymentDate");
				Date paymentDate = Dates.getDate(paymentDateString);
				payment.setPaymentDate(paymentDate);
			}
			if (incomingRequest.containsKey("Payment_invoiceNumber"))
			{
				String invoiceNumber = (String ) incomingRequest.get("Payment_invoiceNumber");
				payment.setInvoiceNumber(invoiceNumber);
			}
			if (incomingRequest.containsKey("Payment_paymentAmount"))
			{
				String paymentAmountString = (String) incomingRequest.get("Payment_paymentAmount");
				if (Utility.isEmpty(paymentAmountString))
				{
					paymentAmountString = "0";
				}
				BigDecimal paymentAmount = new BigDecimal ( paymentAmountString );
				payment.setPaymentAmount(paymentAmount);
			}
			if (incomingRequest.containsKey("Payment_checkNumber"))
			{
				String checkNumber = (String ) incomingRequest.get("Payment_checkNumber");
				payment.setCheckNumber(checkNumber);
			}
			if (incomingRequest.containsKey("Payment_voucherNumber"))
			{
				String voucherNumber = (String ) incomingRequest.get("Payment_voucherNumber");
				payment.setVoucherNumber(voucherNumber);
			}
			payment.setComp_id(comp_id);

			result = payment;
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