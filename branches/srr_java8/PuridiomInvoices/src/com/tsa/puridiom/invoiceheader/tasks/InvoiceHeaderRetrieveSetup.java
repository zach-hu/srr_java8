/*
 * Created on Aug 24, 2005
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.util.*;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
/**
 * @author Kelli
 */
public class InvoiceHeaderRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader") ;

		if (invoiceHeader == null) {
			this.setStatus(Status.FAILED) ;
		} else {
			incomingRequest.put("InvoiceLine_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
			incomingRequest.put("InvoiceVendor_vendorId", invoiceHeader.getVendorId());
			incomingRequest.put("InvoiceAddress_vendorId", invoiceHeader.getVendorId());
			incomingRequest.put("InvoiceAddress_addressCode", invoiceHeader.getVendorAddrCode());
			incomingRequest.put("Account_icHeader", invoiceHeader.getIcIvcHeader().toString());
			incomingRequest.put("Account_icLine", "0");
			incomingRequest.put("DocComment_icHeader", invoiceHeader.getIcIvcHeader().toString());
			incomingRequest.put("DocComment_icLine", "0");
			incomingRequest.put("DocAttachment_icHeader", invoiceHeader.getIcIvcHeader().toString());
			incomingRequest.put("PoLine_icPoHeader", invoiceHeader.getIcPoHeader().toString());
		}

		this.setStatus(Status.SUCCEEDED);

		return null ;
	}
}
