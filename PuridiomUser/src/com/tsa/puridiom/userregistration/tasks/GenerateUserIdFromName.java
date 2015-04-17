package com.tsa.puridiom.userregistration.tasks;

import com.tsa.puridiom.userregistration.exception.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class GenerateUserIdFromName extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			String firstName = (String) incomingRequest.get("UserProfile_firstName");
			String lastName = (String) incomingRequest.get("UserProfile_lastName");
			String userId = "";

			if (Utility.isEmpty(firstName)) {
				throw new RegistrationException("First Name cannot be empty.");
			}
			if (Utility.isEmpty(lastName)) {
				throw new RegistrationException("Last Name cannot be empty.");
			}

			firstName = firstName.toUpperCase();
			lastName = lastName.toUpperCase();

			int len = firstName.length();
			String test;

			for (int n = 0; n < len; n++) {
				test = firstName.substring(n, n + 1);

				if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(test) < 0) {
					firstName = firstName.substring(0, n) + "" + firstName.substring(n + 1, firstName.length());
					len--;
				}
			}

			len = lastName.length();

			for (int n = 0; n < len; n++) {
				test = lastName.substring(n, n + 1);

				if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(test) < 0) {
					lastName = lastName.substring(0, n) + "" + lastName.substring(n + 1, lastName.length());
					len--;
				}
			}

			userId = firstName.substring(0,1) + lastName;

			userId = userId.toUpperCase();

			if (userId.length() > 15) {
				userId = userId.substring(0, 15);
			} else  if (userId.length() < 15) {
				userId = userId + "0000000000000";
				userId = userId.substring(0, 15);
			}

			incomingRequest.put("HostUser_userId", userId);
			incomingRequest.put("UserProfile_userId", userId);
			incomingRequest.put("UserGroupRel_userId", userId);
			result = userId;

			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}