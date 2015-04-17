/*
 * Created on October 06, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author kathleen
 */
public class InvoiceLineRetrieveUnitPrice extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        BigDecimal bdQtyInvoiced = HiltonUtility.ckNull((BigDecimal)incomingRequest.get("qtyInvoiced"));
        if (bdQtyInvoiced.compareTo(new BigDecimal(0)) > 0)
        {
        	String icLine = (String)incomingRequest.get("InvoiceLine_icPoLine") ;
    		BigDecimal bdLine = new BigDecimal(icLine) ;

            String queryString =  	"select invoiceLine.unitPrice from InvoiceLine as invoiceLine where invoiceLine.icPoLine = ? " ;

            List resultList = dbs.query(queryString, 	new Object[] {bdLine}, new Type[] {Hibernate.BIG_DECIMAL}) ;

            if (resultList != null && resultList.size() > 0)
    		{
    			result = resultList.get(0);
    		}
        }
        else
        {
        	result = new BigDecimal(0);
        }

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
