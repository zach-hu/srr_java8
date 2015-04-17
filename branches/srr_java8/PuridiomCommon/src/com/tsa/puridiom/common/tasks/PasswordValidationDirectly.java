package com.tsa.puridiom.common.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.exceptions.PasswordException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordValidationDirectly extends Task {
    
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    String	organizationId = (String) incomingRequest.get("organizationId");
			String	password = (String) incomingRequest.get("newPassword");
			String	confirmPassword = (String) incomingRequest.get("confirmPassword");
			//String	currentPassword = (String) incomingRequest.get("password");
			String	priorPassword = (String) incomingRequest.get("priorPassword");
			String	temp = password;
			
			if (Utility.isEmpty(organizationId)) {
			    organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			}
			
			password = Utility.ckNull(password);
			confirmPassword = Utility.ckNull(confirmPassword);
			//currentPassword = Utility.ckNull(currentPassword);
			priorPassword = Utility.ckNull(priorPassword);
			temp = Utility.ckNull(temp);
			
			boolean requireNew = PropertiesManager.getInstance(organizationId).getProperty("MISC", "NewPass", "N" ).equals("Y");
			int	passwordLength	= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassLength", "1" )).intValue();
			int	minDigit	= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC", "MinDigit", "0") ).intValue();
			int	minNonalpha = new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC", "MinNonAlpha", "0") ).intValue();
			int	priorPassCheck = new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC", "PriorPass", "0") ).intValue();
			List passwordExceptions = new ArrayList();

			temp = Utility.numericFilter(password);
			
			if ( Utility.isEmpty(password) ) {
			    passwordExceptions.add("The password cannot be empty!");
			}
			if ( Utility.isEmpty(confirmPassword) ) {
			    passwordExceptions.add("You must confirm your new password!");
			}
			if ( !password.equals(confirmPassword)) {
			    passwordExceptions.add("New password and confirm password do not match!");
			}
			if ( requireNew || (priorPassCheck > 0)) {
			    // Compare new password against current decrypted password
			    // Compare new encrypted password against prior passwords (stored as encrypted) - only include passwords based on priorPassCheck (separated by a space)
			    if (priorPassCheck > 0) {
			        StringBuffer priorPasswordTemp = new StringBuffer();
			        int itemp = 0;
			        int ispace = priorPassword.indexOf(" ");
			        
			        while(ispace > 0 && itemp < priorPassCheck) {
			            priorPasswordTemp.append(priorPassword.substring(0, ispace) + " ");
			            priorPassword = priorPassword.substring(ispace + 1);
			            ispace = priorPassword.indexOf(" ");
			            itemp++;
			        }
			        
			        if (ispace < 0 && itemp < priorPassCheck && !Utility.isEmpty(priorPassword)) {
			            priorPasswordTemp.append(priorPassword + " ");
			        }
			        priorPassword = priorPasswordTemp.toString();
			    } else {
			        priorPassword = "";
			    }
			    
			    String encryptedPassword = BlackBox.getEncrypt(password);
			    
			    //if (password.equalsIgnoreCase(currentPassword) || (priorPassword.indexOf(encryptedPassword) >= 0)) {
			      //  passwordExceptions.add("You must enter a new password.  This password was previously used!");
			    //}
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
			
			if (passwordExceptions.size() > 0) {
			    StringBuffer msg = new StringBuffer();
			    for (int i=0; i < passwordExceptions.size(); i++) {
			        msg.append((String) passwordExceptions.get(i) + "<br>");
			    }
			    throw new PasswordException(msg.toString());
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}