/*
 * Created July , 2004 
 */
package com.tsa.puridiom.securityprofile.tasks;

import com.tsa.puridiom.entity.SecurityProfile;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

/**
 * @author Kelli 
 */
public class SecurityProfileUpdateList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("SecurityProfile_groupId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("securityprofile-update.xml");
				List	securityProfileList = new ArrayList();
				Object groupIdObj = incomingRequest.get("SecurityProfile_groupId");
				
				if (groupIdObj instanceof String[]) {
					String	groupIds[] = (String[]) groupIdObj; 
					String	profileTypes[] = (String[]) incomingRequest.get("SecurityProfile_profileType");
					String	profiles[] = (String[]) incomingRequest.get("SecurityProfile_profile");
					String	flags[] = (String[]) incomingRequest.get("SecurityProfile_flags");
					
					for (int i=0; i < groupIds.length; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("SecurityProfile_groupId", groupIds[i]);
						updateParameters.put("SecurityProfile_profileType", profileTypes[i]);
						updateParameters.put("SecurityProfile_profile", profiles[i]);
						updateParameters.put("SecurityProfile_flags", flags[i]);
						
						process.executeProcess(updateParameters);
						
						SecurityProfile securityProfile = (SecurityProfile) updateParameters.get("securityProfile");
						securityProfileList.add(securityProfile);
					}
				}
				else {
						process.executeProcess(incomingRequest);
						
						SecurityProfile securityProfile = (SecurityProfile) incomingRequest.get("securityProfile");
						securityProfileList.add(securityProfile);
				}
				
				result = securityProfileList;
			}
			else {
				// No records to update.
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
