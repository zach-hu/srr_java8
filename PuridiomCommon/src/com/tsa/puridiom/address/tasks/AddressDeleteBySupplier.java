/*
 * Created on Dec 12, 2005
 * @author Kelli
 * com.tsa.puridiom.tasks.address.AddressDeleteBySupplier.java
 */

package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

import org.hibernate.Hibernate;

public class AddressDeleteBySupplier extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		String vendorId = (String)incomingRequest.get("Address_addressType");
        String queryString = "from Address as a where a.id.addressType = ?";

        dbs.delete(queryString, vendorId, Hibernate.STRING);

		this.setStatus(dbs.getStatus()) ;
		return null;
	}
}
