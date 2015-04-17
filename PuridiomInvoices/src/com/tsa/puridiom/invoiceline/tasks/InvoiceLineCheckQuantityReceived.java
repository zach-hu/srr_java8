package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvoiceLineCheckQuantityReceived extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
			String poSubType = poHeader.getSubType();
			BigDecimal invoicedQty = (BigDecimal)incomingRequest.get("invoiceQuantity");
			if(invoicedQty == null)
			{
				invoicedQty = new BigDecimal(0);
			}
			BigDecimal qtyOrdered = poLine.getQuantity();
			BigDecimal qtyAccepted = receiptLine.getQtyAccepted();

			BigDecimal qtyToInvoice = invoicedQty.add(qtyAccepted);
			if(qtyToInvoice.compareTo(qtyOrdered) > 0)
			{
				if(poSubType.equalsIgnoreCase("00"))
				{
					BigDecimal qtyToSubtract = qtyToInvoice.subtract(qtyOrdered);
					BigDecimal newQtyToInvoice = qtyAccepted.subtract(qtyToSubtract);
					incomingRequest.put("InvoiceLine_quantity", newQtyToInvoice.toString());
					System.out.print("YEAH");
				}
				else
				{
					BigDecimal orderTolerance = qtyOrdered.multiply(new BigDecimal(1.1).round(new MathContext(2)));
					if(qtyToInvoice.compareTo(orderTolerance) > 0)
					{
						BigDecimal qtyToSubtract = qtyToInvoice.subtract(orderTolerance);
						BigDecimal newQtyToInvoice = qtyAccepted.subtract(qtyToSubtract);
						incomingRequest.put("InvoiceLine_quantity", newQtyToInvoice.toString());
					}
					else
					{
						incomingRequest.put("InvoiceLine_quantity", qtyAccepted.toString());
					}
					System.out.print("YEAH");
				}
			}
			else
			{
				incomingRequest.put("InvoiceLine_quantity", qtyAccepted.toString());
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
