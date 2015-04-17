/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class RequisitionLineSourcingRetrieveByHeader extends Task {
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

	    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	    String icHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader") ;
		BigDecimal bdHeader = new BigDecimal(icHeader) ;
		String status = DocumentStatus.REQ_PLANNING_APPROVED ;

        //String queryString = "from RequisitionLine as b where b.icReqHeader = ? and b.status = ? order by b.vendorId, b.udf7Code, b.udf10Code ASC";
		String queryString = "from RequisitionLine as b where b.icReqHeader = ? and b.status = ? order by b.lineNumber ASC";
		
		List result = dbs.query(queryString,	new Object[] {bdHeader, status},	new Type[] {Hibernate.BIG_DECIMAL, Hibernate.STRING} ) ;

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
