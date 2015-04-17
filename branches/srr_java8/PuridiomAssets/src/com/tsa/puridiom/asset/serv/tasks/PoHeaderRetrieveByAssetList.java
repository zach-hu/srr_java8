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

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoHeaderRetrieveByAssetList extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return a poHeader according with his poNumber
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		PoHeader poHeader = new PoHeader();
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List assetList = (List) incomingRequest.get("assetList");
			if (assetList != null && assetList.size() > 0) {
				Asset asset = (Asset) assetList.get(0);
				String poNumber = asset.getPurchaseOrder();
				String queryString = "from PoHeader PoHeader where PoHeader.poNumber = ? ";
				List resultList = dbs.query(queryString, new Object[] {poNumber} , new Type[] { Hibernate.STRING}) ;
				if (resultList != null && resultList.size() > 0) {
				    poHeader = (PoHeader) resultList.get(0);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return poHeader;
	}
}
