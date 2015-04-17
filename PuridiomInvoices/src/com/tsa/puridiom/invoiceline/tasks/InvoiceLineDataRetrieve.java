/*
 * Created on October 05, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class InvoiceLineDataRetrieve extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        this.setStatus(Status.SUCCEEDED) ;

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("invoicelinedata-retrieve.xml");

		List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); ) {
				incomingRequest.put("invoiceLine", (InvoiceLine) it.next());
				process.executeProcess(incomingRequest);
				this.setStatus(process.getStatus());
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
        }

		return invoiceLineList;
	}

}
