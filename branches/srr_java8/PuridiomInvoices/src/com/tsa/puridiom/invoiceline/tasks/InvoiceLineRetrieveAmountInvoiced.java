/*
 * Created on Sept 14, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author kathleen
 */
public class InvoiceLineRetrieveAmountInvoiced extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String icIvcLine = (String)incomingRequest.get("InvoiceLine_icIvcLine") ;
        String icPoLine = (String)incomingRequest.get("InvoiceLine_icPoLine") ;
        BigDecimal bdIcIvcLine = new BigDecimal(icIvcLine) ;
		BigDecimal bdIcPoLine = new BigDecimal(icPoLine) ;

        String queryString =  	"select sum(invoiceLine.lineTotal) from InvoiceLine as invoiceLine where invoiceLine.icPoLine = ? AND invoiceLine.icIvcLine <> ? AND invoiceLine.invoiceNumber <> 'N/A' " ;

        List resultList = dbs.query(queryString, new Object[] {bdIcPoLine, bdIcIvcLine}, new Type[] {Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;

        if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
