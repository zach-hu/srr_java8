package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.exception.RegistrationException;
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