/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetservice.serv.tasks;

import java.util.Map;
import java.math.BigDecimal;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AssetServiceNewSequence extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			BigDecimal assetServiceLastSequence = (BigDecimal) incomingRequest.get("assetServiceLastSequence");
			BigDecimal assetServiceNextSequence = assetServiceLastSequence.add(new BigDecimal(1));
			ret = assetServiceNextSequence.toString();
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
