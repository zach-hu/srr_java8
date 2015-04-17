package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class ChangePasswordValidationSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
		    String	password = Utility.ckNull((String) incomingRequest.get("password"));
			String	newPassword = (String) incomingRequest.get("newPassword");
		    
		    if (Utility.isEmpty(organizationId)) {
			    organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			}
			
			// Previously encrypted for comparison against current password
			//		Now needs to be decrypted for validation
			incomingRequest.put("password", BlackBox.getDecrypt(password));
			incomingRequest.put("loginId", (String) incomingRequest.get("mailId"));
			
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
			String	priorPassword = "";

			if (userProfile != null) {
			    priorPassword = userProfile.getPriorPassword();
			}
			
			incomingRequest.put("priorPassword", priorPassword);
			
			if (!Utility.isEmpty(newPassword)) {
			    // Encrypt the new password to be used in comparing prior password(s)
			    newPassword = BlackBox.getEncrypt(newPassword);
				if (priorPassword.indexOf(newPassword) != 0) {
				    String updatePriorPassword = newPassword + " " + priorPassword;
				    incomingRequest.put("UserProfile_priorPassword", updatePriorPassword);
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