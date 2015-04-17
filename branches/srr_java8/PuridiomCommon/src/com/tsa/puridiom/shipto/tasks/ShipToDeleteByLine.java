/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.shipto.tasks;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class ShipToDeleteByLine extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("ShipTo_icHeader");
		String icLine = (String)incomingRequest.get("ShipTo_icLine") ;
		if (icLine == null) {
			icLine = "0" ;
		}
		
        String queryString = "from ShipTo as s where s.id.icHeader = ? AND s.id.icLine = ?" ;

		BigDecimal bdHeader = new BigDecimal(icHeader);
		BigDecimal bdLine = new BigDecimal(icLine);

		this.setStatus(dbs.delete(queryString,	
					new Object[] {bdHeader, bdLine},
					new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL})) ;

					
		return object  ;
	}
}
