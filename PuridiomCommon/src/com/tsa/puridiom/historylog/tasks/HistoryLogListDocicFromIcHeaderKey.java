/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.awt.image.BufferStrategy;
import java.math.*;

import org.hibernate.*;
import org.hibernate.type.Type;
/**
 * @author Administrator
 */
public class HistoryLogListDocicFromIcHeaderKey extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        String icHeaderKey = (String) incomingRequest.get("icHeaderKey");

        String queryString = "Select ph.icPoHeader from PoHeader as ph where ph.icHeaderKey = " + icHeaderKey;
        //String queryString = "Select ph.icPoHeader from PoHeader as ph where ph.icHeaderKey = 14147312300014 " ;
        List result = dbs.query(queryString) ;


		this.setStatus(dbs.getStatus()) ;
		return result ;

	}
}
