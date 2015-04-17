/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator
 */
public class AccountUpdateSetValues extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        try
        {
			String accountFLD2 = (String)incomingRequest.get("accountFLD2") ;

			if (! Utility.isEmpty(accountFLD2)) {
				incomingRequest.put("Account_fld2",accountFLD2) ;
				incomingRequest.put("Account_sequence","1");
			}

			this.setStatus(Status.SUCCEEDED);

        }
        catch (Exception e) {
        	this.setStatus(Status.FAILED);
        	Log.error(this,"Error on AccountUpdateSetupbyXrefCombo");
			e.printStackTrace();
        	throw e;
		}
		return null  ;
	}

}
