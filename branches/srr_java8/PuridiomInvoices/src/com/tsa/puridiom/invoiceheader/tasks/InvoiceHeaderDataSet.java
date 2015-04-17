/*
 * Created on Aug 18, 20053
 */
package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/**
 * @author kathleen
 */
public class InvoiceHeaderDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");

		invoiceHeader.setInvoiceLineList((List) incomingRequest.get("invoiceLineList"));
		invoiceHeader.setDocCommentList((List) incomingRequest.get("docCommentList"));
		invoiceHeader.setAccountList((List) incomingRequest.get("accountList"));
		invoiceHeader.setTaxAccountList((List) incomingRequest.get("taxAccountList"));
		invoiceHeader.setUseTaxAccountList((List) incomingRequest.get("usetaxAccountList"));
		invoiceHeader.setShippingAccountList((List) incomingRequest.get("shippingAccountList"));
		invoiceHeader.setOtherAccountList((List) incomingRequest.get("otherAccountList"));
		invoiceHeader.setBillToAddress((Address) incomingRequest.get("billToAddress"));
		invoiceHeader.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
		invoiceHeader.setDocAttachmentList((List) incomingRequest.get("docAttachmentList"));

		this.setStatus(Status.SUCCEEDED) ;

		return null  ;
	}
}
