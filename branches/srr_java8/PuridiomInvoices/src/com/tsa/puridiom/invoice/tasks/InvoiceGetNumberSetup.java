package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.AutoGenType;

/**
 * @author Kelli
 */
public class InvoiceGetNumberSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String	oid = (String) incomingRequest.get("organizationId") ;
			String	icIvcHeaderString = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
			String	fiscalYear = (String) incomingRequest.get("InvoiceHeader_fiscalYear");
//			String	poType = (String) incomingRequest.get("InvoiceHeader_poType");

			if (Utility.isEmpty(fiscalYear))
			{
				fiscalYear = "1994";
			}

			String	temp = AutoGenType.toString("INV",oid);
			if (Utility.isEmpty(temp) || temp.equals("INV")) {
				temp = "INV" ;
			}

			incomingRequest.put("AutoGen_documentType",  temp) ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("InvoiceLine_icIvcHeader", icIvcHeaderString);

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return null ;
	}
}
