/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.sendqueue.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;

import org.hibernate.*;
/**
 * @author Administrator
 */
public class SendQueueRetrieveByHistoryHeaderByAllRevision extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        List historyDocIcList = (List)incomingRequest.get("historyDocIcList");

        StringBuffer queryString = new StringBuffer(0);
        queryString.append("from SendQueue sq where sq.docic in ( '1111'");

        for(int index=0 ; index < historyDocIcList.size() ; index++){
        	String docIc = historyDocIcList.get(index).toString();
        	queryString.append(", '" + docIc + "' ");
        }
        queryString.append(" )");

		List result = dbs.query(queryString.toString());

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}
}
