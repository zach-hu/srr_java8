/**
 * 
 */
package com.tsa.puridiom.alttext.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AltText;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AltTextSaveasSetup extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			AltText altText = (AltText) incomingRequest.get("altText");

			if (altText == null)
			{
				this.setStatus(Status.FAILED);

			} else
			{
				BigDecimal bdIcText = altText.getIcText();
				String newIcText = (String) incomingRequest.get("newDocText_icText");

				if (HiltonUtility.isEmpty(newIcText))
				{
					newIcText = bdIcText.toString();
				}

				incomingRequest.put("newAltText_icText", newIcText);

				this.setStatus(Status.SUCCEEDED);
			}

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "AltTextSaveasSetup error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}