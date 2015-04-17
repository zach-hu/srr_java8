/*
 * Created on Dec 23, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.contactContactRetrieveBySupplier.java
 */

package com.tsa.puridiom.contact.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ContactAddress1099RetrieveBySupplier extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("Contact_vendorId");

//			String queryString = "from Contact as Contact, Address as Address where Contact.id.vendorId = ?" +
//											" and Address.id.addressCode = Contact.addressCode and Address.id.addressType = Contact.id.vendorId" +
//											" and Address.addrFld18 = 'Y' order by Contact.id.contactCode";

			String queryString = "from Address as Address where Address.id.addressType = ?" +
			" and Address.addrFld18 = 'Y'";

			List resultList = dbs.query(queryString, new Object[] {vendorId } , new Type[] { Hibernate.STRING}) ;

			incomingRequest.put("contactAddress1099ListSize", String.valueOf(resultList.size()));

			ret = resultList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
