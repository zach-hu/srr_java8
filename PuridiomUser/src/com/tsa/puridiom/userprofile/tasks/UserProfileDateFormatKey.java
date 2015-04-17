package com.tsa.puridiom.userprofile.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.StdTable;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileDateFormatKey extends Task{

	public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			UserProfile userProfile = (UserProfile)incomingRequest.get("userProfile");
			if(userProfile != null){
				String dateFormat = userProfile.getDateFormat();
				StdTable stdTable = null;
				String dateFormatKey = "";
				String queryString = "from StdTable as StdTable where StdTable.description like ? ";
				List resultList = dbs.query(queryString, new Object[] {dateFormat, } , new Type[] { Hibernate.STRING}) ;

				if (resultList != null && resultList.size() > 0)
				{
					stdTable = (StdTable)resultList.get(0);
					dateFormatKey = stdTable.getTableKey();
					userProfile.setDateFormatKey(dateFormatKey);
				}
				this.setStatus(dbs.getStatus()) ;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
    }
}
