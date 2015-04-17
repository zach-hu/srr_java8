/*
 * Created on Nov 4, 2005
 */
package com.tsa.puridiom.invoice.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Kelli
 */
public class InvoiceValidationRulesData extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;

		try
		{
			Map incomingRequest = (Map) object;
			InvoiceHeader header = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			List lineList = (List) incomingRequest.get("lineitems");

			if (header == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Invoice Header was not found!");
			}

			incomingRequest.put("accounts", header.getAccountList());
			incomingRequest.put("accountLineList", this.setUpLineAccounts(incomingRequest));
			incomingRequest.put("header", header);
			incomingRequest.put("lineItemsForPayment", this.getLineItemsForPayment(lineList));
			incomingRequest.put("lineItemsInvoiced", this.getLineItemsInvoiced(lineList));			

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.debug(this, "Error on the tasks InvoiceValidationRulesData: " + e.getMessage());
			e.printStackTrace();			
			throw e;
		}
		return ret;
	}

	/**
	 * @param lineItems
	 * @return
	 */
	private List getLineItemsForPayment(List lineItems)
	{
		List linesForPayment = new ArrayList();

		if ((lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				InvoiceLine invoiceLine = (InvoiceLine) iterator.next();
				BigDecimal qtyInvoiced = invoiceLine.getQtyInvoiced();
				BigDecimal qtyOrdered = invoiceLine.getQtyOrdered();

				if (qtyInvoiced.compareTo(qtyOrdered) < 0)
				{
					linesForPayment.add(invoiceLine);
				}
			}
		}

		return linesForPayment;
	}

	/**
	 * @param incomingRequest
	 */
	private List setUpLineAccounts(Map incomingRequest)
	{
		List lineAccounts = new ArrayList();
		List lineList = (List) incomingRequest.get("lineitems");

		for (int i = 0; i < lineList.size(); i++)
		{
			InvoiceLine line = (InvoiceLine) lineList.get(i);
			List acctLineList = line.getAccountList();
			if (acctLineList == null)
			{
				acctLineList = new ArrayList();
			}
			lineAccounts.add(acctLineList);
		}

		return lineAccounts;
	}
	
	/**
	 * @param lineItemsInvoiced
	 * @return
	 */
	private List getLineItemsInvoiced(List lineItems)
	{
		List linesQtyGreaterZero = new ArrayList();

		if ((lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				InvoiceLine invoiceLine = (InvoiceLine) iterator.next();
				//BigDecimal qtyInvoiced = invoiceLine.getQtyInvoiced();				
				
				if ((invoiceLine.getLineTotal().compareTo(new BigDecimal(0)) != 0) )
				{
					linesQtyGreaterZero.add(invoiceLine);
				}
			}
		}

		return linesQtyGreaterZero;
	}
}
