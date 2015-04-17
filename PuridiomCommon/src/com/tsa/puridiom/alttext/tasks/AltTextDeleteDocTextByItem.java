/*
 * Created on Aug 4, 2008
 */
package com.tsa.puridiom.alttext.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Kelli
 */
public class AltTextDeleteDocTextByItem extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        String queryString = "from DocText as DocText where DocText.referenceType = 'ALT' and  DocText.idReference = ?";

        String id = (String) incomingRequest.get("AltText_id");
        String itemNumber = (String) incomingRequest.get("AltText_itemNumber");
        String icLine = (String) incomingRequest.get("AltText_icLine");
        String idReference = id + "|" + itemNumber + "|" + icLine;

		this.setStatus(dbs.delete(queryString, new Object[] {idReference}, new Type[] {Hibernate.STRING}));

		this.setStatus(dbs.getStatus());
		return object;
	}
}
