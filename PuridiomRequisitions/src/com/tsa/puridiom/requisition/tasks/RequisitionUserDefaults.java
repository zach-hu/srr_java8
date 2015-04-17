package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RequisitionUserDefaults extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId") ;
		String	userId = (String) incomingRequest.get("userId") ;

		UserManager userManager = UserManager.getInstance() ;
		UserProfile user = userManager.getUser(organizationId, userId) ;

		incomingRequest.put("RequisitionHeader_requisitionerCode",userId) ;

		if ( !organizationId.equalsIgnoreCase("bsc04p") )
		{
			incomingRequest.put("RequisitionHeader_departmentCode",user.getDepartment()) ;
		}

		return null ;
	}

}