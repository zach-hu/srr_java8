package com.tsa.puridiom.userpreference.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserPreference;
import com.tsa.puridiom.entity.UserPreferencePK;
import com.tsa.puridiom.userpreference.UserPreferenceManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserPreferenceUpdateUserIdDocumentCount extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String	organizationId = (String) incomingRequest.get("organizationId");

			List userIdDocumentCountList = (List)incomingRequest.get("userIdDocumentCountList");
			String property = HiltonUtility.ckNull((String)incomingRequest.get("UserPreference_property"));

			if (userIdDocumentCountList != null && userIdDocumentCountList.size() > 0)
			{
				for (int i=0; i<userIdDocumentCountList.size(); i++)
				{
					Object[] userIdDocumentCount = (Object[])userIdDocumentCountList.get(i);

					String userId = (String)userIdDocumentCount[0];
					String documentCount = ((Long)userIdDocumentCount[1]).toString();
					
					if(!HiltonUtility.isEmpty(userId)){
						String queryString = "from UserPreference as UserPreference where UserPreference.id.userId = ? and UserPreference.id.property = ?";
						List resultList = dbs.query(queryString, new Object[] {userId, property } , new Type[] { Hibernate.STRING, Hibernate.STRING});

						UserPreference userPreference;

						/** Updating or Adding */
						if (resultList != null && resultList.size() > 0){
							userPreference = (UserPreference)resultList.get(0);
							userPreference = setupUserPreference(userPreference, userId, property, documentCount);
							dbs.update(userPreference);
						} else {
							userPreference = setupUserPreference(null, userId, property, documentCount);
							dbs.add(userPreference);
						}


						/** UserPreference Set In Cache */
						if (userPreference != null)
						{
							UserPreferenceManager.getInstance().setUserPreferenceInCache(organizationId, userPreference);
						}
					}
				}
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
	
	private UserPreference setupUserPreference(UserPreference userPreference, String userId, String property, String value){
		UserPreferencePK comp_id = new UserPreferencePK();
		
		if (userPreference == null)
		{
			userPreference = new UserPreference();
		}
		
		comp_id.setUserId(userId);
		comp_id.setProperty(property);
		
		userPreference.setComp_id(comp_id);
		userPreference.setValue(value);
		
		return userPreference;
	}
	
}
