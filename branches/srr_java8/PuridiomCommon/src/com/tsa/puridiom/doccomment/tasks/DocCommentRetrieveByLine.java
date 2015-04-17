/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.doccomment.tasks;

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
public class DocCommentRetrieveByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("DocComment_icHeader");
		String icLine = (String)incomingRequest.get("DocComment_icLine") ;
		if (icLine == null) {
			icLine = "0" ;
		}
		
        String queryString = "from DocComment as c where c.id.icHeader = ? AND c.id.icLine = ?" ;

		BigDecimal bdHeader = new BigDecimal(icHeader);
		BigDecimal bdLine = new BigDecimal(icLine);

		List result = dbs.query(queryString,	
					new Object[] {bdHeader, bdLine},
					new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;
					
		this.setStatus(dbs.getStatus()) ;
					
		return result  ;
	}
}
