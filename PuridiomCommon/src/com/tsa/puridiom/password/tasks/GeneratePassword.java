package com.tsa.puridiom.password.tasks;

import com.tsa.puridiom.password.RandomPassword;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class GeneratePassword extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    String	organizationId = (String) incomingRequest.get("organizationId");
			if (Utility.isEmpty(organizationId)) {
			    organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			}
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			int	passwordLength	= new Integer(propertiesManager.getProperty("MISC", "PassLength", "8")).intValue();
			int	minDigit	= new Integer(propertiesManager.getProperty("MISC", "MinDigit", "1") ).intValue();
			int	minNonalpha = new Integer(propertiesManager.getProperty("MISC", "MinNonAlpha", "1") ).intValue();
			boolean caseSensitive = propertiesManager.getProperty("MISC","PASSCASESENSITIVE","N").equals("Y");

			if (passwordLength <= 0) {
				passwordLength = 6;
			}

			int mask = RandomPassword.LOWERS;
			if (minDigit > 0) {
				mask = mask + RandomPassword.DIGITS;
			}
			if (minNonalpha > 0) {
				mask = mask + RandomPassword.OTHERS;
			}
			if (caseSensitive) {
				mask = mask + RandomPassword.UPPERS;
			}
			RandomPassword randomPassword = new RandomPassword(passwordLength, mask);
			result = randomPassword.getNext();

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}