package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class MsrLineRetrieveByHeader extends Task {

	public Object executeTask(Object object) throws Exception {
	
		Map incomingRequest = (Map)object;
	
	    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	    String icHeader = (String)incomingRequest.get("MsrLine_icReqHeader") ;
	    if(icHeader == null){
	    	icHeader = (String)incomingRequest.get("MsrHeader_icReqHeader") ;
	    }
		BigDecimal bdHeader = new BigDecimal(icHeader) ;
	
	    String queryString = "from RequisitionLine as b where b.icReqHeader = ? order by b.lineNumber ASC";
	
		List result = dbs.query(queryString,	bdHeader,	Hibernate.BIG_DECIMAL) ;
	
		this.setStatus(dbs.getStatus()) ;
	
		return result ;
	}

}
