package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.exceptions.PasswordException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.property.PropertiesManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SupplierPasswordValidation extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    String	organizationId = (String) incomingRequest.get("RegisterUser_organizationId");
			String	password = BlackBox.getDecrypt( (String) incomingRequest.get("RegisterUser_contactPassword"));
			String	confirmPassword = (String) incomingRequest.get("confirm_password");
			boolean caseSensitive = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassCaseSensitive", "N" ).equals("Y");
			String  encNewPassword = BlackBox.getEncrypt(password);

			if (!caseSensitive) {
				confirmPassword = confirmPassword.toUpperCase();
			}

			String	temp = password;

			if (Utility.isEmpty(organizationId)) {
			    organizationId = (String) incomingRequest.get("RegisterUser_organizationId");
			}

			password = Utility.ckNull(password);
			confirmPassword = Utility.ckNull(confirmPassword);
			temp = Utility.ckNull(temp);

			int	passwordLength	= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassLength", "1" )).intValue();
			int	minDigit	= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC", "MinDigit", "0") ).intValue();
			int	minNonalpha = new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC", "MinNonAlpha", "0") ).intValue();
			List passwordExceptions = new ArrayList();
			int minUpper	= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC","MINPASSUPPER","0") ).intValue();
			int minLower	= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC","MINPASSLOWER","0") ).intValue();

			temp = Utility.numericFilter(password);

			if ( Utility.isEmpty(password) ) {
			    passwordExceptions.add("The new password cannot be empty!");
			}
			if ( Utility.isEmpty(confirmPassword) ) {
			    passwordExceptions.add("You must confirm your new password!");
			}
			if ( !password.equals(confirmPassword)) {
			    passwordExceptions.add("New password and confirm password do not match!");
			}
			if ( passwordLength > 0 && password.length() < passwordLength) {
				passwordExceptions.add("Password must be at least " + passwordLength + " characters!");
			}
			if ( (Utility.isEmpty(password) || !Utility.isAlphaChar(password.charAt(0))) && PropertiesManager.getInstance(organizationId).getProperty("MISC", "MustBeginAlphaCharacters", "Y" ).equalsIgnoreCase("Y") ) {
			    passwordExceptions.add("Password must begin with an alphabetic character!");
			}
			if ( (minDigit > 0) && (temp.length() < minDigit) ) {
			    passwordExceptions.add("Password must contain at least " + minDigit + " digit(s)!");
			}
			if ( Utility.isEmpty(password) || (Utility.nonAlphanumericFilter(password).length() < minNonalpha) ) {
			    passwordExceptions.add("Password must contain at least " + minNonalpha + " non-alphanumeric character(s)!");
			}

			String	loginId  = Utility.ckNull((String) incomingRequest.get("userLoginId"));

			int ind = loginId.indexOf("@");
			if (ind > 0) {
			    loginId = loginId.substring(0, ind).toLowerCase();
			    password = password.toLowerCase();
			    ind = password.indexOf(loginId);
			    if (ind >= 0) {
			        passwordExceptions.add("Password cannot contain part of the Login Id [" + loginId + "]!");
			    } else {
			        StringBuffer loginIdBk = new StringBuffer();
			        for (int i=loginId.length(); i > 0; i--) {
			            loginIdBk.append(loginId.substring(i - 1, i));
			        }
			        ind = password.indexOf(loginIdBk.toString());
				    if (ind >= 0) {
				        passwordExceptions.add("Password cannot contain part of your Login Id [" + loginIdBk.toString() + "]!");
				    }
			    }
			}

			if (caseSensitive) {
				String	upper = "";
				String  lower = "";
				for (int i = 0; i < password.length(); i++) {
				    char thisChar = password.charAt(i);
				    if (thisChar >= 65 && thisChar <= 90) {
				    	upper += thisChar;
				    } else if (thisChar >= 97 && thisChar <= 122) {
				    	lower += thisChar;
				    }
				}

				if (upper.length() < minUpper){
				    passwordExceptions.add("Password must contain at least " + minUpper + " uppercase character(s)!");
				}
				if (lower.length() < minLower){
				    passwordExceptions.add("Password must contain at least " + minLower + " lowercase character(s)!");
				}
			}

			if (passwordExceptions.size() > 0) {
			    StringBuffer msg = new StringBuffer();
			    for (int i=0; i < passwordExceptions.size(); i++) {
			        msg.append((String) passwordExceptions.get(i) + "<br>");
			    }
			    throw new PasswordException(msg.toString());
			}

			incomingRequest.put("confirm_password", confirmPassword);

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}