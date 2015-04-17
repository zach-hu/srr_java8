/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny Zapana
 */
public class AutoreleaseByShipToJob extends ScheduledJob
{
	public void execute()
	{
		Log.debug(this, "AutoreleaseByShipToJob starts");

		this.getReqLinesToExecute();

		Log.debug(this, "AutoreleaseByShipToJob done");
	}

	public void getReqLinesToExecute()
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("requisitionline-retrieve-for-autorelease-by-shipto.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());

			process.executeProcess(incomingRequest);

		} catch (Exception e)
		{
			e.printStackTrace();

			Log.error(this, "getReqLinesToExecute failed: " + e.getMessage());
		}

	}

}
