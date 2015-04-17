package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.supplierportal.exception.RegistrationException;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ThrowInvalidOrganizationIdException extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		this.status = Status.FAILED;
		throw new RegistrationException ("You have entered an invalid Organization Id.");			

		//return result;
	}
}