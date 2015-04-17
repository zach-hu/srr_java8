package com.tsa.puridiom.payment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class PaymentSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain payment */
			PaymentPK comp_id = new PaymentPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Payment	originalPayment = (Payment) incomingRequest.get("payment");
			Payment	payment = new Payment();

			comp_id.setIcPoHeader(originalPayment.getComp_id().getIcPoHeader());
			comp_id.setSequence(originalPayment.getComp_id().getSequence());
			payment.setIcPoLine(originalPayment.getIcPoLine());
			payment.setPoNumber(originalPayment.getPoNumber());
			payment.setRevisionNumber(originalPayment.getRevisionNumber());
			payment.setReleaseNumber(originalPayment.getReleaseNumber());
			payment.setPaymentDate(originalPayment.getPaymentDate());
			payment.setInvoiceNumber(originalPayment.getInvoiceNumber());
			payment.setPaymentAmount(originalPayment.getPaymentAmount());
			payment.setCheckNumber(originalPayment.getCheckNumber());
			payment.setVoucherNumber(originalPayment.getVoucherNumber());
			payment.setComp_id(comp_id);

			incomingRequest.put("payment", payment);

			PaymentAdd addTask = new PaymentAdd();
			payment = (Payment) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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