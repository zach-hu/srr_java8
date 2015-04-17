/*
 * Created on Nov 10, 2005
 *
 */

package com.tsa.puridiom.invoiceaddress.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;


public class InvoiceAddressRetrieveDefault extends Task
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
			String vendorId = (String ) incomingRequest.get("InvoiceAddress_vendorId");

			String queryString = "from InvoiceAddress as InvoiceAddress where InvoiceAddress.id.vendorId = ? and InvoiceAddress.id.addressCode = 'DEFAULT' ";
			List resultList = dbs.query(queryString, new Object[] {vendorId } , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				ret = resultList.get(0);
				incomingRequest.put("InvoiceAddress_addressCode", "DEFAULT");
				incomingRequest.put("InvoiceHeader_vendorAddrCode", "DEFAULT");
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
