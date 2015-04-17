package com.tsa.puridiom.resetpasswordlink.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ResetPasswordLink;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.Map;

public class ResetPasswordLinkSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String oid = (String) incomingRequest.get("organizationId");
		String userDateFormat = (String) incomingRequest.get("userDateFormat");
        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
        }
        
		try		
		{
			ResetPasswordLink  resetPasswordLink = (ResetPasswordLink) incomingRequest.get("resetPasswordLink");
			if (resetPasswordLink == null)
			{
				resetPasswordLink = new ResetPasswordLink();
			}

			if (incomingRequest.containsKey("ResetPasswordLink_icLink"))
			{
				String icLinkString = (String) incomingRequest.get("ResetPasswordLink_icLink");
				if (Utility.isEmpty(icLinkString))
				{
					icLinkString = "0";
				}
				BigDecimal icLink = new BigDecimal (icLinkString);
				resetPasswordLink.setIcLink(icLink);
			}
			if (incomingRequest.containsKey("ResetPasswordLink_userId"))
			{
				String uid = (String ) incomingRequest.get("ResetPasswordLink_userId");
				resetPasswordLink.setUserId(uid);
			}
			if (incomingRequest.containsKey("ResetPasswordLink_mailId"))
			{
				String mailId = (String) incomingRequest.get("ResetPasswordLink_mailId");
				resetPasswordLink.setMailId(Utility.ckNull(mailId).toLowerCase());
			}
            if (incomingRequest.containsKey("ResetPasswordLink_used"))
            {
                String used = (String ) incomingRequest.get("ResetPasswordLink_used");
                resetPasswordLink.setUsed(used);
            }
			if (incomingRequest.containsKey("ResetPasswordLink_linkDate"))
			{
				//This is an exception because we won't use userDateFormat here but it is not correct
				//String linkDateString = (String) incomingRequest.get("ResetPasswordLink_linkDate");
				//Date linkDate = Dates.getSqlDate(linkDateString, userDateFormat);
				java.util.Date linkDate = (java.util.Date) incomingRequest.get("ResetPasswordLink_linkDate");
				resetPasswordLink.setLinkDate(linkDate);
			}      

			result = resetPasswordLink;
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
