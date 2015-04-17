/*
 * Created on January 25, 2005
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class RequisitionLineRetrieveCheckItem extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String icHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader") ;
		BigDecimal bdHeader = new BigDecimal(icHeader) ;

        String queryString =  	"from RequisitionLine as b where b.icReqHeader = ?" ;

		List resultList = dbs.query(queryString,	bdHeader,	Hibernate.BIG_DECIMAL) ;
		if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
