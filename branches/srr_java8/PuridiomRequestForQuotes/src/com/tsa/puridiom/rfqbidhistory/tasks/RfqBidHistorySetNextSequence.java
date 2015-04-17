/**
 * Created on Jan 6, 2004
 * @author renzo
 * com.tsa.puridiom.rfqbidhistory.tasks.RfqBidHistorySetNextSequence.java
 */

package com.tsa.puridiom.rfqbidhistory.tasks;

import java.util.Map;
import java.math.BigDecimal;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqBidHistorySetNextSequence extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * it increases bidLastSequence in one, and return the new bidLastSequence
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			BigDecimal lastSequence = (BigDecimal) incomingRequest.get("RfqBidHistory_lastSequence");
			BigDecimal nextSequence = lastSequence.add(new BigDecimal(1));
			String sequence = nextSequence.toString();
			ret = sequence;
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
