package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.supplierportal.RegisterUser;
import com.tsa.puridiom.supplierportal.exception.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class RegisterUserVendorNameStrip extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");

			if (registerUser == null) {
				throw new RegistrationException("Vendor registration information was not found.");
			}

			String name = registerUser.getVendorName();
			String checkFor = "";
			String replaceWith = "";
			String phrases[][] = {{" THE",""}, {"THE ",""}, {" INC.",""}, {" INC", ""}, {"INCORPORATED", ""}};
			int rows = phrases.length;

			//jdoCheck.importFile(file);
			//rows = jdoCheck.getRowCount();

			for (int n = 0; n < rows; n++) {
			//	checkfor = jdoCheck.getValue("check_for", n);
			//	replaceWith = jdoCheck.getValue("replace_with", n).toUpperCase();

				checkFor = phrases[n][0];
				replaceWith = phrases[n][1];
			
				if (Utility.isEmpty(replaceWith)) {
					replaceWith = " ";
				}
			
				name = Utility.searchAndReplace(name, checkFor, replaceWith, true).trim();
			}
		
			name = Utility.searchAndReplace(name, "  ", " ", true).trim();
		
			result = name;
			
			//system.out.println("Return code: " + result);
			
			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}