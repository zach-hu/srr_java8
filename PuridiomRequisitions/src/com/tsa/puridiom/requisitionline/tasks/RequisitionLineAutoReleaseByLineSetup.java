/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Administrator
 */
public class RequisitionLineAutoReleaseByLineSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		RequisitionLineAutoReleaseObject autoReleaseObject = (RequisitionLineAutoReleaseObject)incomingRequest.get("autoReleaseObject");
		try
		{
			if (autoReleaseObject != null)
			{
				String icHeader = autoReleaseObject.getIcReqHeader().toString() ;
				String icLine = autoReleaseObject.getIcReqLine().toString() ;

				if (Utility.isEmpty(icHeader))
				{
					this.setStatus(Status.FAILED);
				}
				else
				{
					incomingRequest.put("BillTo_icHeader",icHeader) ;
					incomingRequest.put("BillTo_icLine",icLine) ;
					incomingRequest.put("ShipTo_icHeader",icHeader) ;
					incomingRequest.put("ShipTo_icLine",icLine) ;
					incomingRequest.put("Account_icHeader",icHeader) ;
					incomingRequest.put("Account_icLine", autoReleaseObject.getRequistionLine().getIcAccount().toString()) ;
					incomingRequest.put("DocComment_icHeader",icHeader) ;
					incomingRequest.put("DocComment_icLine",icLine) ;
					incomingRequest.put("requisitionLine", autoReleaseObject.getRequistionLine()) ;
				}
				this.setStatus(Status.SUCCEEDED) ;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("REquisitionLine AutoRelease Setup by Line failed.", e);
		}

		return null ;
	}
}
