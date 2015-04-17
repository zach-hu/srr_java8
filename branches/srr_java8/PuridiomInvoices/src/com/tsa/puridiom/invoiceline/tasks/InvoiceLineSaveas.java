package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvoiceLineSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invoiceLine */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvoiceLine	originalInvoiceLine = (InvoiceLine) incomingRequest.get("invoiceLine");
			InvoiceLine	invoiceLine = new InvoiceLine();

			invoiceLine.setIcIvcHeader(originalInvoiceLine.getIcIvcHeader());
			invoiceLine.setIcIvcLine(originalInvoiceLine.getIcIvcLine());
			invoiceLine.setIcPoHeader(originalInvoiceLine.getIcPoHeader());
			invoiceLine.setIcPoLine(originalInvoiceLine.getIcPoLine());
			invoiceLine.setInvoiceNumber(originalInvoiceLine.getInvoiceNumber());
			invoiceLine.setLineNumber(originalInvoiceLine.getLineNumber());
			invoiceLine.setItemNumber(originalInvoiceLine.getItemNumber());
			invoiceLine.setDescription(originalInvoiceLine.getDescription());
			invoiceLine.setQuantity(originalInvoiceLine.getQuantity());
			invoiceLine.setUmCode(originalInvoiceLine.getUmCode());
			invoiceLine.setUmFactor(originalInvoiceLine.getUmFactor());
			invoiceLine.setUnitPrice(originalInvoiceLine.getUnitPrice());
			invoiceLine.setTaxable(originalInvoiceLine.getTaxable());
			invoiceLine.setTaxPercent(originalInvoiceLine.getTaxPercent());
			invoiceLine.setTaxAmount(originalInvoiceLine.getTaxAmount());
			invoiceLine.setDiscountPercent(originalInvoiceLine.getDiscountPercent());
			invoiceLine.setDiscountAmount(originalInvoiceLine.getDiscountAmount());
			invoiceLine.setLineTotal(originalInvoiceLine.getLineTotal());
			invoiceLine.setStatus(originalInvoiceLine.getStatus());
			invoiceLine.setCommodity(originalInvoiceLine.getCommodity());

			incomingRequest.put("invoiceLine", invoiceLine);

			InvoiceLineAdd addTask = new InvoiceLineAdd();
			invoiceLine = (InvoiceLine) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invoiceLine;
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