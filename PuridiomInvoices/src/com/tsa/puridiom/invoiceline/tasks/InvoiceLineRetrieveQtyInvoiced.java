/*
 * Created on Sept 14, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author kathleen
 */
public class InvoiceLineRetrieveQtyInvoiced extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String icIvcLine = (String)incomingRequest.get("InvoiceLine_icIvcLine") ;
        String icPoLine = (String)incomingRequest.get("InvoiceLine_icPoLine") ;
        String icLineKey = (String)incomingRequest.get("PoLine_icLineKey");
        String status = (String)incomingRequest.get("InvoiceLine_status"); 
        
        BigDecimal bdIcIvcLine = new BigDecimal(icIvcLine);
        BigDecimal bdicLineKey = new BigDecimal(icLineKey);        
		BigDecimal bdIcPoLine = new BigDecimal(icPoLine) ;

		BigDecimal bdStatus = new BigDecimal(status);
		List resultList = null;
		BigDecimal approved = new BigDecimal(6010);
		
		String organizationId = (String) incomingRequest.get("organizationId");
		String qtyInvCalculateOption = PropertiesManager.getInstance(organizationId).getProperty("IVC OPTIONS", "DESACTIVATE ABS QTY INV CALCULATE", "N") ;
		
		if(incomingRequest.containsKey("Type") && bdStatus.compareTo(approved)>=0 && qtyInvCalculateOption.equals("Y")){
			String queryString =  	"select sum(invoiceLine.quantity) from InvoiceLine as invoiceLine where invoiceLine.icRelPoLine = ? " +
		    "AND invoiceLine.invoiceNumber <> 'N/A' AND (invoiceLine.status <> '" + DocumentStatus.CANCELLED + "' AND invoiceLine.status <> '" + DocumentStatus.IVC_INPROGRESS + "')" ;
		    resultList = dbs.query(queryString, new Object[] {bdicLineKey}, new Type[] {Hibernate.BIG_DECIMAL}) ;
		}else if(incomingRequest.containsKey("Type") && bdStatus.compareTo(approved)>=0 && qtyInvCalculateOption.equals("N"))		
		{
			String queryString =  	"select sum(abs(invoiceLine.quantity)) from InvoiceLine as invoiceLine where invoiceLine.icRelPoLine = ? " +
		    "AND invoiceLine.invoiceNumber <> 'N/A' AND (invoiceLine.status <> '" + DocumentStatus.CANCELLED + "' AND invoiceLine.status <> '" + DocumentStatus.IVC_INPROGRESS + "')" ;
		    resultList = dbs.query(queryString, new Object[] {bdicLineKey}, new Type[] {Hibernate.BIG_DECIMAL}) ;			
		}
		else
		{
			String queryString =  	"select sum(invoiceLine.quantity) from InvoiceLine as invoiceLine where invoiceLine.icRelPoLine = ? AND invoiceLine.icIvcLine <> ? " +
			"AND invoiceLine.invoiceNumber <> 'N/A' AND (invoiceLine.status <> '" + DocumentStatus.CANCELLED + "' AND invoiceLine.status <> '" + DocumentStatus.IVC_INPROGRESS + "')" ;
			resultList = dbs.query(queryString, new Object[] {bdicLineKey, bdIcIvcLine}, new Type[] {Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
		}
		if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}

		this.setStatus(dbs.getStatus()) ;
		if (result == null)
		{
			result = new BigDecimal("0");
		}

		return result ;
	}

}
