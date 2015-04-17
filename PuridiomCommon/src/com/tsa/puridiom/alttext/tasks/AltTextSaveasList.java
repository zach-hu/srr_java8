/**
 * 
 */
package com.tsa.puridiom.alttext.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.AltText;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AltTextSaveasList extends Task
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
			List altTextList = (List) incomingRequest.get("altTextList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("alttext-saveas-line.xml");

			if (altTextList != null)
			{
				for (int row = 0; row < altTextList.size(); row++)
				{
					AltText altText = (AltText) altTextList.get(row);
					String icText = "0";

					if (altText.getIcText() != null)
					{
						icText = altText.getIcText().toString();
					}

					incomingRequest.put("DocText_icText", icText);
					incomingRequest.put("altText", altText);

					process.executeProcess(incomingRequest);

					if (process.getStatus() != Status.SUCCEEDED)
					{
						throw new Exception("AltText save as process failed (alttext-saveas-line.xml).");
					}

					altText = (AltText) incomingRequest.get("altText");
					altTextList.set(row, altText);
				}
			}
			
			result = altTextList;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "AltTextSaveasList error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}