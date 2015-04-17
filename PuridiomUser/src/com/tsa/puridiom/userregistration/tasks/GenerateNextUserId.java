package com.tsa.puridiom.userregistration.tasks;

import com.tsa.puridiom.userregistration.exception.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class GenerateNextUserId extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			String	userId = (String) incomingRequest.get("userId");
			int userIdCheckCount = 1;

			if (userId == null) {
				throw new RegistrationFatalException("User Id was not set.  Cannot generate next code.");
			}

			try {
				userIdCheckCount = ((Integer) incomingRequest.get("userIdCheckCount")).intValue();
			}
			catch (Exception e1) {
				// userIdCheckCount must not have been set... use default of 1
			}

			userId = userId.substring(0, 14) + String.valueOf(userIdCheckCount - 1);

			userIdCheckCount++;
			incomingRequest.put("userIdCheckCount", new Integer(userIdCheckCount));

			result = userId;
			this.status = Status.SUCCEEDED;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}