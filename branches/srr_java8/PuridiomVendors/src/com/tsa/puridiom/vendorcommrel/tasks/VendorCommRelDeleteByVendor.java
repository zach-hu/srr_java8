/*
 * Created on July 21, 2004
 */
package com.tsa.puridiom.vendorcommrel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import org.hibernate.Hibernate;
import java.util.Map;

/**
 * @author Kelli
 */
public class VendorCommRelDeleteByVendor extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String	vendorId = (String)incomingRequest.get("VendorCommRel_vendorId");
		
		String queryString = "from VendorCommRel as v where v.id.vendorId = ?" ;

		dbs.delete(queryString, vendorId, Hibernate.STRING) ;

		this.setStatus(dbs.getStatus());
		return null	;
	}
}
