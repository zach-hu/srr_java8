package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class InvRequisitionRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icIvcHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
			BigDecimal icReqHeader = new BigDecimal ( icIvcHeader );
			
			String queryString = "select distinct RequisitionHeader.icReqHeader from InvoiceHeader as InvoiceHeader,PoHeader as PoHeader,RequisitionHeader as RequisitionHeader where InvoiceHeader.icPoHeader = PoHeader.icPoHeader and PoHeader.icReqHeader=RequisitionHeader.icReqHeader and InvoiceHeader.icIvcHeader=? ";
			List resultList = dbs.query(queryString, new Object[] {icReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
			
			if (resultList != null && resultList.size() > 0)
			{
				icReqHeader = (BigDecimal) resultList.get(0);
				icIvcHeader = icReqHeader.toString();
			}
			
			incomingRequest.put("ApprovalLog_icHeader", icIvcHeader);
	
	
			if (!incomingRequest.containsKey("InvoiceHeader_status")) {
			    InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			    if (invoiceHeader != null) {
			        incomingRequest.put("InvoiceHeader_status", invoiceHeader.getStatus());
			    }
			}
			this.setStatus(Status.SUCCEEDED);
	
			return result;	
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "Invoice Routing Setup error " + e.getMessage());

			throw e;
		}
	}
}
