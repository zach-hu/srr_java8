/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
/**
 * @author Administrator 
 */
public class RequisitionLineDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icReqHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader");
		BigDecimal bdReqHeader = new BigDecimal(icReqHeader) ;
		
        String queryString = "from RequisitionLine as r where r.icReqHeader = ?" ;

		dbs.delete(queryString,	
					bdReqHeader,
					Hibernate.BIG_DECIMAL) ;

		this.setStatus(dbs.getStatus());
		return object	;
	}
}
