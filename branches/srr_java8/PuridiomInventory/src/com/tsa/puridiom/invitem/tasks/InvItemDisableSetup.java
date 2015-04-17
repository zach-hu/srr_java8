/*
 * Created on Aug 31, 2005
 */
package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;
import com.tsa.puridiom.common.documents.GeneralStatus;

/**
 * @author Kelli
 */
public class InvItemDisableSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{

			incomingRequest.put("InvItem_status", GeneralStatus.STATUS_ON_HOLD) ;
			incomingRequest.put("ItemCrossRef_status", GeneralStatus.STATUS_ON_HOLD) ;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
