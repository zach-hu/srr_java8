/**
 * 
 * Created on Jan 16, 2004
 * @author renzo
 * com.tsa.puridiom.taxcode.tasks.TaxCodeManualAddSetup.java
 * 
 */
package com.tsa.puridiom.taxcode.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class TaxCodeManualAddSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		incomingRequest.put("TaxCode_taxCode", "PA");
		incomingRequest.put("TaxCode_taxRate", "6");
		return null;
	}

}
