/*
 * Created on Dec 14, 2006
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author kathleen
 */
public class InvoiceHeaderRetrieveByPoNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
	        String poNumber = poHeader.getPoNumber();
			BigDecimal releaseNumber = poHeader.getReleaseNumber();

	        String queryString =  	"from InvoiceHeader as ih where ih.poNumber = ? and ih.poRelease = ? ";

			result = dbs.query(queryString, new Object[] {poNumber, releaseNumber}, new Type[] {Hibernate.STRING, Hibernate.BIG_DECIMAL});

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
