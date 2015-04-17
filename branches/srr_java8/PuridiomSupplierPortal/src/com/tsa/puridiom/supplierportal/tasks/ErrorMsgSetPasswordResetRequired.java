package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;
import java.text.DateFormat;
import java.util.Date;
import com.tsagate.foundation.utility.Dates;

public class ErrorMsgSetPasswordResetRequired extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
		    BidBoardUser bidboardUser = (BidBoardUser) incomingRequest.get("bidboardUser");
			int		daysToChange	= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC","DAYSVALID","0")).intValue();
		    if (daysToChange != 0){
		    	int		warningDays		= new Integer(PropertiesManager.getInstance(organizationId).getProperty("MISC","PASSEXPWARNINGDAYS","0")).intValue();
				String 	passChanged		= bidboardUser.getPassChanged();
				String	login			= Dates.today("yyyy-MM-dd", "");
				Date	passwordChanged = new Date();
				Date	loginDate		= new Date();
				Dates	date			= new Dates();

				loginDate = date.getDate(login, "yyyy-MM-dd");
				passwordChanged = date.getDate(passChanged, "yyyy-MM-dd");
				int	difference	= date.getDateDifference(passwordChanged, loginDate);

				if (daysToChange > difference){
					difference = daysToChange - difference;
					if (warningDays < difference){
						result = "";
						this.status = Status.SUCCEEDED;
					}
					else {
						String message = "";

						message = "A password change is required within " + difference + " days.  Please create a new password soon.";

						incomingRequest.put("warningPassword", "true");
						incomingRequest.put("warningMsg", message);

						result = message;
						this.status = Status.SUCCEEDED;
					}
				}
				else {
				String message = "";

				if (incomingRequest.containsKey("errorMsg")) {
					message = (String) incomingRequest.get("errorMsg") + "  ";
				}

				message = message + "  A password change is required.  Please enter a new password.";

				incomingRequest.put("resetPassword", "true");
				incomingRequest.put("errorMsg", message);

				result = message;
				this.status = Status.SUCCEEDED;
				}
		    }
		    else {
		    	result = "";
		    	this.status = Status.SUCCEEDED;
		    }
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}