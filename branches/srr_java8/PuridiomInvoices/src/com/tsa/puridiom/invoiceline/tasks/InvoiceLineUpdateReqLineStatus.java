/*
 * Created on Sep 30, 2004
 *
 * @author  kathleen
 * project: HiltonInvoices
 * package com.tsa.puridiom.invoiceline.tasks.InvoiceLineUpdateReqLineStatus.java
 *
 */
package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InvoiceLineUpdateReqLineStatus extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
		{
            Map incomingRequest = (Map)object;

            InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");

            if (incomingRequest.containsKey("invoiceLine"))
            {
                InvoiceLine invoiceLine = (InvoiceLine) incomingRequest.get("invoiceLine");
                if (invoiceLine != null)
                {
					String	icInvoiceLine = "";

					if (invoiceLine.getIcIvcLine().compareTo(new BigDecimal(0)) > 0) {
					    icInvoiceLine = String.valueOf(invoiceLine.getIcIvcLine());
					}

					if (!Utility.isEmpty(icInvoiceLine)) {
	                    String organizationId = (String) incomingRequest.get("organizationId");
	                    String userId = (String) incomingRequest.get("userId");
	                    DBSession dbsession = (DBSession) incomingRequest.get("dbsession");

	                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("invoiceline-update-status.xml");
						Map requestParameters = new HashMap();

						String status = invoiceLine.getStatus();
						String	invoiceType = invoiceHeader.getInvoiceType();

						if (status == DocumentStatus.CANCELLED || status == DocumentStatus.DELETE_INPROGRESS) {
						    if (invoiceType.equals(RfqType.PRICING)) {
						        status = DocumentStatus.REQ_FORWARDED;
						    } else {
						        status = DocumentStatus.REQ_APPROVED;
						    }
						}

						requestParameters.put("organizationId",organizationId);
						requestParameters.put("userId",userId);
						requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						requestParameters.put("dbsession", dbsession);
						requestParameters.put("RequisitionLine_icInvoiceLine", String.valueOf(icInvoiceLine));
						requestParameters.put("RequisitionLine_status", status);

						process.executeProcess(requestParameters);

						if (process.getStatus() != Status.SUCCEEDED) {
						    throw new Exception("An error ocurred updating the requisition line status for invoice line #" + invoiceLine.getIcIvcLine());
						}
					}
				}
			}

            this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
		    throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
