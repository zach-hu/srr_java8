/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.schedule.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.Map;
import org.hibernate.*;

/**
 * @author Administrator
 */
public class ScheduleDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("Schedule_icHeader");
		BigDecimal bdHeader = new BigDecimal(icHeader) ;
		
        String queryString = "from Schedule as s where s.id.icHeader = ?" ;

		int retval = dbs.delete(queryString,	
					bdHeader,
					Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(retval) ;
					
		return object  ;
	}
}
