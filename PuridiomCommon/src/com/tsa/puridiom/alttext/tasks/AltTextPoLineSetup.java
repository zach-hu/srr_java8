/**
 * 
 */
package com.tsa.puridiom.alttext.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AltTextPoLineSetup extends Task
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
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			String id = "";
			String itemNumber = "";
			String source = "";

			if (poLine != null)
			{
				itemNumber = poLine.getItemNumber();
				source = poLine.getItemSource();

				if (HiltonUtility.isEmpty(source))
				{
					source = "POL";
				}

				if (source.equals("INV"))
				{
					id = poLine.getItemLocation();

				} else if (source.equals("CAT") || source.equals("XML"))
				{
					id = poLine.getCatalogId();

				} else if (source.equals("POL"))
				{
					id = String.valueOf(poLine.getIcPoLine());
				}

			} else
			{
				itemNumber = (String) incomingRequest.get("PoLine_itemNumber");
				source = (String) incomingRequest.get("PoLine_itemSource");

				if (HiltonUtility.isEmpty(source))
				{
					source = "POL";
				}

				if (source.equals("INV"))
				{
					id = (String) incomingRequest.get("PoLine_itemLocation");

				} else if (source.equals("CAT") || source.equals("XML"))
				{
					id = (String) incomingRequest.get("PoLine_catalogId");

				} else if (source.equals("POL"))
				{
					id = (String) incomingRequest.get("PoLine_icPoLine");
				}
			}

			if (HiltonUtility.isEmpty(id))
			{
				id = "POL";
			}

			if (HiltonUtility.isEmpty(itemNumber))
			{
				itemNumber = " ";
			}

			incomingRequest.put("AltText_source", source);
			incomingRequest.put("AltText_id", id);
			incomingRequest.put("AltText_itemNumber", itemNumber);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "AltTextPoLineSetup error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

}