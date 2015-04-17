package com.tsa.puridiom.common.tasks;

import com.tsa.puridiom.exceptions.InvalidEmailAddressException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailAddressValidation extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    String	organizationId = (String) incomingRequest.get("organizationId");
			String	mailId = (String) incomingRequest.get("newMailId");

			if (Utility.isEmpty(organizationId)) {
			    organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			}

			List exceptions = new ArrayList();

            if (Utility.isEmpty(mailId)) {
			    exceptions.add("The email address cannot be empty!");
			} else {
                String[]    validDomains    = PropertiesManager.getInstance(organizationId).getProperty("LOGIN", "ValidDomain", ".com;.edu;.net;.org;.gov;.mil").split(";");
                String defaultInvalidDomainStr = "adelphia;anonymbox;aolcomcast;erols;gmail;gmx;hotmail;juno;lavabit;lycos;mail;netscape;netzero;onlinkpr;peoplepc;rocketmail;verizon;woomail;yahoo;ymail;";
                String[]    invalidDomains    = PropertiesManager.getInstance(organizationId).getProperty("LOGIN", "InvalidDomain", defaultInvalidDomainStr).split(";");
                String mailIdDomain = "";
                int iat = mailId.indexOf("@");
                int idomain = -1;

                if (iat <= 0) {
                    exceptions.add("The email address must include a prefix followed by the @ sign!");
                } else {
                	mailIdDomain = mailId.substring(iat);
                }

                /****************************
    			 * Check for invalid domains
    			 ***************************/
    			if (invalidDomains != null && invalidDomains.length > 0) {
                    for (int i = 0; i < invalidDomains.length; i++) {
                        if (mailIdDomain.indexOf(invalidDomains[i]) >= 0) {
                            exceptions.add("Please use your corporate email.  We do not accept \'" + invalidDomains[i] + "\' email addresses!");
                            break;
                        }
                    }
                }

    			if (validDomains != null && validDomains.length > 0) {
                    for (int i = 0; i < validDomains.length; i++) {
                        if (mailIdDomain.indexOf(validDomains[i]) >= 0) {
                            idomain = mailId.indexOf(validDomains[i]);
                            break;
                        }
                    }

                    if (idomain < 0) {
                        exceptions.add("The email address must include a valid domain!");
                    } else if (idomain == 0) {
                        exceptions.add("The email address must include a valid domain following the @ sign!");
                    }
                }
            }

			if (exceptions.size() > 0) {
			    StringBuffer msg = new StringBuffer();
			    for (int i=0; i < exceptions.size(); i++) {
			        msg.append((String) exceptions.get(i) + "<br>");
			    }
			    throw new InvalidEmailAddressException(msg.toString());
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