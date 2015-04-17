/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.schedule.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class ScheduleRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("Schedule_icHeader");

        String queryString =
        	"FROM Schedule AS b WHERE b.id.icHeader = ? ORDER BY b.id.scheduleType, b.scheduleDate ";

		BigDecimal bdHeader = new BigDecimal(icHeader);

		List result = dbs.query(queryString,	bdHeader,Hibernate.BIG_DECIMAL) ;

		this.setStatus(dbs.getStatus()) ;
		return result  ;
	}
}
