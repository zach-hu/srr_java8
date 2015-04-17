/*
 * Created on Sept. 2, 2004
 */
package com.tsa.puridiom.schedule.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Kelli
 */
public class ScheduleRetrievePerformanceLinesByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String	scheduleType = "R";
        incomingRequest.put("Schedule_scheduleType",scheduleType);
		String documentType = (String ) incomingRequest.get("Schedule_documentType");
		String icHeaderString = (String) incomingRequest.get("Schedule_icHeader");
		BigDecimal icHeader = new BigDecimal ( icHeaderString );

		String queryString = "from Schedule as Schedule where Schedule.id.scheduleType = ? and Schedule.id.documentType = ? and Schedule.id.icHeader = ? ";
		List resultList = dbs.query(queryString, new Object[] {scheduleType, documentType, icHeader } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

		this.setStatus(dbs.getStatus()) ;
		return resultList  ;
	}
}
