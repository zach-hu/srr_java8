/*
 * Created on September 13, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author kathleen
 */
public class InvoiceLineRetrieveQuantities extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invoiceline-retrieve-quantities.xml");

			List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
			int	ii = 0;
	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); ) {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();
	        	Map requestParameters = new HashMap();

	        	BigDecimal orderUnitCost = new BigDecimal(0);
	        	BigDecimal orderUmFactor = new BigDecimal(0);
	        	BigDecimal qtyOrdered = new BigDecimal(0);
				BigDecimal amountOrdered = new BigDecimal(0);
				BigDecimal qtyReceived = new BigDecimal(0);
				BigDecimal invoiceUnitCost = new BigDecimal(0);
				BigDecimal qtyInvoiced = new BigDecimal(0);
				BigDecimal discountPercent = new BigDecimal(0);
				BigDecimal amountInvoiced = new BigDecimal(0);
				BigDecimal variance = new BigDecimal(0);
				String failsafe = "N";

				BigDecimal icPoLine = HiltonUtility.ckNull(invoiceLine.getIcPoLine());
				if (icPoLine.compareTo(new BigDecimal(0)) > 0)
				{
					requestParameters.put("userId", incomingRequest.get("userId"));
				 requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
		        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
		        	requestParameters.put("InvoiceLine_icIvcLine", String.valueOf(invoiceLine.getIcIvcLine()));
		        	requestParameters.put("InvoiceLine_icPoLine", String.valueOf(invoiceLine.getIcPoLine()));
		        	requestParameters.put("InvoiceLine_status" , String.valueOf(invoiceLine.getStatus()));
		        	requestParameters.put("PoLine_icPoLine", String.valueOf(invoiceLine.getIcPoLine()));
		        	requestParameters.put("PoLine_icLineKey", String.valueOf(invoiceLine.getIcRelPoLine()));
		        	requestParameters.put("PoLine_icPoHeader", String.valueOf(invoiceLine.getIcPoHeader()));
		        	requestParameters.put("ReceiptLine_icPoLine", String.valueOf(invoiceLine.getIcPoLine()));
		        	requestParameters.put("invoiceaction", HiltonUtility.ckNull(incomingRequest.get("invoiceaction")));
					process.executeProcess(requestParameters);

					this.setStatus(process.getStatus()) ;
					if (process.getStatus() == Status.FAILED) {
						break ;
					}
					orderUnitCost = HiltonUtility.ckNull((BigDecimal) requestParameters.get("orderUnitPrice"));
					orderUmFactor = HiltonUtility.ckNull((BigDecimal) requestParameters.get("orderUmFactor"));
					qtyOrdered = HiltonUtility.ckNull((BigDecimal) requestParameters.get("qtyOrdered"));
					amountOrdered = HiltonUtility.ckNull((BigDecimal) requestParameters.get("amountOrdered"));
					qtyReceived = HiltonUtility.ckNull((BigDecimal) requestParameters.get("qtyReceived"));
					invoiceUnitCost = HiltonUtility.ckNull((BigDecimal) requestParameters.get("invoiceUnitPrice"));
					qtyInvoiced = HiltonUtility.ckNull((BigDecimal) requestParameters.get("qtyInvoiced"));
					amountInvoiced = HiltonUtility.ckNull((BigDecimal) requestParameters.get("amountInvoiced"));
					discountPercent = HiltonUtility.ckNull((BigDecimal) requestParameters.get("discountPercent"));

					Commodity commodity = (Commodity) requestParameters.get("commodity");
					if (commodity != null)
					{
						failsafe = commodity.getVchFailsafe();
						variance = commodity.getVchVariance();

						if (HiltonUtility.isEmpty(failsafe))
						{
							failsafe = "N";
						}
						if (variance == null)
						{
							variance = new BigDecimal(0);
						}
					}
				}

				invoiceLine.setOrderUnitCost(orderUnitCost);
				invoiceLine.setOrderUmFactor(orderUmFactor);
				invoiceLine.setQtyOrdered(qtyOrdered);
				invoiceLine.setAmountOrdered(amountOrdered);
				invoiceLine.setQtyReceived(qtyReceived);
				invoiceLine.setInvoiceUnitCost(invoiceUnitCost);
				invoiceLine.setQtyInvoiced(qtyInvoiced);
				invoiceLine.setAmountInvoiced(amountInvoiced);
				invoiceLine.setDiscountPercent(discountPercent);
				invoiceLine.setVchFailsafe(failsafe);
				invoiceLine.setVchVariance(variance);

				invoiceLineList.set(ii, invoiceLine);
				ii++;
	        }

	        result = invoiceLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            throw e;
		}

		return result;
	}

}
