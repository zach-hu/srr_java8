/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class RequisitionLineRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
	
		Map incomingRequest = (Map)object;
	
	    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	    String icHeader = (String)incomingRequest.get("RequisitionLine_icReqHeader") ;
	    if(icHeader == null){
	    	icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader") ;
	    }
		BigDecimal bdHeader = new BigDecimal(icHeader) ;
	
	    String queryString = "from RequisitionLine as b where b.icReqHeader = ? order by b.lineNumber ASC";
	
		List result = dbs.query(queryString,	bdHeader,	Hibernate.BIG_DECIMAL) ;
	
		this.setStatus(dbs.getStatus()) ;
	
		return result ;
	}

}
