/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetListAdd extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return the asset list that were add
	 *
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetList = new ArrayList();
		try
		{
			assetList = (List) incomingRequest.get("assetList");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			if (assetList.size() > 1) {
				for(int i=1; i<assetList.size(); i++)
				{
					Asset asset= (Asset) assetList.get(i);
					dbs.add(asset);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetList;
	}
}
