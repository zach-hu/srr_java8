/**
 * 
 */
package com.tsa.puridiom.alttext.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AltText;
import com.tsa.puridiom.entity.AltTextPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AltTextSaveas extends Task
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
			AltTextAdd addTask = new AltTextAdd();
			AltTextPK comp_id = new AltTextPK();
			AltText originalAltText = (AltText) incomingRequest.get("altText");
			AltText altText = new AltText();
			String icLine = (String) incomingRequest.get("newAltText_id");
			String icText = (String) incomingRequest.get("newAltText_icText");
			String source = (String) incomingRequest.get("newAltText_source");

			comp_id.setId(icLine);
			comp_id.setItemNumber(originalAltText.getComp_id().getItemNumber());
			comp_id.setLanguage(originalAltText.getComp_id().getLanguage());

			if (!HiltonUtility.isEmpty(source))
			{
				comp_id.setSource(source);

			} else
			{
				comp_id.setSource(originalAltText.getComp_id().getSource());
			}

			altText.setComp_id(comp_id);
			altText.setIcText(new BigDecimal(icText));

			incomingRequest.put("altText", altText);

			altText = (AltText) addTask.executeTask(incomingRequest);

			result = altText;

			this.setStatus(addTask.getStatus());

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "AltTextSaveas error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}