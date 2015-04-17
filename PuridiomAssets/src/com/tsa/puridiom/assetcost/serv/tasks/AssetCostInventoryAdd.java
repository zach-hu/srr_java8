/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetcost.serv.tasks;

import java.util.Map;
import java.util.List;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetCostInventoryAdd extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetCostList = null;
		try
		{
			assetCostList = (List) incomingRequest.get("assetCostList");
			for (int i=0; i<assetCostList.size(); i++) {
				AssetCost assetCost = (AssetCost) assetCostList.get(i);
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				dbs.add(assetCost);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetCostList;
	}
}
