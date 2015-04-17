/*
 * Created on August 24, 2005
 */
package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class InvoiceHeaderRetrieveBillToAddressSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader") ;
			if (invoiceHeader == null) {
				throw new Exception("The InvoiceHeader entity was not found.");
			}
			String billToCode = invoiceHeader.getBillToCode();

			incomingRequest.put("Address_addressCode", billToCode) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
