package com.tsa.puridiom.invvendor.tasks;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvVendorRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String itemNumber = (String ) incomingRequest.get("InvVendor_itemNumber");
		String vendorId = (String ) incomingRequest.get("InvVendor_vendorId");

		String queryString = "from InvVendor as invVendor where invVendor.id.itemNumber = ?,invVendor.id.vendorId = ?";
		List result = dbs.query(queryString, new Object[] {itemNumber, vendorId, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
				
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}

}