package com.tsa.puridiom.inspectionstd.tasks;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import org.hibernate.*;
import org.hibernate.type.Type;

public class InspectionStdDeleteByStdCriteria extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession");

        String stdCode = (String)incomingRequest.get("InspectionStd_standardCode");


		String queryString = "from InspectionStd as InspStd where InspStd.id.standardCode = ?";

		this.setStatus(dbs.delete(queryString,
				new Object[] {stdCode},
				new Type[] {Hibernate.STRING}));

		return object;
	}
}
