/*
 * Created on October 23, 2006
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
//import com.tsagate.foundation.utility.TsaException;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author kathleen
 */
public class InvoiceHeaderRetrieveAmountInvoiced extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession");
        	InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
        	BigDecimal icIvcHeader = invoiceHeader.getIcIvcHeader();
            String poNumber = invoiceHeader.getPoNumber();
            BigDecimal poRelease = invoiceHeader.getPoRelease();

            String queryString = "select sum(invoiceHeader.invoiceTotal) from InvoiceHeader as invoiceHeader where " +
            "invoiceHeader.poNumber = ? AND invoiceHeader.poRelease = ? AND invoiceHeader.icIvcHeader <> ? " +
            "AND invoiceHeader.status > '" +DocumentStatus.IVC_INPROGRESS + "' AND invoiceHeader.status <> '" + DocumentStatus.IVC_REJECTED + "' AND invoiceHeader.status <> '" + DocumentStatus.CANCELLED + "'" ;

            List resultList = dbs.query(queryString, new Object[] {poNumber, poRelease, icIvcHeader}, new Type[] {Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});

            if (resultList != null && resultList.size() > 0)
    		{
    			result = resultList.get(0);
    		}

			this.setStatus(dbs.getStatus());
        }
        catch (Exception e)
        {
        	Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            throw e; 
            //throw new TsaException("An Error ocurred submitting the current Invoice ", e);
        }
        return result;
	}

}
