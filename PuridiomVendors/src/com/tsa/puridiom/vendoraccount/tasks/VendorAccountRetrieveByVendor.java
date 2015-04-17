package com.tsa.puridiom.vendoraccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class VendorAccountRetrieveByVendor extends Task{
	public Object  executeTask (Object object) throws Exception
	{

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

        String vendorId = (String ) incomingRequest.get("VendorAccount_vendorId");

        String queryString = "from VendorAccount as VendorAccount where VendorAccount.id.vendorId = ? order by VendorAccount.id.accountNumber ASC";

		List result = dbs.query(queryString,	new Object[] {vendorId}, new Type[] {Hibernate.STRING});

		this.setStatus(dbs.getStatus());

		return result;
	}

}