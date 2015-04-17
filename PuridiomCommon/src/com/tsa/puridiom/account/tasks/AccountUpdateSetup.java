/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator
 */
public class AccountUpdateSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        String userId = (String)incomingRequest.get("userId") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        this.setStatus(Status.SUCCEEDED) ;

		String refType = (String)incomingRequest.get("Default_referenceType") ;
		if (! Utility.isEmpty(refType)) {
			incomingRequest.put("Account_accountType",refType) ;
		}

		if (Utility.isEmpty((String)incomingRequest.get("Account_allocPercent"))) {
			incomingRequest.put("Account_allocPercent","0") ;
		}
		if (Utility.isEmpty((String)incomingRequest.get("Account_allocAmount"))) {
			incomingRequest.put("Account_allocAmount","0") ;
		}
		if (Utility.isEmpty((String)incomingRequest.get("Account_dateEntered"))) {
			incomingRequest.put("Account_dateEntered",Dates.today("", userTimeZone)) ;
		}
		if (Utility.isEmpty((String)incomingRequest.get("Account_dateExpired"))) {
			incomingRequest.put("Account_dateExpired",Dates.today("", userTimeZone)) ;
		}
		if (Utility.isEmpty((String)incomingRequest.get("Account_allocQty"))) {
			incomingRequest.put("Account_allocQty","0") ;
		}
		if (Utility.isEmpty((String)incomingRequest.get("Account_recQty"))) {
			incomingRequest.put("Account_recQty","0") ;
		}
		if (Utility.isEmpty((String)incomingRequest.get("Account_owner"))) {
			incomingRequest.put("Account_owner",userId) ;
		}

		return null  ;
	}

}
