package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
/**
 *
 * @author mdanz
 *
 */
public class PoHeaderCompareRevisions extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;
		boolean revisionCurrent = true;

		try
		{
			BigDecimal maxRevision = HiltonUtility.ckNull((BigDecimal)incomingRequest.get("maxRevision"));
			BigDecimal currentRevision = HiltonUtility.ckNull((BigDecimal)incomingRequest.get("PoHeader_revisionNumber"));

			if (maxRevision.compareTo(currentRevision) != 0)
			{
				revisionCurrent = false;
			}

			incomingRequest.put("revisionCurrent", String.valueOf(revisionCurrent));
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "Problem comparing revisions of the PO");
			e.printStackTrace();
			throw e;
		}

		return result;
	}
}
