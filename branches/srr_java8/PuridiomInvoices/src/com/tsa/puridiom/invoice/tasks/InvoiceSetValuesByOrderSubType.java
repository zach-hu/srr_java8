package com.tsa.puridiom.invoice.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceSetValuesByOrderSubType extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		try
		{
			String oid = (String) incomingRequest.get("organizationId");
			String dateFormat = PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;

			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			BigDecimal invoiceTotal = new BigDecimal(0) ;

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
						PoLine poLine = (PoLine) poLineList.get(i);
						ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);

						//invoiceLine.setQuantity(receiptLine.getQtyAccepted());
						invoiceLine.setQtyOrdered(poLine.getQuantity());
						invoiceLine.setQtyReceived(receiptLine.getQtyAccepted());
						invoiceLine.setQtyInvoiced(invoiceLine.getQuantity());
						BigDecimal total = invoiceLine.getQuantity().multiply(poLine.getUnitPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);


						invoiceTotal = total.add(invoiceTotal);

						invoiceLine.setStatus(DocumentStatus.IVC_ONHOLD);
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


				invoiceHeader.setInvoiceTotal((invoiceTotal));
				invoiceHeader.setInvoiceSubtotal((invoiceTotal));
				invoiceHeader.setInvoiceDate(Dates.getDate(Dates.today(dateFormat), dateFormat));

				invoiceHeader.setStatus(DocumentStatus.IVC_ONHOLD);
				receiptHeader.setReceiptStatus(DocumentStatus.RCV_INVOICED);

				//commit to release lock before calling costpoint process
				dbs.update(invoiceHeader);
				dbs.update(receiptHeader);
				dbs.commit();

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
