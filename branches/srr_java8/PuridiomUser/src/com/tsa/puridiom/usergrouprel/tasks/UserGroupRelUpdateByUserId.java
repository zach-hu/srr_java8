/*
 * Created on July, 2004 
 */
package com.tsa.puridiom.usergrouprel.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.*;


/**
 * @author Kelli
 */
public class UserGroupRelUpdateByUserId extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	userGroupRelList = new ArrayList();
			
			if (incomingRequest.containsKey("UserGroupRel_groupId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("usergrouprel-update-by-id.xml");

				Object userIdObj = incomingRequest.get("UserGroupRel_userId");
				Object groupIdObj = incomingRequest.get("UserGroupRel_groupId");
				String userGroupRelUserId = "";
				
				if (userIdObj instanceof String[]) {
					String userIdArray[] = (String[]) userIdObj;
					if (userIdArray.length > 0) {
						userGroupRelUserId = userIdArray[0]; 
					}
				} else {
					userGroupRelUserId = (String) userIdObj;
				}

				if (groupIdObj instanceof String[]) {
					String groupIdArray[] = (String[]) groupIdObj;
					
					for (int i=0; i < groupIdArray.length; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("UserGroupRel_userId", userGroupRelUserId) ;
						updateParameters.put("UserGroupRel_groupId", groupIdArray[i]) ;
						
						process.executeProcess(updateParameters);
						
						UserGroupRel userGroupRel = (UserGroupRel) updateParameters.get("userGroupRel");
						
						userGroupRelList.add(userGroupRel);
					}
				}
				else {
					if (incomingRequest.containsKey("UserGroupRel_groupId") && !incomingRequest.containsKey("UserGroupRel_userId")) {
						incomingRequest.put("UserGroupRel_userId", incomingRequest.get("UserProfile_userId"));
					}
					process.executeProcess(incomingRequest);
					
					UserGroupRel userGroupRel = (UserGroupRel) incomingRequest.get("userGroupRel");
					userGroupRelList.add(userGroupRel);
				}
			}
			else {
				//No records to update
			}
			
			result = userGroupRelList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
