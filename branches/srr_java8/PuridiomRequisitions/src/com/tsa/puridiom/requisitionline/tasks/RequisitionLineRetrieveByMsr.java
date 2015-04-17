/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator
 */
public class RequisitionLineRetrieveByMsr extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String icMsrLine = (String)incomingRequest.get("RequisitionLine_icLineHistory") ;
        if (icMsrLine == null) {
        	icMsrLine = (String)incomingRequest.get("icMsrLine") ;
        }
        BigDecimal b_icMsrLine = new BigDecimal(-1) ;
        if (icMsrLine != null) {
        	b_icMsrLine = new BigDecimal(icMsrLine) ;
        }

        String queryString =  	"from RequisitionLine as b where b.reqType = 'M' AND b.icLineHistory = ?" ;

		//List result = dbs.query(queryString,	bdHeader,	Hibernate.BIG_DECIMAL) ;
        List resultList = dbs.query(queryString,
				new Object[] {b_icMsrLine},
				new Type[] {Hibernate.BIG_DECIMAL}) ;

        if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
