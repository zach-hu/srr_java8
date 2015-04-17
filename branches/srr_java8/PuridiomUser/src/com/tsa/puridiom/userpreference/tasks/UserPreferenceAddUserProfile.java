package com.tsa.puridiom.userpreference.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.UserPreference;
import com.tsa.puridiom.entity.UserPreferencePK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class UserPreferenceAddUserProfile extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		String[] properties = {"POPENDINGAPPROVALCNT", "REQPENDINGAPPROVALCNT", "POINCOMPLETECNT", "POOVERDUECNT", 
				"REQASSIGNEDCNT", "REQPRICEDCNT", "RECINDELIVERYCNT", "RECININSPECTIONCNT", "RECPENDINGONINVENTORYCNT", 
				"RECINMARKCNT"};
		
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			
			for (int i = 0; i < properties.length; i++) {
				String property = properties[i];
				
				String queryString = "from UserProfile as UserProfile where UserProfile.userId not in " +
				"(SELECT UserPreference.id.userId FROM UserPreference as UserPreference WHERE " +
				"UserPreference.id.userId = UserProfile.userId and UserPreference.id.property = ?)";

				List userProfileList = dbs.query(queryString, property, Hibernate.STRING); ;
				
				if(userProfileList != null && userProfileList.size() > 0){
					for (int j = 0; j < userProfileList.size(); j++) {
						UserProfile userProfile = (UserProfile)userProfileList.get(j);
						userPrferenceAdd(userProfile, property, dbs);
					}
				}
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, e);
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	private void userPrferenceAdd(UserProfile userProfile, String property, DBSession dbs) throws Exception {
		
		UserPreference userPreference = new UserPreference();
		UserPreferencePK comp_id = new UserPreferencePK();

		comp_id.setUserId(userProfile.getUserId());
		comp_id.setProperty(property);

		userPreference.setComp_id(comp_id);
		userPreference.setValue("0");

		dbs.add(userPreference);
	}

}