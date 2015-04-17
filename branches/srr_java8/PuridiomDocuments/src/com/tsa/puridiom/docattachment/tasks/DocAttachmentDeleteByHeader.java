/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.docattachment.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator 
 */
public class DocAttachmentDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("DocAttachment_icHeader");
		BigDecimal bdIcHeader = new BigDecimal(icHeader) ;
		
        String queryString = "from DocAttachment as d where d.id.icHeader = ?" ;

		int retval = dbs.delete(queryString,	
					bdIcHeader,
					Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(retval) ;
					
		return object  ;
	}
}
