/*
 * Created on March 30, 2005 
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class UserProfileSetTempUserRoles extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		UserProfile userProfile = null;
		
		try {
		    userProfile = (UserProfile) incomingRequest.get("userProfile");
		    
			if (incomingRequest.containsKey("UserGroupRel_userId")) {
			    List userRoles = new ArrayList();
			    
				if (userProfile != null && !HiltonUtility.isEmpty(userProfile.getUserId()))
				{	
					Object groupIdObj = incomingRequest.get("UserGroupRel_groupId");
					if (groupIdObj instanceof String[]) {
						String groupIdArray[] = (String[]) groupIdObj;
						
						for (int i=0; i < groupIdArray.length; i++) {
						    userRoles.add(groupIdArray[i]);
						}
					}
					else {
						String groupId = (String) groupIdObj;
						userRoles.add(groupId);
					}
				}

				userProfile.setUserRoles(userRoles);
			}

			result = userProfile;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
