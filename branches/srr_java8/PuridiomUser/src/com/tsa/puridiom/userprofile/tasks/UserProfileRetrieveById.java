package com.tsa.puridiom.userprofile.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class UserProfileRetrieveById extends Task{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String userId = (String ) incomingRequest.get("UserProfile_userId");
            String organizationId = (String ) incomingRequest.get("UserProfile_organizationId");

            String queryString = "from UserProfile as UserProfile where UserProfile.userId = ? and UserProfile.organizationId = ? ";
            List resultList = dbs.query(queryString, new Object[] {userId.toUpperCase(), organizationId.toUpperCase() } , new Type[] { Hibernate.STRING, Hibernate.STRING }) ;

            if (resultList != null && resultList.size() > 0)
            {
                result = resultList.get(0);
            }
            this.setStatus(dbs.getStatus()) ;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return result;
    }

}