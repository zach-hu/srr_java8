package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class InvItemAddSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			// Create new ic codes
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Long id = ukg.getUniqueKey();
			incomingRequest.put("InvItem_icText", id.toString());
			incomingRequest.put("DocComment_icHeader", id.toString());
			incomingRequest.put("DocAttachment_icHeader", id.toString());
			incomingRequest.put("historyStatus", "CREATE");

			InvItem invItem = new InvItem();
			result = invItem;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}