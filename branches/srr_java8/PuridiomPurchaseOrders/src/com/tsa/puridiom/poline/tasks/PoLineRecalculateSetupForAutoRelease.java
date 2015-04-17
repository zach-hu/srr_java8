/**
 *
 */
package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny Zapana
 */
public class PoLineRecalculateSetupForAutoRelease extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		String recalculate = "";

		try
		{
			recalculate = "Y";

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An error ocurred Calculating totals");
		}

		return recalculate;
	}
}
