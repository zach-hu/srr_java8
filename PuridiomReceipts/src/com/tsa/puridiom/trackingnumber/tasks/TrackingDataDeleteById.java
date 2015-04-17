/*
 * Created on December 23, 2004 
 */
package com.tsa.puridiom.trackingnumber.tasks;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class TrackingDataDeleteById extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
        String formType = (String) incomingRequest.get("formType");
        BigDecimal icHeader = new BigDecimal("0");
		if(formType.equalsIgnoreCase("REC"))
		{
			String icRecHeaderString = (String ) incomingRequest.get("ReceiptHeader_icRecHeader");

			icHeader = new BigDecimal(icRecHeaderString);
		}
		
        String queryString = "from TrackingData as TrackingData where TrackingData.id.icHeader = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {icHeader}, new Type[] {Hibernate.BIG_DECIMAL}));
					
		return object;
	}
}
