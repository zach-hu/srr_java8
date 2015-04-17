/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class AssetGetClonesQty extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return the asset quantity that were generated
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String Asset_number = "0";
		try
		{
			String Asset_cloneQty = (String) incomingRequest.get("Asset_cloneQty");
			if (Utility.isEmpty(Asset_cloneQty)) {
				this.setStatus(Status.FAILED);
				throw new TsaException("Number of Asset is necessary to save assets");
			}
			else
			{
				this.setStatus(Status.SUCCEEDED);
				Asset_number = Asset_cloneQty;
			}
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return Asset_number;
	}
}
