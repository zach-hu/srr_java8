/**
 *
 * Created on August 24, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoiceline.tasks.InvoiceLineFromOrderSetup.java
 *
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class InvoiceLineFromOrderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
	    Object ret = null;
	    try
	    {
			Map incomingRequest = (Map)object;
			String userId = (String)incomingRequest.get("userId");
			InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
			if(invoiceHeader == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new TsaException("Invoice was not found!");
			}
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			if(poLine == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new TsaException("PoLine can not be Empty!");
			}
			ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
			if (userId.equalsIgnoreCase("PS-ACI"))
			{
				if (receiptLine == null)
				{
					this.setStatus(Status.FAILED);
				}
				else
				{
					String qtyToInvoiceString = (String)incomingRequest.get("InvoiceLine_quantity");
					if (Utility.isEmpty(qtyToInvoiceString))
					{
						qtyToInvoiceString = "0";
					}
					BigDecimal qtyToInvoice = new BigDecimal(qtyToInvoiceString);
					BigDecimal lineTotal = poLine.getUnitPrice().multiply(qtyToInvoice).setScale(2, BigDecimal.ROUND_HALF_UP);
					incomingRequest.put("InvoiceLine_lineTotal", lineTotal.toString());
				}
			}

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("InvoiceLine_icIvcLine", ukg.getUniqueKey().toString());
			incomingRequest.put("InvoiceLine_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
			incomingRequest.put("InvoiceLine_icLineHistory", ukg.getUniqueKey().toString());
			incomingRequest.put("InvoiceLine_invoiceNumber", invoiceHeader.getInvoiceNumber().toString());
			incomingRequest.put("InvoiceLine_icPoHeader", poLine.getIcPoHeader().toString());
			incomingRequest.put("InvoiceLine_icPoLine", poLine.getIcPoLine().toString());
			incomingRequest.put("InvoiceLine_icRelPoLine", poLine.getIcLineKey().toString());
			incomingRequest.put("InvoiceLine_lineNumber", incomingRequest.get("lineNumber"));
			incomingRequest.put("InvoiceLine_itemNumber", poLine.getItemNumber());
			incomingRequest.put("InvoiceLine_description", poLine.getDescription());
			incomingRequest.put("InvoiceLine_umCode", poLine.getUmCode());
			incomingRequest.put("InvoiceLine_umFactor", poLine.getUmFactor().toString());
			incomingRequest.put("InvoiceLine_unitPrice", poLine.getUnitPrice().toString());
			incomingRequest.put("InvoiceLine_taxable", poLine.getTaxable());
			incomingRequest.put("InvoiceLine_taxPercent", poLine.getTaxPercent().toString());
			incomingRequest.put("InvoiceLine_asset", poLine.getAsset());

			incomingRequest.put("InvoiceLine_status", DocumentStatus.IVC_INPROGRESS);
			incomingRequest.put("InvoiceLine_commodity", poLine.getCommodity()) ;

			incomingRequest.put("Account_icHeader", poLine.getIcPoHeader().toString());
			incomingRequest.put("Account_icLine", poLine.getIcPoLine().toString());
			incomingRequest.put("newAccount_icHeader", invoiceHeader.getIcIvcHeader().toString());
			incomingRequest.put("newAccount_icLine", (String) incomingRequest.get("InvoiceLine_icIvcLine"));
			incomingRequest.put("newAccount_accountType", "IVL");

			incomingRequest.put("createdfrom", "PO");

			this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e)
	    {
            this.setStatus(Status.FAILED);
            throw e;
        }
		return ret;
	}

}
