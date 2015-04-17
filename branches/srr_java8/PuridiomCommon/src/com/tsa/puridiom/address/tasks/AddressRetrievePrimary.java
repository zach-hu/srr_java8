/*
 * Created on Dec 22, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.addressAddressRetrievePrimary.java
 */

package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;


public class AddressRetrievePrimary extends Task
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
			String addressType = (String ) incomingRequest.get("Address_addressType");

			String queryString = "from Address as Address where Address.id.addressType = ? and Address.id.addressCode = 'DEFAULT' ";
			List resultList = dbs.query(queryString, new Object[] {addressType } , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				ret = resultList.get(0);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		finally
		{
			return ret;
		}
	}
}
