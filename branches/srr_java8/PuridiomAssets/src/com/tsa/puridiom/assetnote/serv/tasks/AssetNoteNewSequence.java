/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetnote.serv.tasks;

import java.util.Map;
import java.math.BigDecimal;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AssetNoteNewSequence extends Task
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
			BigDecimal assetNoteLastSequence = (BigDecimal) incomingRequest.get("assetNoteLastSequence");
			BigDecimal assetNoteNextSequence = assetNoteLastSequence.add(new BigDecimal(1));
			ret = assetNoteNextSequence.toString();
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
