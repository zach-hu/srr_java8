/*
 * Created on September 13, 2005
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johann
 */
public class PoLineRetrieveQuantities extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invoiceline-retrieve-quantities.xml");
			
			
			List poLineList = (List) incomingRequest.get("poLineOrderList");
			List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
			int	ii = 0;
	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); ) {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();
	        	Map requestParameters = new HashMap();

	        	BigDecimal orderUnitCost = new BigDecimal(0);
	        	BigDecimal orderUmFactor = new BigDecimal(0);
	        	BigDecimal qtyOrdered = new BigDecimal(0);
	        	BigDecimal QtyPoInvoiced = new BigDecimal(0);
				BigDecimal amountOrdered = new BigDecimal(0);
				BigDecimal qtyReceived = new BigDecimal(0);
				BigDecimal invoiceUnitCost = new BigDecimal(0);
				BigDecimal qtyInvoiced = new BigDecimal(0);
				BigDecimal discountPercent = new BigDecimal(0);
				BigDecimal amountInvoiced = new BigDecimal(0);
				BigDecimal variance = new BigDecimal(0);
				String failsafe = "N";
				String Type = "PO";
				BigDecimal icPoLine = HiltonUtility.ckNull(invoiceLine.getIcPoLine());
				PoLine poLine = (PoLine) requestParameters.get("poLine");
				
				if (icPoLine.compareTo(new BigDecimal(0)) > 0)
				{
					requestParameters.put("userId", incomingRequest.get("userId"));
				    requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
		        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
		        	requestParameters.put("InvoiceLine_icIvcLine", String.valueOf(invoiceLine.getIcIvcLine()));
		        	requestParameters.put("InvoiceLine_icPoLine", String.valueOf(invoiceLine.getIcPoLine()));
		        	requestParameters.put("PoLine_icPoLine", String.valueOf(invoiceLine.getIcPoLine()));
		        	requestParameters.put("PoLine_icLineKey", String.valueOf(invoiceLine.getIcRelPoLine()));
		        	if (invoiceLine.getIcRelPoLine().compareTo(new BigDecimal(0)) == 0)
		        		requestParameters.put("PoLine_icLineKey", String.valueOf(invoiceLine.getIcPoLine()));
		        	requestParameters.put("PoLine_icPoHeader", String.valueOf(invoiceLine.getIcPoHeader()));
		        	requestParameters.put("ReceiptLine_icPoLine", String.valueOf(invoiceLine.getIcPoLine()));
		        	requestParameters.put("invoiceaction", HiltonUtility.ckNull(incomingRequest.get("invoiceaction")));
		        	requestParameters.put("InvoiceLine_status", invoiceLine.getStatus());
		        	requestParameters.put("Type",Type);
		        	
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
					QtyPoInvoiced = HiltonUtility.ckNull((BigDecimal) requestParameters.get("bdQtyPoInvoiced"));
					poLine = (PoLine) requestParameters.get("poLine");
					
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
				//poLine.
				BigDecimal poInvoiced = qtyInvoiced;
				poLine.setQtyInvoiced(poInvoiced);
				
				poLineList.set(ii, poLine);
				ii++;
	        }

	        result = poLineList;
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
