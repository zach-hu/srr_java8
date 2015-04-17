package com.tsa.puridiom.alttext.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class AltTextCreateDocTextSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String id = (String) incomingRequest.get("AltText_id");
            String itemNumber = (String) incomingRequest.get("AltText_itemNumber");
            String icLine = (String) incomingRequest.get("AltText_icLine");
            StringBuffer idReference = new StringBuffer("ALT|");

            if (!Utility.isEmpty(icLine) && !icLine.equals("0")) {
                idReference.append(icLine);
            } else {
                idReference.append(id);
            }
            idReference.append("|" + itemNumber);

			incomingRequest.put("DocText_icText", incomingRequest.get("AltText_icText"));
            incomingRequest.put("DocText_referenceType", "ALT");
            incomingRequest.put("DocText_idReference", idReference.toString());

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
