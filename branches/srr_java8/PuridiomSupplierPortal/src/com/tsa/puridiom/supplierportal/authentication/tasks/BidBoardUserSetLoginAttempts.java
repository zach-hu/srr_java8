package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BidBoardUserSetLoginAttempts extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BidBoardUser user = (BidBoardUser) incomingRequest.get("bidboardUser");
			String	tempAttempts	= (String) incomingRequest.get("attempts");
			int priorAttempts	= 0;
			String priorId	= (String) incomingRequest.get("priorId");

			if (user != null){
				if (priorId.equals("null")){
					incomingRequest.put("priorId", user.getEmailAddress());

					try{
						priorAttempts = Integer.parseInt(tempAttempts);
					}
					catch (Exception e) { };

					if (user == null)
					{
						String userId = (String) incomingRequest.get("userId");
						String organizationId = (String) incomingRequest.get("organizationId");

						user = new BidBoardUser();
						user.setEmailAddress(userId);
						user.setOrganization(organizationId);
					}

					user.setLoginAttempts(user.getLoginAttempts() + 1 + priorAttempts);

					String attempts = Integer.toString(user.getLoginAttempts());

					incomingRequest.put("attempts", attempts);
				}
				else if(priorId.equals(user.getEmailAddress())){
					try{
						priorAttempts = Integer.parseInt(tempAttempts);
					}
					catch (Exception e) { };

					if (user == null)
					{
						String userId = (String) incomingRequest.get("userId");
						String organizationId = (String) incomingRequest.get("organizationId");

						user = new BidBoardUser();
						user.setEmailAddress(userId);
						user.setOrganization(organizationId);
					}

					user.setLoginAttempts(user.getLoginAttempts() + 1 + priorAttempts);

					String attempts = Integer.toString(user.getLoginAttempts());

					incomingRequest.put("attempts", attempts);
				}
				else {
					incomingRequest.put("priorId", user.getEmailAddress());

					if (user == null)
					{
						String userId = (String) incomingRequest.get("userId");
						String organizationId = (String) incomingRequest.get("organizationId");

						user = new BidBoardUser();
						user.setEmailAddress(userId);
						user.setOrganization(organizationId);
					}

					user.setLoginAttempts(user.getLoginAttempts() + 1);

					String attempts = Integer.toString(user.getLoginAttempts());

					incomingRequest.put("attempts", attempts);
				}
			}

			result = user;
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