/*
 * Created on June 10, 2004
 */
package com.tsa.puridiom.usermanager.tasks;

import com.tsa.puridiom.entity.SecurityProfile;
import com.tsa.puridiom.usermanager.UserRole;
import com.tsa.puridiom.usermanager.UserRoleManager;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

/**
 * @author kelli
 */
public class UserRoleGetFromSecurityProfileList extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try 
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			List securityProfileList = (List) incomingRequest.get("securityProfileList");
			UserRole userRole = new UserRole();
			
			if (securityProfileList != null)
			{
				SecurityProfile securityProfile = (SecurityProfile) securityProfileList.get(0);
				if (securityProfile != null) {
					String roleId = securityProfile.getComp_id().getGroupId();
					userRole = UserRoleManager.getInstance().getUserRoleFromSecurityProfileList(organizationId, roleId, securityProfileList);
				}
			}
			
			result = userRole;
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return result;
		}
	}

}
