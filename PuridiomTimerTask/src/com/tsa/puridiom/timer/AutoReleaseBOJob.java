package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

/**
 * Job that will read Blanket Order Schedules and automatically create Release Orders 
 * in Order Pending status when PO_HEADER.AUTO_RELEASE ='Y' and Blanket has not expired.
 * 
 * @author Alexander Richard Angulo Kcana
 *
 */
public class AutoReleaseBOJob extends ScheduledJob 
{
	/**
	 * 
	 */
	public void execute() 
	{
		this.getReqLinesToExecute();
		Log.info(this, "job done");
	}
	
	/**
	 * Retrieve list of Blanket Orders(BO) with PO_HEADER.AUTO_RELEASE ='Y' and BO has 
	 * not expired to create Release Order. 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void getReqLinesToExecute() 
	{
		Log.debug(this, "retrieve list of Blanket Orders(BO) with PO_HEADER.AUTO_RELEASE ='Y' and BO has not expired.");
		try 
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("poheader-retrieve-for-autoareleasebo.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("userId", "PS-ARBO");
			incomingRequest.put("isAutoReleaseBO", "Y");

			process.executeProcess(incomingRequest);
		} 
		catch (Exception e)
		{
			Log.error(this, "getRequisitionsToExecute failed: " + e.getMessage());
		}

	}
}
