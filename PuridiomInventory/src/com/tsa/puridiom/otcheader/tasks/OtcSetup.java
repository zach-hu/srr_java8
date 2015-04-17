/*
 * Created on Jun 11, 2004
 *
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.disbheader.tasks.OtcSetup.java
 * 
 */
package com.tsa.puridiom.otcheader.tasks;

import java.util.Calendar;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;


public class OtcSetup extends Task
{

	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String ic = ukg.getUniqueKey().toString();
			incomingRequest.put("DisbHeader_icDsbHeader", ic);
			incomingRequest.put("DisbHeader_icHeaderHistory", ic);

			String userid =(String) incomingRequest.get("userId");
			incomingRequest.put("DisbHeader_owner", userid);
			incomingRequest.put("DisbHeader_lastChgBy", userid);
			incomingRequest.put("DisbHeader_disbType", "O");
			incomingRequest.put("DisbHeader_status", DocumentStatus.INV_INPROGRESS);
			Calendar cal = Calendar.getInstance();
			int iYear = cal.get(Calendar.YEAR);
			
			incomingRequest.put("DisbHeader_fiscalYear", String.valueOf(iYear));
			
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
