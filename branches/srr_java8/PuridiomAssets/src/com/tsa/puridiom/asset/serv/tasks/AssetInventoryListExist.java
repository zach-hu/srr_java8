/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetInventoryListExist extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return an asset list with a respective itemNumber
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
			String queryString = "from Asset Asset where Asset.itemNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {itemNumber} , new Type[] { Hibernate.STRING}) ;
			result = resultList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return result;
	}
}
