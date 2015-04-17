/*
 * Created on Aug 19, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import org.hibernate.*;

/**
 * @author kathleen
 */
public class InvoiceLineRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        String icHeader = (String) incomingRequest.get("InvoiceLine_icIvcHeader");
			BigDecimal bdHeader = new BigDecimal(icHeader);

	        String queryString =  	"from InvoiceLine as ih where ih.icIvcHeader = ? order by ih.icIvcLine ASC";

			result = dbs.query(queryString,	bdHeader,	Hibernate.BIG_DECIMAL);

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}

		return result;
	}

}
