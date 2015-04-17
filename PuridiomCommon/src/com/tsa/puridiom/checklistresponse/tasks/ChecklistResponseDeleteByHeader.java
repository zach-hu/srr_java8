/*
 * Created on Aug 31, 2006
 */
package com.tsa.puridiom.checklistresponse.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.Map;
import org.hibernate.*;

/**
 * @author Kelli
 */
public class ChecklistResponseDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("ChecklistResponse_icHeader");
		BigDecimal bdHeader = new BigDecimal(icHeader) ;
		
        String queryString = "from ChecklistResponse as c where c.id.icHeader = ?" ;

		int retval = dbs.delete(queryString,	
					bdHeader,
					Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(retval) ;
					
		return object  ;
	}
}
