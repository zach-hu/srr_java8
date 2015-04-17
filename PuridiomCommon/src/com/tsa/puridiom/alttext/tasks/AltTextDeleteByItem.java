/*
 * Created on Aug 4, 2008
 */
package com.tsa.puridiom.alttext.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Kelli
 */
public class AltTextDeleteByItem extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        //String queryString = "from AltText as AltText, DocText as DocText where AltText.id.source = ? and  AltText.id.id = ? and AltText.id.itemNumber = ? and DocText.icText =  AltText.icText";
        String queryString = "from AltText as AltText where AltText.id.source = ? and  AltText.id.id = ? and AltText.id.itemNumber = ?";

		String source = (String)incomingRequest.get("AltText_source");
        String id = (String)incomingRequest.get("AltText_id");
        String itemNumber = (String)incomingRequest.get("AltText_itemNumber");

		this.setStatus(dbs.delete(queryString, new Object[] {source, id, itemNumber}, new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}));

		this.setStatus(dbs.getStatus());
		return object;
	}
}
