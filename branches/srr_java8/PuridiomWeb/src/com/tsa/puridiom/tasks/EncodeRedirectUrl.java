package com.tsa.puridiom.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;
import javax.servlet.http.*;

public class EncodeRedirectUrl extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	redirectUrl = (String) incomingRequest.get("redirectUrl");
			HttpServletResponse response = (HttpServletResponse) incomingRequest.get("HttpServletResponse");
			
			result = response.encodeRedirectUrl(redirectUrl);

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