package com.tsa.puridiom.payment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class PaymentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Payment as payment where 1=1 ");
		if(incomingRequest.containsKey("Payment_icPoHeader"))
		{			
			String icPoHeader = (String) incomingRequest.get("Payment_icPoHeader");
			queryString.append(" AND payment.id.icPoHeader = '" + icPoHeader + "'");
		}
		if(incomingRequest.containsKey("Payment_sequence"))
		{			
			String sequence = (String) incomingRequest.get("Payment_sequence");
			queryString.append(" AND payment.id.sequence = '" + sequence + "'");
		}
		if(incomingRequest.containsKey("Payment_icPoLine"))
		{			
			String icPoLine = (String) incomingRequest.get("Payment_icPoLine");
			queryString.append(" AND payment.icPoLine = '" + icPoLine + "'");
		}
		if(incomingRequest.containsKey("Payment_poNumber"))
		{			
			String poNumber = (String) incomingRequest.get("Payment_poNumber");
			queryString.append(" AND payment.poNumber = '" + poNumber + "'");
		}
		if(incomingRequest.containsKey("Payment_revisionNumber"))
		{			
			String revisionNumber = (String) incomingRequest.get("Payment_revisionNumber");
			queryString.append(" AND payment.revisionNumber = '" + revisionNumber + "'");
		}
		if(incomingRequest.containsKey("Payment_releaseNumber"))
		{			
			String releaseNumber = (String) incomingRequest.get("Payment_releaseNumber");
			queryString.append(" AND payment.releaseNumber = '" + releaseNumber + "'");
		}
		if(incomingRequest.containsKey("Payment_paymentDate"))
		{			
			String paymentDate = (String) incomingRequest.get("Payment_paymentDate");
			queryString.append(" AND payment.paymentDate = '" + paymentDate + "'");
		}
		if(incomingRequest.containsKey("Payment_invoiceNumber"))
		{			
			String invoiceNumber = (String) incomingRequest.get("Payment_invoiceNumber");
			queryString.append(" AND payment.invoiceNumber = '" + invoiceNumber + "'");
		}
		if(incomingRequest.containsKey("Payment_paymentAmount"))
		{			
			String paymentAmount = (String) incomingRequest.get("Payment_paymentAmount");
			queryString.append(" AND payment.paymentAmount = '" + paymentAmount + "'");
		}
		if(incomingRequest.containsKey("Payment_checkNumber"))
		{			
			String checkNumber = (String) incomingRequest.get("Payment_checkNumber");
			queryString.append(" AND payment.checkNumber = '" + checkNumber + "'");
		}
		if(incomingRequest.containsKey("Payment_voucherNumber"))
		{			
			String voucherNumber = (String) incomingRequest.get("Payment_voucherNumber");
			queryString.append(" AND payment.voucherNumber = '" + voucherNumber + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}