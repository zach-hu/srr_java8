package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class ChangePasswordDirectlyValidationSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
			String	newPassword = (String) incomingRequest.get("newPassword");

		    if (Utility.isEmpty(organizationId)) {
			    organizationId = (String) incomingRequest.get("organizationId");
			}

			// Previously encrypted for comparison against current password
			//		Now needs to be decrypted for validation
		    incomingRequest.put("loginId", (String) incomingRequest.get("mailId"));

			VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");
			Contact	contact = (Contact) incomingRequest.get("contact");
			String	priorPassword = "";

			if (vendorRegister != null) {
			    priorPassword = vendorRegister.getPriorPassword();
			}

			if (contact != null) {
				priorPassword = contact.getPriorPassword();
			}

			incomingRequest.put("priorPassword", priorPassword);

			if (!Utility.isEmpty(newPassword)) {
			    // Encrypt the new password to be used in comparing prior password(s)
			    newPassword = BlackBox.getEncrypt(newPassword);
				if (priorPassword.indexOf(newPassword) != 0) {
				    String updatePriorPassword = newPassword + " " + priorPassword;
				    incomingRequest.put("VendorRegister_priorPassword", updatePriorPassword);
				    incomingRequest.put("Contact_priorPassword", updatePriorPassword);
				}
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}