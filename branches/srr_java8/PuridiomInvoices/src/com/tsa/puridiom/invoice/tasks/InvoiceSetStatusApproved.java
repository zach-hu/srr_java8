package com.tsa.puridiom.invoice.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceSetStatusApproved extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");			

			List invoiceLineList = (List) incomingRequest.get("invoiceLineList") ;
			List poLineList = (List) incomingRequest.get("poLines") ;
			List receiptLineList = (List) incomingRequest.get("receiptLineList") ;

			System.out.println("items list null. " + (invoiceLineList == null ? "[invoiceLineList]":"") + (poLineList == null ? "[poLineList]":"") + (receiptLineList == null ? "[receiptLineList]":""));

			if(poHeader != null && invoiceHeader != null){
				if(invoiceLineList != null && !invoiceLineList.isEmpty() &&
						poLineList != null && !poLineList.isEmpty() &&
						receiptLineList != null && !receiptLineList.isEmpty()){

					for (int i=0; i < invoiceLineList.size(); i++) {
						InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);
						ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);

						invoiceLine.setStatus(DocumentStatus.IVC_APPROVED);
						receiptLine.setStatus(DocumentStatus.RCV_INVOICED);
					}

				}
				else
				{
					this.setStatus(Status.FAILED);
					Log.debug(this, "PoNumber: "+poHeader.getPoNumber()+" ReceiptNumber: "+receiptHeader.getReceiptNumber()+" InvoiceNumber: "+invoiceHeader.getInvoiceNumber());
					if(invoiceLineList != null && poLineList != null && receiptLineList != null){
						Log.error(this,"***** Error, item list is empty. [invoiceLineList: "+invoiceLineList.size()+"][poLineList: "+poLineList.size()+"][receiptLineList: "+receiptLineList.size()+"]");
					} else {
						Log.error(this,"***** Error, item list is null." + (invoiceLineList == null ? " [invoiceLineList]":"") + (poLineList == null ? "[poLineList]":"") + (receiptLineList == null ? "[receiptLineList]":""));
					}
					throw new TsaException("An Error ocurred creating the current Invoice" + invoiceHeader.getInvoiceNumber());
				}


				invoiceHeader.setStatus(DocumentStatus.IVC_APPROVED);
				receiptHeader.setReceiptStatus(DocumentStatus.RCV_INVOICED);
				
				this.setStatus(Status.SUCCEEDED);
			} else {
				this.setStatus(Status.FAILED);
				Log.error(this,"An Error ocurred creating the current Invoice " + poHeader.getPoNumber());
				throw new TsaException("An Error ocurred creating the current Invoice");
			}

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			Log.error(this, e);
			throw new TsaException("An Error ocurred creating the current Invoice ", e);
		}
		return null ;
	}
}
