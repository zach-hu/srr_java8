package com.tsa.puridiom.approvals.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Task;

public class ApproverLookup extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		String	userId = (String) incomingRequest.get("userId") ;

		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;

		UserManager userManager = UserManager.getInstance() ;
		UserProfile user = userManager.getUser((String)incomingRequest.get("organizationId"),userId) ;

		incomingRequest.put("approverOverride",user.getOverrider());
		incomingRequest.put("approverAmount",user.getApprovalAmount());
		
		return user ;
	}

}