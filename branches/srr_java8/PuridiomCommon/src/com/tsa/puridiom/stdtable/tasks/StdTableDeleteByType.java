/*
 * Created on Oct 16, 2008
 */
package com.tsa.puridiom.stdtable.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;

import org.hibernate.Hibernate;
/**
 * @author Kelli
 */
public class StdTableDeleteByType extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession");

        String tableType = (String)incomingRequest.get("StdTable_tableType");

        String queryString = "from StdTable as stdTable where stdTable.id.tableType = ?";
        this.setStatus(dbs.delete(queryString, tableType, Hibernate.STRING));

		return object ;
	}
}
