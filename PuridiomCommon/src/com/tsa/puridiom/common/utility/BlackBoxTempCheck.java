package com.tsa.puridiom.common.utility;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class BlackBoxTempCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
            String property = "EAP";

            if (this.applicationName.equalsIgnoreCase("vendor-registration") || this.applicationName.equalsIgnoreCase("SupplierPortal")) {
                property = "SEAP";
            }
		    String	x = PropertiesManager.getInstance(organizationId).getProperty("MISC", property, "");
		    String	p = (String) incomingRequest.get("p");

		    if (Utility.isEmpty(p) || Utility.isEmpty(x))
		    {
		        result = Boolean.FALSE;
		    }
		    else
		    {
			    p = BlackBox.getEncrypt(p.toUpperCase());

				if (p.equals(x))
				{
				    result = Boolean.TRUE;
				}
				else
				{
				    result = Boolean.FALSE;
				}
		    }

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