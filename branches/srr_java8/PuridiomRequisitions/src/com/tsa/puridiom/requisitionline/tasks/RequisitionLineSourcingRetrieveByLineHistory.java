/*
 * Created on July 8, 2011
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Matthew
 * Modified by: Jael - FEB 02, 2012
 */
@SuppressWarnings("unchecked")
public class RequisitionLineSourcingRetrieveByLineHistory extends Task {
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

	    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	    String icHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader") ;
		BigDecimal bdHeader = new BigDecimal(icHeader) ;
		String status = DocumentStatus.REQ_PLANNING_SOURCED ;

        String queryString = "from RequisitionLine as b where b.icReqHeader = ? order by b.lineNumber, b.vendorId, b.udf7Code, b.udf10Code ASC";

		List result = dbs.query(queryString,	new Object[] {bdHeader},	new Type[] {Hibernate.BIG_DECIMAL} ) ;

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}