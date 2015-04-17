/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.sendqueue.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;

import org.hibernate.*;
/**
 * @author Administrator
 */
public class SendQueueRetrieveByHistoryHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String queryString = "from SendQueue as sq where sq.docic = ? order by sq.datesent, sq.timesent";
        String formtype = (String)incomingRequest.get("formtype");
        String icHeader = "0";
        if (formtype.equalsIgnoreCase("RFQ")) {
        	icHeader = (String)incomingRequest.get("RfqLine_icRfqHeader");
        }
        if (formtype.equalsIgnoreCase("REQ")) {
        	icHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader");
        }
        if (formtype.equalsIgnoreCase("PO")) {
        	icHeader = (String)incomingRequest.get("PoLine_icPoHeader");
        }
        if (formtype.equalsIgnoreCase("IVC")) {
        	icHeader = (String)incomingRequest.get("InvoiceLine_icIvcHeader");
        }
        if (formtype.equalsIgnoreCase("RCH")) {
        	icHeader = (String)incomingRequest.get("ReceiptLine_icRecHeader");
        }
		BigDecimal bdHeader = new BigDecimal(icHeader);

		List result = dbs.query(queryString, bdHeader, Hibernate.BIG_DECIMAL);

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
